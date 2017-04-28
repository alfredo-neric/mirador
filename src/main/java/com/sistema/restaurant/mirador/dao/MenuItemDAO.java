package com.sistema.restaurant.mirador.dao;

import java.util.List;

import com.sistema.restaurant.mirador.business.domain.TcMenuItem;

/**
 * @author Mateo
 *
 */
public interface MenuItemDAO {
	
	/**
	 * @param tcMenus
	 * @return
	 */
	List<TcMenuItem> save(TcMenuItem tcMenuItem);
	/**
	 * @param tcMenus
	 * @return
	 */
	List<TcMenuItem> update(TcMenuItem tcMenuItem);
	/**
	 * @param tcMenus
	 * @return
	 */
	List<TcMenuItem> delete(TcMenuItem tcMenuItem);
	/**
	 * @param tcMenus
	 * @return
	 */
	Boolean existMenu(TcMenuItem tcMenuItem);
	/**
	 * @return
	 */
	List<TcMenuItem> orderBy();

}
