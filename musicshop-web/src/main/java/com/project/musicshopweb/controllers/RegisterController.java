package com.project.musicshopweb.controllers;

import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.project.musicshopentities.entities.Cart;
import com.project.musicshopentities.entities.Person;
import com.project.musicshopentities.entities.Rol;
import com.project.musicshopservices.service.RegisterService;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Data
public class RegisterController {
	private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);
	@ManagedProperty("#{registerServiceImpl}")
	private RegisterService registerServiceImpl;
	private UploadedFile uploadedFile;
	private Person person;
	private InputStream inputStream;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	String finalPathImges;
	
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
		this.cleanComponents();
		this.initServices();
	}
	
	public void initServices() {
		String pathImages = "/resources/images/artistas";
		this.finalPathImges = FacesContext.getCurrentInstance().getExternalContext().getRealPath(pathImages);
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
		this.person = new Person();
		this.person.setRol(new Rol());
		this.person.setCart(new Cart());
		LOGGER.info("Limpio");
	}
	 public void save() {
		 try {
			CommonsUtils.saveImage(this.finalPathImges, this.uploadedFile.getFileName(), this.inputStream);
		} catch (IOException e) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error al guardar la imagen");
			LOGGER.info(e.getCause());
		}
		 try {
			 this.person.setImage(this.uploadedFile.getFileName());
			 Person savedPerson = this.registerServiceImpl.savePerson(person);
			 if (savedPerson.getIdPerson() != null) {
				CommonsUtils.showMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado exitósamente", "Se ha guardado exitósamente");
				this.cleanComponents();
				LOGGER.info("Se ha añadido usuario");
			} else {
				CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error al registrarse");
			} 
		 } catch (IndexOutOfBoundsException e) {
			LOGGER.info(e.getMessage());
		}
		
	 }
}
