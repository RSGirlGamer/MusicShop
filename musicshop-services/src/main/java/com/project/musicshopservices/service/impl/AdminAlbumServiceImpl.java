package com.project.musicshopservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.AlbumDAO;
import com.project.musicshopdata.DAO.ArtistDAO;
import com.project.musicshopdata.DAO.FloppyDiskDAO;
import com.project.musicshopentities.entities.Album;
import com.project.musicshopentities.entities.Artist;
import com.project.musicshopentities.entities.FloppyDisk;
import com.project.musicshopservices.service.AdminAlbumService;

@Service
public class AdminAlbumServiceImpl implements AdminAlbumService {
	
	@Autowired
	private ArtistDAO artistDAOImpl;
	@Autowired
	private FloppyDiskDAO floppyDiskDAOImpl;
	@Autowired
	private AlbumDAO albumDAOImpl;
	@Override
	public List<FloppyDisk> consultFloppyDisks() {
		Pageable pageable = PageRequest.of(0, 20);
		Page<FloppyDisk> page = this.floppyDiskDAOImpl.findAll(pageable);
		return page.getContent();
		
	}
	@Override
	public List<Artist> consultArtist() {
		Pageable pageable = PageRequest.of(0, 20);
		Page<Artist> page = this.artistDAOImpl.findAll(pageable);
		return page.getContent();
		
	}
	@Override
	public Album saveAlbum(Album album) {
		album.setDateCreation(LocalDateTime.now());
		album.setStatus(true);
		return this.albumDAOImpl.save(album);
	}
	@Override
	public Album updateAlbum(Album album) {
		album.setDateModification(LocalDateTime.now());
		return this.albumDAOImpl.save(album);
	}
}
