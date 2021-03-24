package com.project.musicshopweb.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.project.musicshopentities.entities.Artist;
import com.project.musicshopentities.entities.Gender;
import com.project.musicshopentities.entities.Nacionality;
import com.project.musicshopentities.entities.SubGender;
import com.project.musicshopservices.service.AdminArtistService;
import com.project.musicshopservices.service.GenderService;
import com.project.musicshopservices.service.NacionalityService;
import com.project.musicshopservices.service.SubGenderService;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Data
public class ArtistController {
	private static final Logger LOGGER = LogManager.getLogger(ArtistController.class);
	@ManagedProperty("#{adminArtistServiceImpl}")
	private AdminArtistService adminArtistServiceImpl;
	@ManagedProperty("#{subGenderServiceImpl}")
	private SubGenderService subGenderServiceImpl;
	@ManagedProperty("#{genderServiceImpl}")
	private GenderService genderServiceImpl;
	@ManagedProperty("#{nacionalityServiceImpl}")
	private NacionalityService nacionalityServiceImpl;
	private List<Artist> artists;
	private List<Nacionality> nacionalities;
	private List<Artist> artistFilter;
	private UploadedFile uploadedFile;
	private Artist artist;
	private Nacionality nacionality;
	private List<SelectItem> gendersGroup;
	private List<SubGender> subGenders;
	private List<Gender> genders;
	private InputStream inputStream;
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
		this.nacionalities = this.nacionalityServiceImpl.consultNacionality();
		this.genders = this.genderServiceImpl.consultGender();
		String pathImages = "/resources/images/artistas";
		this.finalPathImges = FacesContext.getCurrentInstance().getExternalContext().getRealPath(pathImages);
		this.initGendersServices();
	}
	public void initGendersServices() {
		try {
			gendersGroup = new ArrayList<SelectItem>();
			for (Gender gender : this.genders) {
				SelectItemGroup genderItems = new SelectItemGroup(gender.getDescription());
				this.subGenders = this.subGenderServiceImpl.consultSubGender();
				List<SelectItem> arrayListSubGender = new ArrayList<SelectItem>();
				for (SubGender subGender : subGenders) {
					if(subGender.getGender().getIdGender() == gender.getIdGender()) {
						SelectItem subGenderItem = new SelectItem(subGender.getIdSubGender(), subGender.getDescription());
						arrayListSubGender.add(subGenderItem);
					}
				}
				SelectItem subGenderItems[] = new SelectItem[arrayListSubGender.size()];
				subGenderItems = arrayListSubGender.toArray(subGenderItems);
				genderItems.setSelectItems(subGenderItems);
				this.gendersGroup.add(genderItems);
			}
		} catch (NullPointerException e) {
			LOGGER.info(e.getMessage());
		} catch (ClassCastException e) {
			LOGGER.info(e.getMessage());
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {
		this.uploadedFile = fileUploadEvent.getFile();
		try {
			this.inputStream = fileUploadEvent.getFile().getInputStream();
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
		this.artist = new Artist();
		this.artist.setNacionality(new Nacionality());
		this.artist.setSubGender(new SubGender());
		LOGGER.info("Limpio");
	}
	 public void consult() {
		 this.artists = this.adminArtistServiceImpl.consultArtist();
	 }
	 public void save() {
		 try {
			CommonsUtils.saveImage(this.finalPathImges, this.uploadedFile.getFileName(), this.inputStream);
		} catch (IOException e) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error al guardar la imagen");
			LOGGER.info(e.getCause());
		}
		 this.artist.setImage(this.uploadedFile.getFileName());
		 Artist savedAlbum = this.adminArtistServiceImpl.saveArtist(this.artist);
		 if (savedAlbum.getIdArtist() != null) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado exitósamente", "Se ha guardado exitósamente");
			PrimeFaces.current().executeScript("PF('dlg2').hide()");
			this.consult();
			
		}
	 }
	 public void update() {
		 try {
			CommonsUtils.saveImage(this.finalPathImges, this.uploadedFile.getFileName(), this.inputStream);
		} catch (IOException e) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error al guardar la imagen");
			LOGGER.info(e.getCause());
		}
		 this.artist.setImage(this.uploadedFile.getFileName());
		 Artist savedAlbum = this.adminArtistServiceImpl.updateArtist(this.artist);
		 if (savedAlbum.getIdArtist() != null) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado exitósamente", "Se ha guardado exitósamente");
			PrimeFaces.current().executeScript("PF('dlg2').hide()");
			this.consult();
		}
	 }
	 public void getArtistJSF(Artist artistSelect) {
		 this.artist = artistSelect;
		 LOGGER.info("Obteniendo Datos...");
	 } 
}
