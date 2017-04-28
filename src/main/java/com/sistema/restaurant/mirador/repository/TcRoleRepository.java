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

import com.sistema.restaurant.mirador.business.domain.TcRole;


/**
 * @author gauss
 *
 */
@Repository(value = "tcRoleRepository")
public interface TcRoleRepository
		extends PagingAndSortingRepository<TcRole, Long>, QueryDslPredicateExecutor<TcRole>, Serializable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */

	List<TcRole> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcRole> S save(S entity);

//	@Transactional(timeout = 10)
//	@Query(value = "insert into #{#entityName} (clave,nombre) VALUES(:clave,:nombre)", nativeQuery = true)
//	int insertnewRol(@Param("clave") String clave, @Param("nombre") String nombre);

	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query(value = "insert into TR_ROLES_MENUS VALUES(:idRol,:idMenu)", nativeQuery = true)
	int insertnewRolMenu(@Param("idRol") Long idRol, @Param("idMenu") Long idMenu);

	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from TR_ROLES_MENUS where ID_ROL=:idRol", nativeQuery = true)
	int deletenewRolMenu(@Param("idRol") Long idRol);
	
	/**
	 * @param clave
	 * @return
	 */
	List<TcRole> findByclave(String clave);

}
