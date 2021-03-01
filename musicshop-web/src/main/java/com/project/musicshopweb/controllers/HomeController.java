package com.project.musicshopweb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopservices.service.HomeService;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class HomeController {
	private String filter;
	private List<ArtistAlbumDTO> artistAlbumDTO;
	@ManagedProperty("#{homeServiceImpl}")
	private HomeService homeServiceImpl;
	@PostConstruct
	public void init() {
		System.out.println("Inicializando...");
	}
	public void consultAlbumsArtistsByFilter() {
		try {
			this.artistAlbumDTO = this.homeServiceImpl.consultAlbumByFilter(this.filter);
			if(this.artistAlbumDTO != null) {
				for (ArtistAlbumDTO artistAlbums : artistAlbumDTO) {
					System.out.println("Artista: " + artistAlbums.getArtist().getName());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getCause());
		}
	}
}
