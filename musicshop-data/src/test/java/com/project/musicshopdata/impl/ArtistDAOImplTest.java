package com.project.musicshopdata.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.project.musicshopdata.DAO.ArtistDAO;
import com.project.musicshopentities.dto.ArtistAlbumDTO;

@SpringBootTest
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
class ArtistDAOImplTest {
	
	@Autowired()
	private ArtistDAO artistDAO;
	@Test
	void testConsultObjectsByFilter() {
		try {
			List<ArtistAlbumDTO> list = this.artistDAO.consultAlbumsByFilter("Master");
			for (ArtistAlbumDTO artistAlbumDTO : list) {
				System.out.println(artistAlbumDTO.getAlbum());
			}
		} catch (NullPointerException e) {
			System.out.println(e.getCause());
		}
	}
}
