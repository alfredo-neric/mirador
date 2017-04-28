/**
 * 
 */
package com.sistema.restaurant.mirador.web.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.MenuModel;

import com.sistema.restaurant.mirador.security.services.MenuService;

/**
 * @author gauss
 *
 */
@ManagedBean
@SessionScoped
public class MenuMB extends AbstractMB {

	private MenuModel menuModel;

    @ManagedProperty(value="#{menuServiceImpl}")
	private MenuService menuService;
	

	@PostConstruct
	public void init(){

			this.menuModel = menuService.getMenuModelByUsuario(this.getUserDetails().getUsername());

	}

	/**
	 * @return the menuModel
	 */
	public MenuModel getMenuModel() {
		if (this.menuModel == null) {
			this.menuModel = menuService.getMenuModelByUsuario(this.getUserDetails().getUsername());
		}
		return this.menuModel;
	}

	/**
	 * @param menuModel the menuModel to set
	 */
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	/**
	 * @return the menuService
	 */
	public MenuService getMenuService() {
		return menuService;
	}

	/**
	 * @param menuService the menuService to set
	 */
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	
	
	
}
