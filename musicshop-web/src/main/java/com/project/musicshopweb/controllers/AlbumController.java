package com.project.musicshopweb.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.project.musicshopentities.entities.Album;
import com.project.musicshopentities.entities.Artist;
import com.project.musicshopentities.entities.FloppyDisk;
import com.project.musicshopservices.service.AdminAlbumService;
import com.project.musicshopservices.service.AlbumService;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Data
public class AlbumController {
	private static final Logger LOGGER = LogManager.getLogger(AlbumController.class);
	@ManagedProperty("#{albumServiceImpl}")
	private AlbumService albumServiceImpl;
	@ManagedProperty("#{adminAlbumServiceImpl}")
	private AdminAlbumService adminAlbumServiceImpl;
	private List<Album> albums;
	private List<Artist> artists;
	private List<FloppyDisk> floppyDisks;
	private List<Album> albumFilter;
	private Album album;
	private UploadedFile uploadedFile;
	private InputStream inputStrean;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	String finalPathImges;
	
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
		this.cleanComponents();
		this.consult();
		this.initServices();
	}
	
	public void initServices() {
		this.artists = this.adminAlbumServiceImpl.consultArtist();
		this.floppyDisks = this.adminAlbumServiceImpl.consultFloppyDisks();
		String pathImages = "/resources/images/albums";
		this.finalPathImges = FacesContext.getCurrentInstance().getExternalContext().getRealPath(pathImages);
		 
	}
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {
		this.uploadedFile = fileUploadEvent.getFile();
		try {
			this.inputStrean = fileUploadEvent.getFile().getInputStream();
			LOGGER.info("Se ha añadido la imagen");
			CommonsUtils.showMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se ha añadido la imagen Correctamente");
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error al cargar el archivo");
		} catch (NullPointerException e) {
			LOGGER.info("No se ha añadido imagen");
		}
	}
	public void cleanComponents() {
		this.album = new Album();
		this.album.setArtist(new Artist());
		this.album.setFloppyDisk(new FloppyDisk());
		LOGGER.info("Limpio");
	}
	 public void consult() {
		 this.albums = this.albumServiceImpl.consultAlbums();
	 }
	 public void save() {
		 try {
			CommonsUtils.saveImage(this.finalPathImges, this.uploadedFile.getFileName(), this.inputStrean);
		} catch (IOException e) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error al guardar la imagen");
			LOGGER.info(e.getCause());
		}
		 this.album.setImage(this.uploadedFile.getFileName());
		 Album savedAlbum = this.adminAlbumServiceImpl.saveAlbum(this.album);
		 if (savedAlbum.getIdAlbum() != null) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado exitósamente", "Se ha guardado exitósamente");
			PrimeFaces.current().executeScript("PF('dlg2').hide()");
			this.consult();
			
		}
	 }
	 public void update() {
		 try {
			CommonsUtils.saveImage(this.finalPathImges, this.uploadedFile.getFileName(), this.inputStrean);
		} catch (IOException e) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error al guardar la imagen");
			LOGGER.info(e.getCause());
		}
		 this.album.setImage(this.uploadedFile.getFileName());
		 Album savedAlbum = this.adminAlbumServiceImpl.updateAlbum(this.album);
		 if (savedAlbum.getIdAlbum() != null) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado exitósamente", "Se ha guardado exitósamente");
			PrimeFaces.current().executeScript("PF('dlg2').hide()");
			this.consult();
		}
	 }
	 public void getAlbumJSF(Album albumSelect) {
		 this.album = albumSelect;
		 LOGGER.info("Obteniendo Datos...");
	 } 
}
