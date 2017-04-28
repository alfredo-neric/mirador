package com.sistema.restaurant.mirador.dao.impl;

import static com.sistema.restaurant.mirador.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sistema.restaurant.mirador.business.domain.AbstractEntity;
import com.sistema.restaurant.mirador.dao.CommonsDAO;
import com.sistema.restaurant.mirador.repository.CommonsRepository;

@Repository
public class CommonsDAOImpl<T extends AbstractEntity> implements CommonsDAO<T> {

	private static final Boolean TRUE = Boolean.TRUE;
	private static final Boolean FALSE = Boolean.FALSE;

	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('#form1 span.ui-icon-pencil').eq(";
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";

	@Autowired
	private CommonsRepository<T> commonsRepository;

	public CommonsRepository<T> getCommonsRepository() {
		return commonsRepository;
	}

	public void setCommonsRepository(CommonsRepository<T> commonsRepository) {
		this.commonsRepository = commonsRepository;
	}

	@Override
	public List<T> save(T entity) {
		this.getCommonsRepository().save(entity);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Datos guardados correctamente");
		return this.getCommonsRepository().findAll();
	}

	@Override
	public List<T> update(T entity) {
		this.getCommonsRepository().save(entity);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Datos Editados correctamente");
		return this.getCommonsRepository().findAll();
	}

	@Override
	public List<T> delete(T entity) {
		this.getCommonsRepository().delete(entity);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Datos Eliminados correctamente");
		return this.getCommonsRepository().findAll();
	}

	@Override
	public T add() {
		return null ;
	}

	@Override
	public List<T> findAll() {
		return this.getCommonsRepository().findAll();
	}

	@Override
	public void activeRoeEdit(int index) {
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		// text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());

	}

}
