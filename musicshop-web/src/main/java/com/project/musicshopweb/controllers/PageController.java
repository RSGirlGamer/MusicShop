package com.project.musicshopweb.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.musicshopweb.session.SessionBean;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class PageController {
	private static final Logger LOGGER = LogManager.getLogger(PageController.class);
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
	}
	public void redirectPage(String page) {
		try {
			CommonsUtils.redirect(page);
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un error al redireccionar");
			e.printStackTrace();
		}
	}
}
