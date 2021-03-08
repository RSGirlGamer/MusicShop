package com.project.musicshopservices.service.impl;

import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.project.musicshopentities.entities.CartAlbum;
import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.service.LoginService;

@Ignore()
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
class CartServiceImplTest {
	@Autowired
	private LoginService loginServiceImpl;
	@Test
	void calculateTotal() {
		Person person = this.loginServiceImpl.consultUser("RSGirlGamer", "Rokka");
		List<CartAlbum> cartAlbums = person.getCart().getCartAlbums();
		for (CartAlbum cartAlbum : cartAlbums) {
			System.out.println(cartAlbum.getAmount());
		}
	}

}
