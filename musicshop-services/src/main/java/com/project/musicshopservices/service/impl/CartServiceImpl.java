package com.project.musicshopservices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.CartDAO;
import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopentities.entities.Cart;
import com.project.musicshopentities.entities.CartAlbum;
import com.project.musicshopservices.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAO cartDAO;
	@Override
	public CartAlbum saveAlbumsCart(ArtistAlbumDTO artistAlbumDTO, Cart cart, int amountAlbum) {
		CartAlbum cartAlbum = new CartAlbum();
		cartAlbum.setAlbum(artistAlbumDTO.getAlbum());
		cartAlbum.setCart(cart);
		cartAlbum.setAmount(amountAlbum);
		cartAlbum.setStatus("Pendiente");
		this.cartDAO.save(cartAlbum);
		return cartAlbum;
	}
	@Override
	public float calculateTotal(Cart cart) {
		float total = 0.00F;
		for (CartAlbum cartAlbum : cart.getCartAlbums()) {
			total += (cartAlbum.getAlbum().getValue() * cartAlbum.getAmount());
		}
		return total;
	}
	@Override
	public void deleteAlbumCart(CartAlbum cartAlbum) {
		this.cartDAO.delete(cartAlbum);
	}
	@Override
	public float updateAmountCart(CartAlbum cartAlbum, Cart cart) {
		this.cartDAO.save(cartAlbum);
		float total = this.calculateTotal(cart);
		return total;
	}
}
