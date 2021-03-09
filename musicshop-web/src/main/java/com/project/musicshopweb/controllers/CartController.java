package com.project.musicshopweb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopentities.entities.CartAlbum;
import com.project.musicshopservices.service.CartService;
import com.project.musicshopweb.session.SessionBean;
import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class CartController {
	private static final Logger LOGGER = LogManager.getLogger(CartController.class);
	private String filter;
	private List<ArtistAlbumDTO> artistAlbumDTO;
	@ManagedProperty("#{cartServiceImpl}")
	private CartService cartServiceImpl;
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	@PostConstruct
	public void init() {
		this.sessionBean.setStep(0);
		LOGGER.info("INFO");
		this.calculateTotal();
	}
	public void calculateTotal() {
		try {
			float total = this.cartServiceImpl.calculateTotal(this.sessionBean.getPerson().getCart());
			this.sessionBean.setTotal(total);
		} catch (ArithmeticException e) {
			LOGGER.error(e.getCause());
		} catch (NullPointerException e) {
			LOGGER.info(e.getLocalizedMessage());
		}
	}
	public void deleteAlbumCart(CartAlbum cartAlbum) {
		LOGGER.info("Eliminando Albúm");
		this.cartServiceImpl.deleteAlbumCart(cartAlbum);
		this.sessionBean.getPerson().getCart().getCartAlbums().remove(cartAlbum);
		this.calculateTotal();
	}
	public void updateAmountCar(CartAlbum cartAlbum) {
		float total = this.cartServiceImpl.updateAmountCart(cartAlbum, this.sessionBean.getPerson().getCart());
		this.sessionBean.setTotal(total);
	}
}
