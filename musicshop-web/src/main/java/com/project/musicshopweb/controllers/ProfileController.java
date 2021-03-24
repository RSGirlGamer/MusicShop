package com.project.musicshopweb.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import com.project.musicshopentities.entities.Rol;
import com.project.musicshopservices.service.AdminProfilesService;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class ProfileController {
	private static final Logger LOGGER = LogManager.getLogger(ProfileController.class);
	@ManagedProperty("#{adminProfilesServiceImpl}")
	private AdminProfilesService adminProfilesServiceImpl;
	private List<Rol> rols;
	private Rol rol;
	private List<Rol> rolFilter;
	
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
		this.cleanComponents();
		this.consult();
	}
	
	public void cleanComponents() {
		this.rol = new Rol();
		LOGGER.info("Limpio");
	}
	 public void consult() {
		 this.rols = this.adminProfilesServiceImpl.consultProfile();
	 }
	 public void save() {
		 Rol savedRol = this.adminProfilesServiceImpl.saveProfile(this.rol);
		 if (savedRol.getIdRol() != null) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado exitósamente", "Se ha guardado exitósamente");
			PrimeFaces.current().executeScript("PF('dlg2').hide()");
			this.consult();
			
		}
	 }
	 public void update() {
		 Rol savedRol = this.adminProfilesServiceImpl.updateProfile(this.rol);
		 if (savedRol.getIdRol() != null) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado exitósamente", "Se ha guardado exitósamente");
			PrimeFaces.current().executeScript("PF('dlg2').hide()");
			this.consult();
		}
	 }
	 public void getRolJSF(Rol rolSelect) {
		 this.rol = rolSelect;
		 LOGGER.info("Obteniendo Datos...");
	 } 
}
