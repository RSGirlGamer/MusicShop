package com.project.musicshopdata.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.musicshopentities.entities.Gender;

public interface GenderDAO extends PagingAndSortingRepository<Gender, Long>{
	
}
