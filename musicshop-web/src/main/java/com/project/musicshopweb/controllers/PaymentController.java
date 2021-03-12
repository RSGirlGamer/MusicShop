package com.project.musicshopweb.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.project.musicshopentities.entities.Bill;
import com.project.musicshopentities.entities.CartAlbum;
import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.client.ReportServiceClient;
import com.project.musicshopservices.service.BillService;
import com.project.musicshopservices.service.CartService;
import com.project.musicshopweb.session.SessionBean;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class PaymentController {
	private static final Logger LOGGER = LogManager.getLogger(PaymentController.class);
	@ManagedProperty("#{billServiceImpl}")
	private BillService billServiceImpl;
	@ManagedProperty("#{cartServiceImpl}")
	private CartService cartServiceImpl;
	@ManagedProperty("#{reportServiceClient}")
	private ReportServiceClient reportServiceClient;
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	private StepController stepController = new StepController();
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
	}
	public void saveBill() {
		LOGGER.info("Guardando Factura....");
		HttpResponse<Order> order = this.sessionBean.getOrder();
		Person person = this.sessionBean.getPerson();
		Bill bill = new Bill();
		Bill newBill =  this.billServiceImpl.saveBill(bill, order.result(), person);
		if (newBill != null) {
			boolean productsCartUpdated = this.cartServiceImpl.updateCartAlbum(sessionBean.getPerson().getCart().getCartAlbums(), newBill);
			if (productsCartUpdated) {
				String client = person.getName() + person.getFirstLastName() + person.getSecondtLastName();
				Response response = this.reportServiceClient.generateReport(order.result().id(), person.getEmail(), client);
				LOGGER.info(response.getStatus());
				this.sessionBean.getPerson().getCart().setCartAlbums(new ArrayList<CartAlbum>());
				this.sessionBean.setStep(2);
				try {
					CommonsUtils.redirect("/pages/client/confirm.xhtml");
				} catch (IOException e) {
					LOGGER.info(e.getMessage());
					e.printStackTrace();
				}
			}
		} else {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha generado un error al crear la factura");
		}
	}
}
