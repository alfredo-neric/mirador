package com.sistema.restaurant.mirador.repository.predicate;


import com.mysema.query.types.Predicate;
import com.sistema.restaurant.mirador.business.domain.QTcUsuario;


public class TcUsuarioPredicates {

	private TcUsuarioPredicates(){}
	

	/**
	 * 
	 * @return
	 */
	public static Predicate findByUsuario(String user) {		
		
		return QTcUsuario.tcUsuario.usuario.eq(user);
		
    }
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByUsuarioContains(String user) {		
		
		return QTcUsuario.tcUsuario.usuario.containsIgnoreCase(user);
		
    }
	
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByIdRole(Long idRole) {		
		
		return QTcUsuario.tcUsuario.role.id.eq(idRole);
		
    }
}

