package com.project.musicshopservices.service.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopservices.service.HomeService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
class HomeServiceImplTest {

	@Autowired
	private HomeService homeServiceImpl;
	@Test
	void testConsultAlbumByFilter() {
		try {
			List<ArtistAlbumDTO> list = this.homeServiceImpl.consultAlbumByFilter("Metal");
			for (ArtistAlbumDTO artistAlbumDTO : list) {
				System.out.println(artistAlbumDTO.getAlbum());
			}
		} catch (NullPointerException e) {
			System.out.println(e.getCause());
		}
	}

}
