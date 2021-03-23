package com.project.musicshopservices.service;

import java.util.List;

import com.project.musicshopentities.entities.FloppyDisk;
import com.project.musicshopentities.entities.Album;
import com.project.musicshopentities.entities.Artist;

public interface AdminAlbumService {

	List<FloppyDisk> consultFloppyDisks();

	List<Artist> consultArtist();

	Album saveAlbum(Album album);

	Album updateAlbum(Album album);
	
}
