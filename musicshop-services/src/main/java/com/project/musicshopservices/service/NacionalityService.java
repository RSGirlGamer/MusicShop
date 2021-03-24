package com.project.musicshopservices.service;

import java.util.List;

import com.project.musicshopentities.entities.Nacionality;

public interface NacionalityService {

	List<Nacionality> consultNacionality();

	Nacionality saveNacionality(Nacionality nacionality);

	Nacionality updateNacionality(Nacionality nacionality);
}
