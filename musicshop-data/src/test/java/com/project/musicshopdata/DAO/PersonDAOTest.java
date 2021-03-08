package com.project.musicshopdata.DAO;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.project.musicshopentities.entities.Person;

@Ignore()
@SpringBootTest
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
class PersonDAOTest {

	@Autowired
	private PersonDAO personDAO;
	
	@Test
	void testLogin() {
		try {
			Person person = this.personDAO.findByUserAndPassword("Rokka", "RSGirlGamer");
			System.out.println(person);
		} catch (NullPointerException e) {
			System.out.println(e.getCause());
		}
	}

}
