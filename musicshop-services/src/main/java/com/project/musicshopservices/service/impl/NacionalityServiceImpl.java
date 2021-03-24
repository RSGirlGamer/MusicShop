package com.project.musicshopservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.NacionalityDAO;
import com.project.musicshopentities.entities.Nacionality;
import com.project.musicshopservices.service.NacionalityService;

@Service
public class NacionalityServiceImpl implements NacionalityService {

	@Autowired
	private NacionalityDAO nacionalityDAOImpl;
	
	@Override
	public List<Nacionality> consultNacionality() {
		Pageable pageable = PageRequest.of(0, 20);
		Page<Nacionality> page = this.nacionalityDAOImpl.findAll(pageable);
		return page.getContent();
	}

	@Override
	public Nacionality saveNacionality(Nacionality nacionality) {
		nacionality.setDateCreation(LocalDateTime.now());
		nacionality.setStatus(true);
		return this.nacionalityDAOImpl.save(nacionality);
	}

	@Override
	public Nacionality updateNacionality(Nacionality nacionality) {
		nacionality.setDateModification(LocalDateTime.now());
		return this.nacionalityDAOImpl.save(nacionality);
	}

}
