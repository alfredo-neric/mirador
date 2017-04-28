package com.sistema.restaurant.mirador.dao.impl;

import static com.sistema.restaurant.mirador.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.sistema.restaurant.mirador.business.domain.TcStatus;
import com.sistema.restaurant.mirador.dao.TcStatusDAO;
import com.sistema.restaurant.mirador.repository.TcStatusRepository;
import com.sistema.restaurant.mirador.repository.predicate.TcStatusPredicate;

/**
 * @author Mateo
 *
 */
@Repository
public class TcStatusDAOImpl implements TcStatusDAO {

	private static final Boolean TRUE = Boolean.TRUE;
	private static final Boolean FALSE = Boolean.FALSE;
	private static final Sort SORT = new Sort(Direction.ASC, "idStatus");

	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('#form1 span.ui-icon-pencil').eq(";
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:status:%1$s:descripcion');";

	@Autowired
	private TcStatusRepository tcStatusRepository;

	public TcStatusRepository getTcStatusRepository() {
		return tcStatusRepository;
	}

	public void setTcStatusRepository(TcStatusRepository tcStatusRepository) {
		this.tcStatusRepository = tcStatusRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sistema.restaurant.mirador.dao.StatusDAO#save(com.sistema.restaurant.
	 * mirador.business.domain.TcStatus)
	 */
	@Override
	public List<TcStatus> save(TcStatus tcStatus) {
		if (TRUE == this.validateExist(tcStatus)) {
			this.tcStatusRepository.save(tcStatus);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Datos guardados correctamente");
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El status ya existe");
		}

		return this.orderBy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sistema.restaurant.mirador.dao.StatusDAO#update(com.sistema.
	 * restaurant.mirador.business.domain.TcStatus)
	 */
	@Override
	public List<TcStatus> update(TcStatus tcStatus) {
		this.tcStatusRepository.save(tcStatus);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Datos guardados correctamente");
		return this.orderBy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sistema.restaurant.mirador.dao.StatusDAO#delete(com.sistema.
	 * restaurant.mirador.business.domain.TcStatus)
	 */
	@Override
	public List<TcStatus> delete(TcStatus tcStatus) {
		this.tcStatusRepository.delete(tcStatus);
		return this.orderBy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sistema.restaurant.mirador.dao.StatusDAO#add()
	 */
	@Override
	public TcStatus add() {
		return new TcStatus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sistema.restaurant.mirador.dao.StatusDAO#orderBy()
	 */
	@Override
	public List<TcStatus> orderBy() {
		return (List<TcStatus>) this.tcStatusRepository.findAll(SORT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sistema.restaurant.mirador.dao.StatusDAO#validateExist(com.sistema.
	 * restaurant.mirador.business.domain.TcStatus)
	 */
	@Override
	public Boolean validateExist(TcStatus tcStatus) {
		return null == this.tcStatusRepository.findOne(TcStatusPredicate.findBy(tcStatus.getIdStatus())) ? TRUE : FALSE;
	}

	@Override
	public void activeRowEdit(int index) {
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	@Override
	public Integer getPage(List<TcStatus> list) {
		int rows = list.size();
		int de = 1;
		double maxRow = 5;
		double pageActual = ((rows * de) / maxRow) - 1;
		String page = "" + Math.ceil(pageActual);
		return Integer.parseInt(this.getValue(page)[0]);
	}

	public String[] getValue(String data) {
		return data.split("\\.");
	}

	@Override
	public List<TcStatus> findStatus(String describe) {
		return (List<TcStatus>) this.tcStatusRepository.findAll(TcStatusPredicate.findByDescripcion(describe));
	}

}
