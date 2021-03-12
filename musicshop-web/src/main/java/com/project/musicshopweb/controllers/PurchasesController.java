package com.project.musicshopweb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.musicshopentities.entities.Bill;
import com.project.musicshopentities.entities.CartAlbum;
import com.project.musicshopservices.service.BillService;
import com.project.musicshopweb.session.SessionBean;
import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class PurchasesController {
	private static final Logger LOGGER = LogManager.getLogger(PurchasesController.class);
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	private List<Bill> bills;
	private List<CartAlbum> cartAlbums;
	@ManagedProperty("#{billServiceImpl}")
	private BillService billServiceImpl;
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
		this.consultBillByPerson();
	}
	public void consultBillByPerson() {
		LOGGER.info("Consultando Facturas");
		this.bills = this.billServiceImpl.consultBillByPerson(this.sessionBean.getPerson());
	}
	public void showDetails(List<CartAlbum> cartAlbums) {
		this.cartAlbums = cartAlbums;
	}
}
