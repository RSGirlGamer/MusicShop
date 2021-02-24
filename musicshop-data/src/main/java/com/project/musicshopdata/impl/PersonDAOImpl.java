package com.project.musicshopdata.impl;

import com.project.musicshopdata.DAO.PersonDAO;
import com.project.musicshopdata.common.CommonDAO;
import com.project.musicshopentities.entities.Person;

public class PersonDAOImpl extends CommonDAO<Person, PersonDAO> {
	public Person login(String user, String password) {
		return this.repository.findByUserAndPassword(user, password);
	}
}
