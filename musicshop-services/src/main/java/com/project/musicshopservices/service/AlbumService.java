package com.project.musicshopservices.service;

import java.util.List;

import com.project.musicshopentities.dto.AlbumTopDTO;

public interface AlbumService {
	List<AlbumTopDTO> consultAlbumsTop();
}
