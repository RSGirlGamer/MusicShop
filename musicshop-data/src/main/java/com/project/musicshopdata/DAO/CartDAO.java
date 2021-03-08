package com.project.musicshopdata.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.musicshopentities.entities.CartAlbum;

public interface CartDAO extends PagingAndSortingRepository<CartAlbum, Long> {
	
}
