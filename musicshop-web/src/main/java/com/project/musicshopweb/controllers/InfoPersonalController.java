package com.project.musicshopweb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.service.InfoPersonalService;
import com.project.musicshopweb.session.SessionBean;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class InfoPersonalController {
	private static final Logger LOGGER = LogManager.getLogger(InfoPersonalController.class);
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	@ManagedProperty("#{infoPersonalServiceImpl}")
	private InfoPersonalService infoPersonalServiceImpl;
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
	}
	public void updatePerson() {
		Person person = this.infoPersonalServiceImpl.updatePerson(this.sessionBean.getPerson());
		if (person != null) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_INFO, "Información Actualizada", "Información Actualizada");
		}
	}
}
