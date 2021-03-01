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
@Table(name = "artista")
@Data
@EqualsAndHashCode(callSuper=false)
public class Artist extends CommonEntities{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idArtista")
	private Long idArtist;
	@Column(name = "nombre", length = 100, nullable = false)
	private String name;
	@Column(name = "imagen", length = 100)
	private String image;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idNacionalidad")
	private Nacionality nacionality;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idSubGenero")
	private SubGender subGender;
}
