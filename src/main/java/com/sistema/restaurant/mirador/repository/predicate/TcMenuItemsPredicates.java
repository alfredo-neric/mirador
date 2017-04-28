package com.sistema.restaurant.mirador.repository.predicate;


import com.mysema.query.types.Predicate;
import com.sistema.restaurant.mirador.business.domain.QTcMenuItem;


public class TcMenuItemsPredicates {

	private TcMenuItemsPredicates(){}
	
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByLabelContains(String label) {		
		
		return QTcMenuItem.tcMenuItem.label.containsIgnoreCase(label);
		
    }
	
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByUrlContains(String url) {		
		
		return QTcMenuItem.tcMenuItem.url.containsIgnoreCase(url);
		
    }
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByStyleContains(String styleclass) {		
		
		return QTcMenuItem.tcMenuItem.styleClass.containsIgnoreCase(styleclass);
		
    }
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByIconContains(String icon) {		
		
		return QTcMenuItem.tcMenuItem.icon.containsIgnoreCase(icon);
		
    }
	
	/**
	 * 
	 * @return
	 */
	public static Predicate findByIdMenu(Long idMenu) {		
		
		return QTcMenuItem.tcMenuItem.menu.id.eq(idMenu);
		
    }
	
	
}

