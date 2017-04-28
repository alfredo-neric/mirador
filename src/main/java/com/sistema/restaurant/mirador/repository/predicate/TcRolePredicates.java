package com.sistema.restaurant.mirador.repository.predicate;


import com.mysema.query.types.Predicate;
import com.sistema.restaurant.mirador.business.domain.QTcRole;


public class TcRolePredicates {

	private TcRolePredicates(){}
	

	/**
	 * 
	 * @return
	 */
	public static Predicate findByRole(String nombre) {		
		
		return QTcRole.tcRole.nombre.eq(nombre);
		
    }
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByClaveContains(String clave) {		
		
		return QTcRole.tcRole.clave.containsIgnoreCase(clave);
		
    }
	
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByNombreContains(String nombre) {		
		
		return QTcRole.tcRole.nombre.containsIgnoreCase(nombre);
		
    }
	
}

