/**
 * 
 */
package com.sistema.restaurant.mirador.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.restaurant.mirador.business.domain.TcMenuItem;

/**
 * @author gauss
 *
 */
@Repository(value = "tcMenuItemRepository")
public interface TcMenuItemRepository
		extends PagingAndSortingRepository<TcMenuItem, Long>, QueryDslPredicateExecutor<TcMenuItem>, Serializable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcMenuItem> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcMenuItem> S save(S entity);

	/**
	 * @param label
	 * @return
	 */
	List<TcMenuItem> findBylabel(String label);

}
