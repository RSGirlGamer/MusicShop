package com.project.musicshopservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.orders.Order;
import com.project.musicshopdata.DAO.BillDAO;
import com.project.musicshopentities.entities.Bill;
import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.service.BillService;

@Service
public class BillServiceImpl implements BillService {
	
	@Autowired
	private BillDAO billDAO;
	
	@Override
	public Bill saveBill(Bill bill, Order order, Person person) {
		bill.setOrderId(order.id());
		bill.setDateCreation(LocalDateTime.now());
		bill.setStatus(true);
		bill.setCountry(order.payer().addressPortable().adminArea1());
		bill.setCity(order.payer().addressPortable().adminArea2());
		bill.setZipCode(order.payer().addressPortable().postalCode());
		bill.setDirection(order.payer().addressPortable().addressLine1() + ", " + order.payer().addressPortable().addressLine2());
		bill.setCurrency(order.purchaseUnits().get(0).amountWithBreakdown().currencyCode());
		Double sent = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().shipping().value());
		bill.setSent(sent);
		Double discountSent = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().shippingDiscount().value());
		bill.setDiscountSent(discountSent);
		Double handling = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().handling().value());
		bill.setHandling(handling);
		Double total = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().itemTotal().value());
		bill.setTotal(total);
		Double taxTotal = Double.parseDouble(order.purchaseUnits().get(0).amountWithBreakdown().amountBreakdown().taxTotal().value());
		bill.setTotalTax(taxTotal);
		bill.setPerson(person);
		this.billDAO.save(bill);
		return bill;
	}

	@Override
	public List<Bill> consultBillByPerson(Person person) {
		return this.billDAO.findAllByPerson(person);
	}

}
