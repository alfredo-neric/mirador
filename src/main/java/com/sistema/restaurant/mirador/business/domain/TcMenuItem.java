package com.sistema.restaurant.mirador.business.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tc_menu_items database table.
 * 
 */
@Entity
@Table(name="tc_menu_items")
@NamedQuery(name="TcMenuItem.findAll", query="SELECT t FROM TcMenuItem t")
public class TcMenuItem extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String icon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MENU", nullable = false)
	private TcMenus menu;

	private String label;

	@Column(name="STYLE_CLASS")
	private String styleClass;

	private String url;

	public TcMenuItem() {
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	

	public TcMenus getMenu() {
		return menu;
	}

	public void setMenu(TcMenus menu) {
		this.menu = menu;
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

}