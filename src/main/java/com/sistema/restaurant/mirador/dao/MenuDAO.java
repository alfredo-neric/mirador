package com.sistema.restaurant.mirador.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistema.restaurant.mirador.business.domain.TcMenus;

/**
 * @author Mateo
 *
 */
public interface MenuDAO {
	
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
	 * @param tcMenus
	 * @return
	 */
	Boolean existMenu(TcMenus tcMenus);
	/**
	 * @return
	 */
	List<TcMenus> orderBy();
	
	/**
	 * @param filters
	 * @return
	 */
	Integer count(Map<String, Object> filters);
	
	/**
	 * @param filters
	 * @param pageRequest
	 * @param count
	 * @param renpol
	 * @return
	 */
	Page<TcMenus> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count, Integer renpol);
	
	/**
	 * @param filters
	 * @param pageRequest
	 * @param count
	 * @return
	 */
	Page<TcMenus> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count);

}
