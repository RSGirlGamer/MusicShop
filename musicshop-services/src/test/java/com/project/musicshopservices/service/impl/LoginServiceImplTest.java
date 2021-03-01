package com.project.musicshopservices.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.service.LoginService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
class LoginServiceImplTest {
	
	@Autowired
	private LoginService loginServiceImpl;
	@Test
	void testConsultUser() {
		try {
			Person person = this.loginServiceImpl.consultUser("Rokka", "RSGirlGamer");
			System.out.println(person);
		} catch (NullPointerException e) {
			System.out.println(e.getCause());
		}
	}

}
