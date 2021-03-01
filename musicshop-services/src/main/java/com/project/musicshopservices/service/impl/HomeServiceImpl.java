package com.project.musicshopservices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.ArtistDAO;
import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopservices.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {
	
	@Autowired
	private ArtistDAO artistDAOImpl;

	@Override
	public List<ArtistAlbumDTO> consultAlbumByFilter(String filter) {
		return this.artistDAOImpl.consultAlbumsByFilter(filter);
	}
	

}
