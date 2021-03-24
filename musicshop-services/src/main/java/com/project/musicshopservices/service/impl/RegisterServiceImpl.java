package com.project.musicshopservices.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.PersonDAO;
import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private PersonDAO personDAOImpl;
	
	@Override
	public Person savePerson(Person person) {
		person.setDateCreation(LocalDateTime.now());
		person.setStatus(true);
		person.getRol().setIdRol(4L);
		person.getCart().setPerson(person);
		return this.personDAOImpl.save(person);
	}

}
