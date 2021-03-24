package com.project.musicshopservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.RolDAO;
import com.project.musicshopentities.entities.Rol;
import com.project.musicshopservices.service.AdminProfilesService;

@Service
public class AdminProfilesServiceImpl implements AdminProfilesService {

	@Autowired
	private RolDAO rolDAOImpl;
	
	@Override
	public List<Rol> consultProfile() {
		Pageable pageable = PageRequest.of(0, 20);
		Page<Rol> page = this.rolDAOImpl.findAll(pageable);
		return page.getContent();
	}

	@Override
	public Rol saveProfile(Rol rol) {
		rol.setDateCreation(LocalDateTime.now());
		rol.setStatus(true);
		return this.rolDAOImpl.save(rol);
	}

	@Override
	public Rol updateProfile(Rol rol) {
		rol.setDateModification(LocalDateTime.now());
		return this.rolDAOImpl.save(rol);
	}

}
