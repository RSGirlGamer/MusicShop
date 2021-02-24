package com.project.musicshopentities.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public class CommonEntities {
	@Column(name = "fechaCreacion", nullable = false)
	private LocalDateTime dateCreation;
	@Column(name = "fechaModificacion")
	private LocalDateTime dateModification;
	@Column(name = "estatus", nullable = false)
	private boolean status;
}
