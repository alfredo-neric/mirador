package com.sistema.restaurant.mirador.service;

import java.util.List;

import com.sistema.restaurant.mirador.business.domain.AbstractEntity;

/**
 * @author Mateo
 *
 */

public interface CommonsService<T extends AbstractEntity> {
	/**
	 * @param T
	 * @return
	 */
	List<T> save(T entity);

	/**
	 * @param T
	 * @return
	 */
	List<T> update(T entity);

	/**
	 * @param T
	 * @return
	 */
	List<T> delete(T entity);

	/**
	 * @return
	 */
	T add();

	/**
	 * @return
	 */
	List<T> findAll();

	/**
	 * @param index
	 */
	void activeRowEdit(int index);
}
