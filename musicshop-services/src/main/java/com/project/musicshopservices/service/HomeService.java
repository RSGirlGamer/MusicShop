package com.project.musicshopservices.service;

import java.util.List;

import com.project.musicshopentities.dto.ArtistAlbumDTO;

public interface HomeService {
	public List<ArtistAlbumDTO> consultAlbumByFilter(String filter);
}
