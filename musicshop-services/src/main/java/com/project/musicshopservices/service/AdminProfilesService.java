package com.project.musicshopservices.service;

import java.util.List;

import com.project.musicshopentities.entities.Rol;

public interface AdminProfilesService {

	List<Rol> consultProfile();

	Rol saveProfile(Rol rol);

	Rol updateProfile(Rol rol);
}
