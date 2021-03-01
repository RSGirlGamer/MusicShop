package com.project.musicshopentities.dto;

import com.project.musicshopentities.entities.Album;
import com.project.musicshopentities.entities.Artist;

import lombok.Data;

@Data
public class ArtistAlbumDTO {
	private Artist artist;
	private Album album;
	public ArtistAlbumDTO() {
		super();
	}
	public ArtistAlbumDTO(Album album, Artist artist) {
		this.album = album;
		this.artist = artist;
	}
}
