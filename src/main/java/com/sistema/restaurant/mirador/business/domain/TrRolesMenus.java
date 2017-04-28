package com.sistema.restaurant.mirador.business.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/**
 * The persistent class for the TR_ROLES_MENUS database table.
 * 
 */
@Entity
@Table(name="TR_ROLES_MENUS")
@NamedQuery(name="TrRolesMenus.findAll", query="SELECT t FROM TrRolesMenus t")
public class TrRolesMenus extends AbstractEntity implements Serializable {
	
	/**
	 * . 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name="ID_ROL")
	private Long idRol;

	@Column(name="ID_MENU")
	private Long idMenu;
	

	
	public TrRolesMenus() {
	}


	public Long getIdRol() {
		return idRol;
	}


	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}


	public Long getIdMenu() {
		return idMenu;
	}


	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

}