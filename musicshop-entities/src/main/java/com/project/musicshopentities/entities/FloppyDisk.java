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
@Table(name = "disquera")
@Data
@EqualsAndHashCode(callSuper=false)
public class FloppyDisk extends CommonEntities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDisquera")
	private Long idFloppyDisk;
	@Column(name = "descripcion", length = 100, nullable = false)
	private String description;
}
