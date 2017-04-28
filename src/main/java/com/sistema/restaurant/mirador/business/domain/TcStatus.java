package com.sistema.restaurant.mirador.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the tc_status database table.
 * 
 */
@Entity
@Table(name="tc_status")
@NamedQuery(name="TcStatus.findAll", query="SELECT t FROM TcStatus t")
public class TcStatus extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String descripcion;

	@Column(name="id_status")
	private BigInteger idStatus;

	public TcStatus() {
	}

	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigInteger getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(BigInteger idStatus) {
		this.idStatus = idStatus;
	}

}