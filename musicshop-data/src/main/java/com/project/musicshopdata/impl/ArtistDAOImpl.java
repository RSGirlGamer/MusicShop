package com.project.musicshopdata.impl;

import java.util.List;

import org.springframework.beans.factory.BeanCreationException;
import com.project.musicshopdata.DAO.ArtistDAO;
import com.project.musicshopdata.common.CommonDAO;
import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopentities.entities.Artist;

public class ArtistDAOImpl extends CommonDAO<Artist, ArtistDAO> {
	public List<ArtistAlbumDTO> consultObjectsByFilter(String filter) {
		try {
			return this.repository.consultAlbumsByFilter(filter);
		} catch (BeanCreationException e) {
			System.out.println(e.getCause());
			return null;
		}
	}
}
