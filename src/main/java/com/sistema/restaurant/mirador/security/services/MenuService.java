/**
 * 
 */
package com.sistema.restaurant.mirador.security.services;

import org.primefaces.model.menu.MenuModel;

/**
 * @author gauss
 *
 */
@FunctionalInterface
public interface MenuService {
	
	 /**
     * Recupera el panel de menu, en base al usuario
     * @param perfil
     * @return MenuModel
     * @throws Exception
     */
    MenuModel getMenuModelByUsuario(String usuario);

}
