package com.project.musicshopdata.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.musicshopentities.entities.Nacionality;

public interface NacionalityDAO extends PagingAndSortingRepository<Nacionality, Long>{
	
}
