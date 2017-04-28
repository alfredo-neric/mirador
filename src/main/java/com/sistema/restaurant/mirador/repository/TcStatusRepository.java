package com.sistema.restaurant.mirador.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.restaurant.mirador.business.domain.TcStatus;

@Repository(value = "tcStatusRepository")
public interface TcStatusRepository
		extends PagingAndSortingRepository<TcStatus, Long>, QueryDslPredicateExecutor<TcStatus>, Serializable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcStatus> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcStatus> S save(S entity);

}
