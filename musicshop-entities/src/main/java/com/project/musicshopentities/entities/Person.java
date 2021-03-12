package com.project.musicshopentities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "persona")
@Data
@EqualsAndHashCode(callSuper=false)
public class Person extends CommonEntities{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPersona", nullable = false)
	private Long idPerson;
	@Column(name = "numeroIdentificacion", length = 12, nullable = false)
	private String numberID;
	@Column(name = "nombre", length = 100, nullable = false)
	private String name;
	@Column(name = "primerApellido", length = 100)
	private String firstLastName;
	@Column(name = "segundoApellido", length = 45)
	private String secondtLastName;
	@Column(name = "imagen", length = 100)
	private String image;
	@Column(name = "email", length = 200, nullable = false)
	private String email;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTipoIdentificacion", nullable = false)
	private TypeofIdentification typeofIdentification;
	@Column(name = "usuario", length = 45, nullable = false)
	private String user;
	@Column(name = "password", length = 45, nullable = false)
	private String password;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRol", nullable = false)
	private Rol rol;
	@OneToOne(mappedBy = "person")
	private Cart cart;
}
