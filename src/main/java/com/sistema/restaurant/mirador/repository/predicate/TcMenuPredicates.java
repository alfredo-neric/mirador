package com.sistema.restaurant.mirador.repository.predicate;


import com.mysema.query.types.Predicate;
import com.sistema.restaurant.mirador.business.domain.QTcMenus;


public class TcMenuPredicates {

	private TcMenuPredicates(){}
	
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByLabelContains(String label) {		
		
		return QTcMenus.tcMenus.label.containsIgnoreCase(label);
		
    }
	
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByUrlContains(String url) {		
		
		return QTcMenus.tcMenus.url.containsIgnoreCase(url);
		
    }
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByStyleContains(String styleclass) {		
		
		return QTcMenus.tcMenus.styleClass.containsIgnoreCase(styleclass);
		
    }
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByIconContains(String icon) {		
		
		return QTcMenus.tcMenus.icon.containsIgnoreCase(icon);
		
    }
	
}

