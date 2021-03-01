package com.project.musicshopweb.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.service.LoginService;
import com.project.musicshopweb.session.SessionBean;
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
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	@PostConstruct
	public void init() {
		System.out.println("Inicializando pantalla...");
	}
	public void login() {
		Person person = this.loginService.consultUser(this.user, this.password);
		if(person != null) {
			try {
				this.sessionBean.setPerson(person);
				CommonsUtils.redirect("/pages/commons/dashboard.xhtml");
			} catch (IOException e) {
				CommonsUtils.showMessage(FacesMessage.SEVERITY_FATAL, "Error al Iniciar Sesión", "Hubo un problema al entrar, Intente de nuevo");
				System.out.println(e.getCause());
			}
		} else {
			CommonsUtils.showMessage(FacesMessage.SEVERITY_ERROR, "Error al Iniciar Sesión", "Usuario y/o Contraseña incorrectos");
		}
	}
}
