package com.project.musicshopweb.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopservices.service.HomeService;
import com.project.musicshopweb.session.SessionBean;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class HomeController {
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
	private String filter;
	private List<ArtistAlbumDTO> artistAlbumDTO;
	@ManagedProperty("#{homeServiceImpl}")
	private HomeService homeServiceImpl;
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
	}
	public void consultAlbumsArtistsByFilter() {
		try {
			this.artistAlbumDTO = this.homeServiceImpl.consultAlbumByFilter(this.filter);
			if(this.artistAlbumDTO != null) {
				for (ArtistAlbumDTO artistAlbums : artistAlbumDTO) {
					LOGGER.info("Artista: " + artistAlbums.getArtist().getName());
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getCause());
		}
	}
	public void showDetail(ArtistAlbumDTO artistAlbum) {
		try {
			this.sessionBean.setArtistAlbumDTO(artistAlbum);
			CommonsUtils.redirect("/pages/client/detail.xhtml");
			LOGGER.info("Entrando...");
		} catch (IOException e) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "Hubo un error de formato");
			LOGGER.info(e.getCause());
		}
	}
}
