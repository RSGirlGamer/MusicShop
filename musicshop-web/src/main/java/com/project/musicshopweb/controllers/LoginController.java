package com.project.musicshopweb.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.musicshopentities.entities.Cart;
import com.project.musicshopentities.entities.CartAlbum;
import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.service.LoginService;
import com.project.musicshopweb.session.SessionBean;
import com.project.musicshopweb.utils.CommonsUtils;
import com.sun.jdi.InvocationException;

import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class LoginController {
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	private String user;
	private String password;
	private Cart cart;
	@ManagedProperty("#{loginServiceImpl}")
	private LoginService loginService;
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	@PostConstruct
	public void init() {
		System.out.println("Inicializando pantalla...");
	}
	public void login() throws InvocationException {
		Person person = this.loginService.consultUser(this.user, this.password);
		if(person != null) {
			try {
				List<CartAlbum> cartAlbums = person.getCart().getCartAlbums().stream().filter(cartAlbum -> 
					cartAlbum.getStatus().equals("Pendiente")).collect(Collectors.toList());
				person.getCart().setCartAlbums(cartAlbums);
				LOGGER.info("Albums del Carrito: " + cartAlbums.size());
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
