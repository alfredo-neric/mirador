/**
 * 
 */
package com.sistema.restaurant.mirador.security.services;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.sistema.restaurant.mirador.business.domain.TcMenuItem;
import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.business.domain.TcUsuario;
import com.sistema.restaurant.mirador.repository.TcUsuarioRepository;
import com.sistema.restaurant.mirador.repository.predicate.TcUsuarioPredicates;


@Repository
public class MenuServiceImpl implements MenuService {

	@Autowired
	private TcUsuarioRepository tcUsuarioRepository;
	
	
	
	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}



	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}



	/* (non-Javadoc)
	 * @see com.uer.sistema.web.security.services.MenuService#getMenuModelByPerfil(java.lang.String)
	 */
	public MenuModel getMenuModelByUsuario(String user) {
		
		
		final TcUsuario usuario = tcUsuarioRepository.findOne(TcUsuarioPredicates.findByUsuario(user));
		final MenuModel menuModel = new DefaultMenuModel();
		
		for (TcMenus tcMenu: usuario.getRole().getMenus()) {
			DefaultSubMenu  submenu = new DefaultSubMenu(tcMenu.getLabel());
			for( TcMenuItem menuItem :  tcMenu.getMenuItems()  ){
				DefaultMenuItem item = new DefaultMenuItem(menuItem.getLabel());
				item.setUrl(menuItem.getUrl());
				submenu.addElement(item);
				
			}
			menuModel.addElement(submenu);
			
		}
		
		return menuModel;
	}

}
