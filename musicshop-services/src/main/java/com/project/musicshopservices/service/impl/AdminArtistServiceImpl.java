package com.project.musicshopservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.ArtistDAO;
import com.project.musicshopentities.entities.Artist;
import com.project.musicshopservices.service.AdminArtistService;

@Service
public class AdminArtistServiceImpl implements AdminArtistService {

	@Autowired
	private ArtistDAO artistDAOImpl;
	
	@Override
	public List<Artist> consultArtist() {
		Pageable pageable = PageRequest.of(0, 20);
		Page<Artist> page = this.artistDAOImpl.findAll(pageable);
		return page.getContent();
	}

	@Override
	public Artist saveArtist(Artist artist) {
		artist.setDateCreation(LocalDateTime.now());
		artist.setStatus(true);
		return this.artistDAOImpl.save(artist);
	}

	@Override
	public Artist updateArtist(Artist artist) {
		artist.setDateModification(LocalDateTime.now());
		return this.artistDAOImpl.save(artist);
	}

}
