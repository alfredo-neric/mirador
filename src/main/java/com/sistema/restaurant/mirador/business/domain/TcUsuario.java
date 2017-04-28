package com.sistema.restaurant.mirador.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tc_usuarios database table.
 * 
 */
@Entity
@Table(name="tc_usuarios")
@NamedQuery(name="TcUsuario.findAll", query="SELECT t FROM TcUsuario t")
public class TcUsuario extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer accountnonexpired;

	private Integer accountnonlocked;

	private String contrasenia;

	private Integer credentialsnonexpired;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	private Integer habilitado;


	private String nombre;

	private String usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ROL", nullable = false,updatable=true ,insertable =true)
	private TcRole role;

	public TcUsuario() {
	}

	public Integer getAccountnonexpired() {
		return this.accountnonexpired;
	}

	public void setAccountnonexpired(Integer accountnonexpired) {
		this.accountnonexpired = accountnonexpired;
	}

	public Integer getAccountnonlocked() {
		return this.accountnonlocked;
	}

	public void setAccountnonlocked(Integer accountnonlocked) {
		this.accountnonlocked = accountnonlocked;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Integer getCredentialsnonexpired() {
		return this.credentialsnonexpired;
	}

	public void setCredentialsnonexpired(Integer credentialsnonexpired) {
		this.credentialsnonexpired = credentialsnonexpired;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(Integer habilitado) {
		this.habilitado = habilitado;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public TcRole getRole() {
		return role;
	}

	public void setRole(TcRole role) {
		this.role = role;
	}
	
	

}