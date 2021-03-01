package com.project.musicshopentities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "nacionalidad")
@Data
@EqualsAndHashCode(callSuper=false)
public class Nacionality extends CommonEntities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idNacionalidad")
	private Long idNacionality;
	@Column(name = "descripcion", length = 60, nullable = false)
	private String description;
	@Column(name = "abreviacion", length = 60, nullable = false)
	private String abbreviation;
	
}
