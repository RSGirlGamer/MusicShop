package com.project.musicshopweb.controllers;

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
public class StepController {
	private static final Logger LOGGER = LogManager.getLogger(StepController.class);
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
	}
	public void changeStep(String url, int step) {
		try {
			this.sessionBean.setStep(step);
			CommonsUtils.redirect(url);
		} catch (Exception e) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un problema al ingresar al siguiente paso de la Compra.");
		}
	}
}
