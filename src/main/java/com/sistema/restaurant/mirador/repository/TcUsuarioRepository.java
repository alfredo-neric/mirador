/**
 * 
 */
package com.sistema.restaurant.mirador.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.restaurant.mirador.business.domain.TcUsuario;



/**
 * @author Mateo
 *
 */
@Repository(value = "tcUsuarioRepository")
public interface TcUsuarioRepository
		extends PagingAndSortingRepository<TcUsuario, Long>, QueryDslPredicateExecutor<TcUsuario>, Serializable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */

	List<TcUsuario> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcUsuario> S save(S entity);

	<S extends TcUsuario> S findByUsuario(String usuario);

	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} u set u.accountnonlocked = :accountnonlocked where u.id = :id")
	int setAccountNonLockedFor(@Param("accountnonlocked") Boolean accountnonlocked, @Param("id") Long id);

	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} u set u.contrasenia = :contrasenia where u.id = :id")
	int setContraseniaFor(@Param("contrasenia") String accountnonlocked, @Param("id") Long id);

	/**
	 * @param usuario
	 * @return
	 */
	List<TcUsuario> findByusuario(String usuario);

}
