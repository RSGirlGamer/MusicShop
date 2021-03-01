package com.project.musicshopentities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "subgenero")
@Data
@EqualsAndHashCode(callSuper=false)
public class SubGender extends CommonEntities{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSubGenero")
	private Long idSubGender;
	@Column(name = "descripcion", length = 100, nullable = false)
	private String description;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idGenero")
	private Gender gender;
}
