package com.project.musicshopservices.service;

import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopentities.entities.Cart;
import com.project.musicshopentities.entities.CartAlbum;

public interface CartService {
	CartAlbum saveAlbumsCart(ArtistAlbumDTO artistAlbumDTO, Cart cart, int amountAlbum);

	float calculateTotal(Cart cart);

	void deleteAlbumCart(CartAlbum cartAlbum);

	float updateAmountCart(CartAlbum cartAlbum, Cart cart);
}
