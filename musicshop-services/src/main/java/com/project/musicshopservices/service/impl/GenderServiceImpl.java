package com.project.musicshopservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.GenderDAO;
import com.project.musicshopentities.entities.Gender;
import com.project.musicshopservices.service.GenderService;

@Service
public class GenderServiceImpl implements GenderService {

	@Autowired
	private GenderDAO genderDAOImpl;
	
	@Override
	public List<Gender> consultGender() {
		Pageable pageable = PageRequest.of(0, 20);
		Page<Gender> page = this.genderDAOImpl.findAll(pageable);
		return page.getContent();
	}

	@Override
	public Gender saveGender(Gender gender) {
		gender.setDateCreation(LocalDateTime.now());
		gender.setStatus(true);
		return this.genderDAOImpl.save(gender);
	}

	@Override
	public Gender updateGender(Gender gender) {
		gender.setDateModification(LocalDateTime.now());
		return this.genderDAOImpl.save(gender);
	}

}
