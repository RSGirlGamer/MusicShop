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
@Table(name = "rol")
@Data
@EqualsAndHashCode(callSuper=false)
public class Rol extends CommonEntities{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRol")
	private Long idRol;
	@Column(name = "nombre", length = 45, nullable = false)
	private String name;
}
