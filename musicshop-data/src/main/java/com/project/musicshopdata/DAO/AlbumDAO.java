package com.project.musicshopdata.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.musicshopentities.dto.AlbumTopDTO;
import com.project.musicshopentities.entities.Album;

public interface AlbumDAO extends PagingAndSortingRepository<Album, Long>{
	@Query("SELECT new com.project.musicshopentities.dto.AlbumTopDTO(cartAlbum, SUM(cartAlbum.amount) as amountTotal) FROM CartAlbum cartAlbum INNER JOIN cartAlbum.album album WHERE cartAlbum.status = 'PAGADO' GROUP BY album.name ORDER BY cartAlbum.amount DESC")
	Page<AlbumTopDTO> consultAlbumsByFilter(Pageable pageable);
}
