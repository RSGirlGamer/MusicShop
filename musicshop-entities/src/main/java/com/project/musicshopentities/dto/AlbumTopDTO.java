package com.project.musicshopentities.dto;

import com.project.musicshopentities.entities.CartAlbum;

import lombok.Data;

@Data
public class AlbumTopDTO {
	private CartAlbum cartAlbum;
	private long amountTotal;
	public AlbumTopDTO() {
		super();
	}
	public AlbumTopDTO(CartAlbum cartAlbum, long amountTotal) {
		this.cartAlbum = cartAlbum;
		this.amountTotal = amountTotal;
	}
}
