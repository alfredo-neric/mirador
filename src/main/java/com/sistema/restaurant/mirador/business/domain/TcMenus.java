package com.sistema.restaurant.mirador.business.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the tc_menus database table.
 * 
 */
@Entity
@Table(name="tc_menus")
@NamedQuery(name="TcMenus.findAll", query="SELECT t FROM TcMenus t")
public class TcMenus extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String icon;

	private String label;

	@Column(name="STYLE_CLASS")
	private String styleClass;

	private String url;
	
	@OneToMany( fetch=FetchType.LAZY, mappedBy="menu" )
	private List<TcMenuItem> menuItems;

	public TcMenus() {
	}

	

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStyleClass() {
		return this.styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public List<TcMenuItem> getMenuItems() {
		return menuItems;
	}



	public void setMenuItems(List<TcMenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	
	

}