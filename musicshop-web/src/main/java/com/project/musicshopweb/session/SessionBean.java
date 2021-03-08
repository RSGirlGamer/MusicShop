package com.project.musicshopweb.session;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopentities.entities.Person;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.Data;

@ManagedBean
@SessionScoped
@Data
public class SessionBean {
	private Person person;
	private ArtistAlbumDTO artistAlbumDTO;
	private float total;
	@PostConstruct
	public void init() {
		System.out.println("Creando Sesión....");
	}
	public void closeSession() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			CommonsUtils.redirect("/login.xhtml");
		} catch (IOException e) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "Hubo un error al cerrar Sesión");
			System.out.println(e.getCause()); 
		}
	}
}
