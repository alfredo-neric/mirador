package com.sistema.restaurant.mirador.business.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the tc_roles database table.
 * 
 */
@Entity
@Table(name="tc_roles")
@NamedQuery(name="TcRole.findAll", query="SELECT t FROM TcRole t")
public class TcRole extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String clave;

	private String nombre;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "TR_ROLES_MENUS", joinColumns = { 
			@JoinColumn(name = "ID_ROL", nullable = false, updatable = false) }, 
	inverseJoinColumns = { @JoinColumn(name = "ID_MENU", 
	nullable = false, updatable = false) })
	private List<TcMenus> menus;


	public TcRole() {
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TcMenus> getMenus() {
		return menus;
	}

	public void setMenus(List<TcMenus> menus) {
		this.menus = menus;
	}
	
	

}