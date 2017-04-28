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

import com.sistema.restaurant.mirador.business.domain.TwUserAttempt;



/**
 * @author gauss
 *
 */
@Repository(value ="twUserAttemptRepository")
public interface TwUserAttemptRepository extends PagingAndSortingRepository<TwUserAttempt, Long>, QueryDslPredicateExecutor<TwUserAttempt> , Serializable {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TwUserAttempt> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TwUserAttempt> S save(S entity);
	
	<S extends TwUserAttempt> S findByUsuario( String usuario);
	
}
