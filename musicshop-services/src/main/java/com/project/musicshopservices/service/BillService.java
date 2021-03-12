package com.project.musicshopservices.service;

import com.paypal.orders.Order;
import com.project.musicshopentities.entities.Bill;
import com.project.musicshopentities.entities.Person;

public interface BillService {

	Bill saveBill(Bill bill, Order order, Person person);
	
}
