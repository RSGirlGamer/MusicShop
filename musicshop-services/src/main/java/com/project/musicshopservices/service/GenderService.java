package com.project.musicshopservices.service;

import java.util.List;

import com.project.musicshopentities.entities.Gender;

public interface GenderService {
	List<Gender> consultGender();

	Gender saveGender(Gender gender);

	Gender updateGender(Gender gender);
}
