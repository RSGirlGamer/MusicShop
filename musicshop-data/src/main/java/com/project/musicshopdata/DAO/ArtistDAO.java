package com.project.musicshopdata.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.project.musicshopentities.dto.ArtistAlbumDTO;
import com.project.musicshopentities.entities.Artist;

public interface ArtistDAO extends PagingAndSortingRepository<Artist, Long>{
	@Query("SELECT new com.project.musicshopentities.dto.ArtistAlbumDTO(album, artist) FROM Album album INNER JOIN album.artist artist INNER JOIN artist.subGender subgender INNER JOIN subgender.gender gender WHERE artist.name LIKE %:filter% OR gender.description LIKE %:filter% OR subgender.description LIKE %:filter% OR album.name LIKE %:filter% ORDER BY artist.name")
	public List<ArtistAlbumDTO> consultAlbumsByFilter(@Param("filter") String filter);
}
