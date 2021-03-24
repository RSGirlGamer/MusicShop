package com.project.musicshopservices.service;

import java.util.List;

import com.project.musicshopentities.entities.Artist;

public interface AdminArtistService {

	List<Artist> consultArtist();

	Artist saveArtist(Artist artist);

	Artist updateArtist(Artist artist);
	
}
