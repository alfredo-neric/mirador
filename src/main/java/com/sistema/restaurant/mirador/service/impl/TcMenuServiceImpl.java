package com.sistema.restaurant.mirador.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.dao.MenuDAO;
import com.sistema.restaurant.mirador.service.TcMenuService;

/**
 * @author Mateo
 *
 */
@Service(value = "tcMenuService")
public class TcMenuServiceImpl implements TcMenuService {
	
	@Autowired
	private MenuDAO menuDAO;
	
	

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	/**
	 * @param menuDAO
	 */
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	/* (non-Javadoc)
	 * @see com.sistema.dulceria.marisol.service.TcMenuService#save(com.sistema.duceria.marisol.business.domain.TcMenus)
	 */
	public List<TcMenus> save(TcMenus tcMenus) {
		return this.menuDAO.save(tcMenus);
	}

	/* (non-Javadoc)
	 * @see com.sistema.dulceria.marisol.service.TcMenuService#update(com.sistema.duceria.marisol.business.domain.TcMenus)
	 */
	public List<TcMenus> update(TcMenus tcMenus) {
		return this.menuDAO.update(tcMenus);
	}

	/* (non-Javadoc)
	 * @see com.sistema.dulceria.marisol.service.TcMenuService#delete(com.sistema.duceria.marisol.business.domain.TcMenus)
	 */
	public List<TcMenus> delete(TcMenus tcMenus) {
		return this.menuDAO.delete(tcMenus);
	}

	public List<TcMenus> orderBy() {
		return this.menuDAO.orderBy();
	}

}
