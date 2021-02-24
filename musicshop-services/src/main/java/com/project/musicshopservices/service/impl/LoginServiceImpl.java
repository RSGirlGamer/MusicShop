package com.project.musicshopservices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.musicshopdata.DAO.PersonDAO;
import com.project.musicshopentities.entities.Person;
import com.project.musicshopservices.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private PersonDAO personDAO;
	
	@Override
	public Person consultUser(String user, String password) {
		return this.personDAO.findByUserAndPassword(user, password);
	}

}
