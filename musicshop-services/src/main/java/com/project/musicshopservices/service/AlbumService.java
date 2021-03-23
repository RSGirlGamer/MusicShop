package com.project.musicshopservices.service;

import java.util.List;

import com.project.musicshopentities.dto.AlbumTopDTO;
import com.project.musicshopentities.entities.Album;

public interface AlbumService {
	List<AlbumTopDTO> consultAlbumsTop();
	List<Album> consultAlbums();
}
