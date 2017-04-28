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

import com.sistema.restaurant.mirador.business.domain.TcMenus;


/**
 * @author Mateo
 *
 */
@Repository(value = "tcMenuRepository")
public interface TcMenuRepository extends PagingAndSortingRepository<TcMenus, Long>, QueryDslPredicateExecutor<TcMenus> , Serializable {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	
	List<TcMenus> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcMenus> S save(S entity);
	
	/**
	 * @param label
	 * @return
	 */
	List<TcMenus> findBylabel(String label);
	
}
