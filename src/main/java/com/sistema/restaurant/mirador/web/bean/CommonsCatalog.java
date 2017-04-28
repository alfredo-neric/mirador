package com.sistema.restaurant.mirador.web.bean;

import static com.sistema.restaurant.mirador.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.sistema.restaurant.mirador.business.domain.AbstractEntity;
import com.sistema.restaurant.mirador.business.domain.TcStatus;
import com.sistema.restaurant.mirador.service.CommonsService;
import com.sistema.restaurant.mirador.web.datamodel.DataModelGeneric;

@ManagedBean
@ViewScoped
public class CommonsCatalog<T extends AbstractEntity> extends AbstractMB {

	private static final Boolean TRUE = Boolean.TRUE;
	private static final Boolean FALSE = Boolean.FALSE;
	protected Boolean bUpdate;
	protected Boolean bEdit;

	protected T select;
	protected List<T> list;
	protected DataModelGeneric<T> dataModelGeneric;

	@ManagedProperty("#{commonsService}")
	protected CommonsService<T> commonsService;

	public Boolean getbUpdate() {
		return bUpdate;
	}

	public void setbUpdate(Boolean bUpdate) {
		this.bUpdate = bUpdate;
	}

	public Boolean getbEdit() {
		return bEdit;
	}

	public void setbEdit(Boolean bEdit) {
		this.bEdit = bEdit;
	}

	public T getSelect() {
		return select;
	}

	public void setSelect(T select) {
		this.select = select;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public DataModelGeneric<T> getDataModelGeneric() {
		return dataModelGeneric;
	}

	public void setDataModelGeneric(DataModelGeneric<T> dataModelGeneric) {
		this.dataModelGeneric = dataModelGeneric;
	}

	public CommonsService<T> getCommonsService() {
		return commonsService;
	}

	public void setCommonsService(CommonsService<T> commonsService) {
		this.commonsService = commonsService;
	}

	public void findInit() {
		list = this.getCommonsService().findAll();
		dataModelGeneric = new DataModelGeneric<>(list);
	}

	public void addElement() {
		this.getList().add(this.getCommonsService().add());
		this.getDataModelGeneric().setListT(this.getList());
		this.getCommonsService().activeRowEdit(this.getDataModelGeneric().getListT().size() - 1);
	}

	public void onRowEdit(RowEditEvent event) {
		select = (T) event.getObject();
		Integer lastIndex = this.getDataModelGeneric().getListT().size();
		if (TRUE == this.getbUpdate()) {
			this.getCommonsService().update(select);
		} else {
			this.getCommonsService().save(select);
		}

		if (lastIndex != this.getDataModelGeneric().getListT().size()) {
			this.getCommonsService().activeRowEdit(this.getDataModelGeneric().getListT().size() - 1);
		} else {
			list = this.getCommonsService().findAll();
		}
	}

	public void onRowEditInit(RowEditEvent event) {
		if (null != event.getObject()) {
			T pl = (T) event.getObject();
			if (null != pl.getId() && pl.getId() != 0) {
				this.setbUpdate(TRUE);

			}
		}
	}

	public void onRowCancel(RowEditEvent event) {
		list = this.getCommonsService().findAll();
		this.getDataModelGeneric().setListT(list);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

}
