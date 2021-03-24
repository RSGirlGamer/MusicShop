package com.project.musicshopservices.service;

import java.util.List;

import com.project.musicshopentities.entities.SubGender;

public interface SubGenderService {
	List<SubGender> consultSubGender();

	SubGender saveSubGender(SubGender subGender);

	SubGender updateSubGender(SubGender subGender);
}
