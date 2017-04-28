package com.sistema.restaurant.mirador.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.restaurant.mirador.business.domain.TcMenuItem;
import com.sistema.restaurant.mirador.dao.MenuItemDAO;
import com.sistema.restaurant.mirador.service.TcMenuItemService;

/**
 * @author Mateo
 *
 */
@Service(value = "tcMenuItemService")
public class TcMenuItemServiceImpl implements TcMenuItemService {
	
	@Autowired
	private MenuItemDAO menuItemDAO;

	public MenuItemDAO getMenuItemDAO() {
		return menuItemDAO;
	}

	/**
	 * @param menuItemDAO
	 */
	public void setMenuItemDAO(MenuItemDAO menuItemDAO) {
		this.menuItemDAO = menuItemDAO;
	}

	/* (non-Javadoc)
	 * @see com.sistema.dulceria.marisol.service.TcMenuItemService#save(com.sistema.duceria.marisol.business.domain.TcMenuItem)
	 */
	public List<TcMenuItem> save(TcMenuItem tcMenuItem) {
		return this.menuItemDAO.save(tcMenuItem);
	}

	/* (non-Javadoc)
	 * @see com.sistema.dulceria.marisol.service.TcMenuItemService#update(com.sistema.duceria.marisol.business.domain.TcMenuItem)
	 */
	public List<TcMenuItem> update(TcMenuItem tcMenuItem) {
		return this.menuItemDAO.update(tcMenuItem);
	}

	/* (non-Javadoc)
	 * @see com.sistema.dulceria.marisol.service.TcMenuItemService#delete(com.sistema.duceria.marisol.business.domain.TcMenuItem)
	 */
	public List<TcMenuItem> delete(TcMenuItem tcMenuItem) {
		return this.menuItemDAO.delete(tcMenuItem);
	}

	/* (non-Javadoc)
	 * @see com.sistema.dulceria.marisol.service.TcMenuItemService#existMenu(com.sistema.duceria.marisol.business.domain.TcMenuItem)
	 */
	public Boolean existMenu(TcMenuItem tcMenuItem) {
		return this.menuItemDAO.existMenu(tcMenuItem);
	}

	/* (non-Javadoc)
	 * @see com.sistema.dulceria.marisol.service.TcMenuItemService#orderBy()
	 */
	public List<TcMenuItem> orderBy() {
		return this.menuItemDAO.orderBy();
	}

}
