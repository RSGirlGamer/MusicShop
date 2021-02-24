package com.project.musicshopweb.controllers;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.service.LoginService;
import com.project.musicshopweb.utils.CommonsUtils;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class LoginController {
	private String user;
	private String password;
	@ManagedProperty("#{loginServiceImpl}")
	private LoginService loginService;
	@PostConstruct
	public void init() {
		System.out.println("Inicializando pantalla...");
	}
	public void login() {
		Person person = this.loginService.consultUser(this.user, this.password);
		if(person != null) {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_INFO, "Entrando al Sitio Web", "Entrando al Sitio Web");
		} else {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error al Iniciar Sesión", "Usuario y/o Contraseña incorrectos");
		}
	}
}
