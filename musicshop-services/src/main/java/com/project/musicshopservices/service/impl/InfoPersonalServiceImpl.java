package com.project.musicshopservices.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.PersonDAO;
import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.service.InfoPersonalService;

@Service
public class InfoPersonalServiceImpl implements InfoPersonalService {
	
	@Autowired
	private PersonDAO personDAOImpl;
	
	@Override
	public Person updatePerson(Person person) {
		person.setDateModification(LocalDateTime.now());
		return this.personDAOImpl.save(person);
	}

}
