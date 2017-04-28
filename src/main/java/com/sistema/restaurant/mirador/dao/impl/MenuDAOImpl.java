package com.sistema.restaurant.mirador.dao.impl;

import static com.sistema.restaurant.mirador.util.UtilFront.generateNotificationFront;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.dao.MenuDAO;
import com.sistema.restaurant.mirador.repository.TcMenuRepository;

/**
 * @author Mateo
 *
 */
@Repository
public class MenuDAOImpl implements MenuDAO {

	private static final Sort sort = new Sort(Direction.ASC, "label");

	@Autowired
	private TcMenuRepository tcMenuRepository;

	public TcMenuRepository getTcMenuRepository() {
		return tcMenuRepository;
	}

	public void setTcMenuRepository(TcMenuRepository tcMenuRepository) {
		this.tcMenuRepository = tcMenuRepository;
	}

	public List<TcMenus> save(TcMenus tcMenus) {
		if (this.existMenu(tcMenus) == Boolean.TRUE) {
			this.getTcMenuRepository().save(tcMenus);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "La informacion se guardo correctamente");
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El menu ya existe en la base");
		}
		return this.orderBy();
	}

	public List<TcMenus> update(TcMenus tcMenus) {
		this.getTcMenuRepository().save(tcMenus);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se edito la iformacion");
		return this.orderBy();
	}

	public List<TcMenus> delete(TcMenus tcMenus) {
		this.getTcMenuRepository().delete(tcMenus);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la iformacion");
		return this.orderBy();
	}

	public Boolean existMenu(TcMenus tcMenus) {
		return CollectionUtils.isEmpty(tcMenuRepository.findBylabel(tcMenus.getLabel()));
	}

	public List<TcMenus> orderBy() {
		return (List<TcMenus>) tcMenuRepository.findAll(sort);
	}

	public Integer count(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public Page<TcMenus> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count,
			Integer renpol) {
		Page<TcMenus> searchResultPage = null;
		//searchResultPage = tcMenuRepository.findAllByFilters(filters, pageRequest, count);
		
		return searchResultPage;
	}

	@Transactional(readOnly = true)
	public Page<TcMenus> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count) {
		Page<TcMenus> searchResultPage = null;
		//searchResultPage = tcMenuRepository.findBy
		return searchResultPage;
	}

}
