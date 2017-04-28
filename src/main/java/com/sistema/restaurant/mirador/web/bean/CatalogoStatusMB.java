package com.sistema.restaurant.mirador.web.bean;

import static com.sistema.restaurant.mirador.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.sistema.restaurant.mirador.business.domain.TcStatus;
import com.sistema.restaurant.mirador.service.TcStatusService;
import com.sistema.restaurant.mirador.web.datamodel.DataModelGeneric;

@ManagedBean(name = "catalogoStatusMB")
@ViewScoped
public class CatalogoStatusMB {

	private static final Boolean TRUE = Boolean.TRUE;
	private static final Boolean FAlSE = Boolean.FALSE;

	private Boolean bEdit;
	private Boolean bUpdate;

	private TcStatus select;
	private String descripcion;
	private List<TcStatus> listStatus;
	private DataModelGeneric<TcStatus> dataModelStatus;

	@ManagedProperty("#{tcStatusService}")
	private TcStatusService tcStatusService;

	public Boolean getbEdit() {
		return bEdit;
	}

	public void setbEdit(Boolean bEdit) {
		this.bEdit = bEdit;
	}

	public Boolean getbUpdate() {
		return bUpdate;
	}

	public void setbUpdate(Boolean bUpdate) {
		this.bUpdate = bUpdate;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TcStatus getSelect() {
		return select;
	}

	public void setSelect(TcStatus select) {
		this.select = select;
	}

	public List<TcStatus> getListStatus() {
		return listStatus;
	}

	public void setListStatus(List<TcStatus> listStatus) {
		this.listStatus = listStatus;
	}

	public DataModelGeneric<TcStatus> getDataModelStatus() {
		return dataModelStatus;
	}

	public void setDataModelStatus(DataModelGeneric<TcStatus> dataModelStatus) {
		this.dataModelStatus = dataModelStatus;
	}

	public TcStatusService getTcStatusService() {
		return tcStatusService;
	}

	public void setTcStatusService(TcStatusService tcStatusService) {
		this.tcStatusService = tcStatusService;
	}

	@PostConstruct
	public void init() {
		this.findAll();
	}

	public void findAll() {
		listStatus = this.tcStatusService.orderBy();
		dataModelStatus = new DataModelGeneric<TcStatus>(listStatus);
	}

	public void addElement() {
		this.getListStatus().add(this.getTcStatusService().add());
		this.getDataModelStatus().setListT(this.getListStatus());
		this.getTcStatusService().activeRowEdit(this.getDataModelStatus().getListT().size() - 1);
		if (this.getDataModelStatus().getListT().size() > 5) {

			RequestContext.getCurrentInstance().execute("PF('statusWdg').paginator.setPage("
					+ this.getTcStatusService().getPage(this.getDataModelStatus().getListT()) + ");");
			this.getTcStatusService().activeRowEdit(this.getDataModelStatus().getListT().size() - 1);
		}

	}

	/**
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		select = (TcStatus) event.getObject();
		Integer lastIndex = this.getDataModelStatus().getListT().size();
		if (TRUE == this.getbUpdate()) {
			this.getTcStatusService().update(select);
		} else {
			this.getTcStatusService().save(select);
		}

		if (lastIndex != this.getDataModelStatus().getListT().size()) {
			this.getTcStatusService().activeRowEdit(this.getDataModelStatus().getListT().size() - 1);
		} else {
			listStatus = this.getTcStatusService().orderBy();
		}
	}

	public void onRowEditInit(RowEditEvent event) {
		if (null != event.getObject()) {
			TcStatus pl = (TcStatus) event.getObject();
			if (null != pl.getId() && pl.getId() != 0) {
				this.setbUpdate(TRUE);

			}
		}
	}

	public void onRowCancel(RowEditEvent event) {
		listStatus = this.getTcStatusService().orderBy();
		this.getDataModelStatus().setListT(listStatus);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	public void delete(Integer index) {
		select = listStatus.get(index);
		this.getTcStatusService().delete(select);
		listStatus = this.getTcStatusService().orderBy();
		this.getDataModelStatus().setListT(listStatus);
	}

	public void findStatus() {
		listStatus = this.getTcStatusService().findStatus(descripcion);
		this.getDataModelStatus().setListT(listStatus);
	}
}
