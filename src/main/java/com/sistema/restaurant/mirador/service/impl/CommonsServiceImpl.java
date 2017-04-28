package com.sistema.restaurant.mirador.service.impl;

import java.util.List;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sistema.restaurant.mirador.business.domain.AbstractEntity;
import com.sistema.restaurant.mirador.dao.CommonsDAO;
import com.sistema.restaurant.mirador.service.CommonsService;

/**
 * @author Mateo
 *
 * @param <T>
 */
@Service(value = "commonsService")
public class CommonsServiceImpl<T extends AbstractEntity> implements CommonsService<T> {

	@Autowired
	private CommonsDAO<T> commonsDAO;

	public CommonsDAO<T> getCommonsDAO() {
		return commonsDAO;
	}

	public void setCommonsDAO(CommonsDAO<T> commonsDAO) {
		this.commonsDAO = commonsDAO;
	}

	@Override
	public List<T> save(T entity) {
		return this.getCommonsDAO().save(entity);
	}

	@Override
	public List<T> update(T entity) {
		return this.getCommonsDAO().update(entity);
	}

	@Override
	public List<T> delete(T entity) {
		return this.getCommonsDAO().delete(entity);
	}

	@Override
	public T add() {
		return this.getCommonsDAO().add();
	}

	@Override
	public List<T> findAll() {

		return this.getCommonsDAO().findAll();
	}

	@Override
	public void activeRowEdit(int index) {
		this.getCommonsDAO().activeRoeEdit(index);
	}

}
