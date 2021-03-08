package com.project.musicshopentities.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "factura")
@Data
@EqualsAndHashCode(callSuper=false)
public class Bill extends CommonEntities{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFactura")
	private Long idBill;
	@Column(name = "orderId", length = 50, nullable = false)
	private String orderId;
	@Column(name = "impuestoTotal")
	private double totalTax;
	@Column(name = "envio")
	private double sent;
	@Column(name = "envioDescuento")
	private double discountSent;
	@Column(name = "handling")
	private double handling;
	@Column(name = "total")
	private double total;
	@Column(name = "direccion", length = 500)
	private String direction;
	@Column(name = "codigoPostal", length = 5)
	private String zipCode;
	@Column(name = "pais", length = 45)
	private String country;
	@Column(name = "ciudad", length = 45)
	private String city;
	@Column(name = "divisa", length = 45)
	private String currency;
	@ManyToOne
	@JoinColumn(name = "idPersona")
	private Person person;
	@OneToMany(mappedBy = "bill")
	private List<CartAlbum> cartAlbums;
}
