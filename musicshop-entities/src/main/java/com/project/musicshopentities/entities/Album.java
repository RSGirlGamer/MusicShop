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
@Table(name = "album")
@Data
@EqualsAndHashCode(callSuper=false)
public class Album extends CommonEntities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAlbum")
	private Long idAlbum;
	@Column(name = "nombre", length = 100, nullable = false)
	private String name;
	@Column(name = "descripcion", length = 100, nullable = false)
	private String description;
	@Column(name = "imagen", length = 100)
	private String image;
	@Column(name = "anio", nullable = false)
	private int year;
	@Column(name = "formato", length = 10, nullable = false)
	private String format;
	@Column(name = "valor", nullable = false)
	private double value;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idDisquera")
	private FloppyDisk floppyDisk;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idArtista")
	private Artist artist;
	@Column(name = "rating")
	private Integer rating;
}
