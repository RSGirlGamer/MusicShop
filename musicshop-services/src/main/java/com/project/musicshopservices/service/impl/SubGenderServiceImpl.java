package com.project.musicshopservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.SubGenderDAO;
import com.project.musicshopentities.entities.SubGender;
import com.project.musicshopservices.service.SubGenderService;

@Service
public class SubGenderServiceImpl implements SubGenderService {

	@Autowired
	private SubGenderDAO subGenderDAOImpl;
	
	@Override
	public List<SubGender> consultSubGender() {
		Pageable pageable = PageRequest.of(0, 20);
		Page<SubGender> page = this.subGenderDAOImpl.findAll(pageable);
		return page.getContent();
	}

	@Override
	public SubGender saveSubGender(SubGender subGender) {
		subGender.setDateCreation(LocalDateTime.now());
		subGender.setStatus(true);
		return this.subGenderDAOImpl.save(subGender);
	}

	@Override
	public SubGender updateSubGender(SubGender subGender) {
		subGender.setDateModification(LocalDateTime.now());
		return this.subGenderDAOImpl.save(subGender);
	}

}
