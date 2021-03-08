package com.project.musicshopdata.DAO;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@Ignore()
@SpringBootTest
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
class CartDAOTest {
	
	@Autowired
	private CartDAO cartDAO;

	@Test
	void findByID() {
		this.cartDAO.findById(1L);
	}

}
