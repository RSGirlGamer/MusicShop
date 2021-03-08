package com.project.musicshopweb.controllers;

import java.util.List;

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
public class DetailController {
	private int amountAlbum;
	private static final Logger LOGGER = LogManager.getLogger(DetailController.class);
	@ManagedProperty("#{cartServiceImpl}")
	private CartService cartServiceImpl;
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	public void init() {
		this.amountAlbum = 1;
	}
	public void addAlbumCart(ArtistAlbumDTO artistAlbumDTO) {
		LOGGER.info("Agregando Albúm al Carrito: " + this.amountAlbum);
		CartAlbum cartAlbum = this.cartServiceImpl.saveAlbumsCart(artistAlbumDTO, this.sessionBean.getPerson().getCart(), amountAlbum);
		List<CartAlbum> cartAlbums = this.sessionBean.getPerson().getCart().getCartAlbums();
		cartAlbums.add(cartAlbum);
	}
}
