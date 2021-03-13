package com.project.musicshopservices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.AlbumDAO;
import com.project.musicshopentities.dto.AlbumTopDTO;
import com.project.musicshopservices.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {
	
	@Autowired
	private AlbumDAO albumDAO;
	
	@Override
	public List<AlbumTopDTO> consultAlbumsTop() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<AlbumTopDTO> page = this.albumDAO.consultAlbumsByFilter(pageable);
		return page.getContent();
	}
}
