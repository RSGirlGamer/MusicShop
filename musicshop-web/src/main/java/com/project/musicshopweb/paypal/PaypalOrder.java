package com.project.musicshopweb.paypal;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.paypal.http.HttpResponse;
import com.paypal.orders.AddressPortable;
import com.paypal.orders.AmountBreakdown;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Item;
import com.paypal.orders.Money;
import com.paypal.orders.Name;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.Payer;
import com.paypal.orders.PurchaseUnitRequest;
import com.paypal.orders.ShippingDetail;
import com.project.musicshopentities.entities.CartAlbum;
import com.project.musicshopweb.session.SessionBean;

public class PaypalOrder extends PayPalClient {
	private OrderRequest orderRequest;
	private static final Logger LOGGER = LogManager.getLogger(PaypalOrder.class);
	public HttpResponse<Order> createOrder(SessionBean sessionBean) throws IOException {
		LOGGER.info("Ingresando la Orden...");
		OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest();
		ordersCreateRequest.prefer("return=representation");
		ordersCreateRequest.requestBody(this.generateBodyOrder(sessionBean));
		HttpResponse<Order> response = client.execute(ordersCreateRequest);
		sessionBean.setOrder(response);
		return response;
	}
	private OrderRequest generateBodyOrder(SessionBean sessionBean) {
		this.orderRequest = new OrderRequest();
		Payer payer = new Payer();
		this.orderRequest.checkoutPaymentIntent("CAPTURE");
		ApplicationContext applicationContext = new ApplicationContext().brandName("MusicShop INC").landingPage("BILLING").shippingPreference("SET_PROVIDED_ADDRESS");
		this.orderRequest.applicationContext(applicationContext);
		String name = sessionBean.getPerson().getName();
		String firstLastName = sessionBean.getPerson().getFirstLastName();
		String secondLastName = sessionBean.getPerson().getSecondtLastName();
		List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
		List<Item> items = new ArrayList<Item>();
		List<CartAlbum> cartAlbums = sessionBean.getPerson().getCart().getCartAlbums();
		double taxByProduct = 10.00;
		double taxTotalProduct = 0.0;
		for (CartAlbum cartAlbum : cartAlbums) {
			Item item = new Item();
			item.name(cartAlbum.getAlbum().getName());
			String description = cartAlbum.getAlbum().getDescription().substring(0, 50);
			item.description(description);
			item.unitAmount(new Money().currencyCode("USD").value(String.valueOf(cartAlbum.getAlbum().getValue())));
			item.tax(new Money().currencyCode("USD").value(String.valueOf(taxByProduct)));
			item.quantity(String.valueOf(cartAlbum.getAmount()));
			item.category("PHYSICAL_GOODS");
			item.sku("sku1");
			items.add(item);
			taxTotalProduct += (taxByProduct * cartAlbum.getAmount());
		}
		ShippingDetail shippingDetail = new ShippingDetail();
		shippingDetail.name(new Name().fullName(name + " " + firstLastName + " " + secondLastName));
		shippingDetail.addressPortable(new AddressPortable().addressLine1("Hamilton Street").addressLine2("Sakura Center").adminArea1("Japón").adminArea2("San Fransokio").countryCode("SV").postalCode("0614"));
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		double totalDecimal = Double.valueOf(decimalFormat.format(sessionBean.getTotal()));
		double send = 20.00;
		double handling = 10.00;
		double discountSend = 20.00;
		double totalPurchaseTax = totalDecimal + taxTotalProduct + handling + (send - discountSend);
		PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest();
		purchaseUnitRequest.items(items);
		purchaseUnitRequest.shippingDetail(shippingDetail);
		purchaseUnitRequest.amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(String.valueOf(totalPurchaseTax)).amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("USD").value(String.valueOf(totalDecimal))).shipping(new Money().currencyCode("USD").value(String.valueOf(send))).handling(new Money().currencyCode("USD").value(String.valueOf(handling))).taxTotal(new Money().currencyCode("USD").value(String.valueOf(taxTotalProduct))).shippingDiscount(new Money().currencyCode("USD").value(String.valueOf(discountSend)))));
		purchaseUnitRequests.add(purchaseUnitRequest);
		this.orderRequest.purchaseUnits(purchaseUnitRequests);
		payer.addressPortable(purchaseUnitRequest.shippingDetail().addressPortable());
		this.orderRequest.payer(payer);
		Gson gson = new Gson();
		LOGGER.info(gson.toJson(purchaseUnitRequests));
		return this.orderRequest;
	}
}
