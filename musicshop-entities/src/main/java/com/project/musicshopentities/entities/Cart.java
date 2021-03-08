package com.project.musicshopentities.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "carrito")
@Data
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCarrito")
	private Long idCart;
	@OneToOne
	@JoinColumn(name = "idPersona")
	private Person person;
	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
	private List<CartAlbum> cartAlbums;
}
