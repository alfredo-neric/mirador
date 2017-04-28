package com.sistema.restaurant.mirador.business.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tc_proveedor database table.
 * 
 */
@Entity
@Table(name="tc_proveedor")
@NamedQuery(name="TcProveedor.findAll", query="SELECT t FROM TcProveedor t")
public class TcProveedor extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String correo;

	private String direccion;

	@Column(name="nombre_proveedor")
	private String nombreProveedor;

	private String telefono;

	public TcProveedor() {
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombreProveedor() {
		return this.nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}