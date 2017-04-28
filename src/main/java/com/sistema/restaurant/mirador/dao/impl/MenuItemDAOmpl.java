package com.sistema.restaurant.mirador.dao.impl;

import static com.sistema.restaurant.mirador.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sistema.restaurant.mirador.business.domain.TcMenuItem;
import com.sistema.restaurant.mirador.dao.MenuItemDAO;
import com.sistema.restaurant.mirador.repository.TcMenuItemRepository;

/**
 * @author Mateo
 *
 */
@Service(value = "menuItemDAO")
public class MenuItemDAOmpl implements MenuItemDAO{
	
	private static final Sort sort = new Sort(Direction.ASC, "label");
	
	@Autowired
	private TcMenuItemRepository tcMenuItemRepository;
	
	public TcMenuItemRepository getTcMenuItemRepository() {
		return tcMenuItemRepository;
	}

	public void setTcMenuItemRepository(TcMenuItemRepository tcMenuItemRepository) {
		this.tcMenuItemRepository = tcMenuItemRepository;
	}

	public List<TcMenuItem> save(TcMenuItem tcMenuItem) {
		if(this.existMenu(tcMenuItem) == Boolean.TRUE) {
			this.getTcMenuItemRepository().save(tcMenuItem);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Datos guardados correctamente");
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El submenu ya existe");
		}
		
		return this.orderBy();
	}

	public List<TcMenuItem> update(TcMenuItem tcMenuItem) {
		this.getTcMenuItemRepository().save(tcMenuItem);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se modificaron los datos correctamente");
		return this.orderBy();
	}

	public List<TcMenuItem> delete(TcMenuItem tcMenuItem) {
		this.getTcMenuItemRepository().delete(tcMenuItem);
		return this.orderBy();
	}

	public Boolean existMenu(TcMenuItem tcMenuItem) {
		return CollectionUtils.isEmpty(tcMenuItemRepository.findBylabel(tcMenuItem.getLabel()));
	}

	public List<TcMenuItem> orderBy() {
		return (List<TcMenuItem>) this.getTcMenuItemRepository().findAll(sort);
	}

}
