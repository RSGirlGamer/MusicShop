package com.project.musicshopentities.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "carrito_album")
@Data
public class CartAlbum {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCarritoAlbum")
	private Long idCartAlbum;
	@ManyToOne()
	@JoinColumn(name = "idCarrito")
	private Cart cart;
	@ManyToOne
	@JoinColumn(name = "idAlbum")
	private Album album;
	@ManyToOne
	@JoinColumn(name = "idFactura")
	private Bill bill;
	@Column(name = "cantidad")
	private int amount;
	@Column(name = "estatus")
	private String status;
	@Column(name = "fechaCompra")
	private LocalDateTime datePurchase;
}
