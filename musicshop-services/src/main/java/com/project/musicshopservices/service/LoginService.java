package com.project.musicshopservices.service;

import com.project.musicshopentities.entities.Person;

public interface LoginService {
	Person consultUser(String user, String password);
}
