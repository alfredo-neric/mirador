package com.sistema.restaurant.mirador.service;

import java.util.List;

import com.sistema.restaurant.mirador.business.domain.TcMenus;

/**
 * @author Mateo
 *
 */
public interface TcMenuService {

	/**
	 * @param tcMenus
	 * @return
	 */
	List<TcMenus> save(TcMenus tcMenus);

	/**
	 * @param tcMenus
	 * @return
	 */
	List<TcMenus> update(TcMenus tcMenus);

	/**
	 * @param tcMenus
	 * @return
	 */
	List<TcMenus> delete(TcMenus tcMenus);
	
	/**
	 * @return
	 */
	List<TcMenus> orderBy();

}
