package com.sistema.restaurant.mirador.web.bean;

import static com.sistema.restaurant.mirador.util.UtilFront.generateNotificationFront;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.service.TcMenuService;
import com.sistema.restaurant.mirador.web.datamodel.MenuDataModel;
import com.sistema.restaurant.mirador.web.datamodel.TcMenuDataModel;

@ManagedBean(name = "tcMenuMB")
@ViewScoped
public class TcMenuMB {

	/**
	 * Habilitar el modo de edicion
	 */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('span.ui-icon-pencil').eq(";
	/**
	 * Habilitar el modo de edicion
	 */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";

	protected static final String FOCUS_BY_ROWID = "PrimeFaces.focus('formMenus:objects:%1$s:label');";

	private Boolean modify;

	private TcMenus selectTcMenu;

	private List<TcMenus> listTcMenus;

	private MenuDataModel menuDataModel;

	@ManagedProperty("#{tcMenuService}")
	private TcMenuService tcMenuService;

	public Boolean getModify() {
		return modify;
	}

	public void setModify(Boolean modify) {
		this.modify = modify;
	}

	public TcMenus getSelectTcMenu() {
		return selectTcMenu;
	}

	public void setSelectTcMenu(TcMenus selectTcMenu) {
		this.selectTcMenu = selectTcMenu;
	}

	public List<TcMenus> getListTcMenus() {
		return listTcMenus;
	}

	public void setListTcMenus(List<TcMenus> listTcMenus) {
		this.listTcMenus = listTcMenus;
	}

	public MenuDataModel getMenuDataModel() {
		return menuDataModel;
	}

	public void setMenuDataModel(MenuDataModel menuDataModel) {
		this.menuDataModel = menuDataModel;
	}

	public TcMenuService getTcMenuService() {
		return tcMenuService;
	}

	public void setTcMenuService(TcMenuService tcMenuService) {
		this.tcMenuService = tcMenuService;
	}

	@PostConstruct
	public void init() {
		listTcMenus = new ArrayList<TcMenus>();
		listTcMenus = tcMenuService.orderBy();
		menuDataModel = new MenuDataModel(listTcMenus);
		//
	}

	public void onRowEditInit(RowEditEvent event) {
		if (null != event.getObject()) {
			TcMenus pl = (TcMenus) event.getObject();
			if (null != pl.getId() && pl.getId() != 0) {
				this.setModify(Boolean.TRUE);
			}
		}
	}

	public void onRowEdit(RowEditEvent event) {
		selectTcMenu = (TcMenus) event.getObject();
		if (this.getModify() == Boolean.TRUE) {
			listTcMenus = tcMenuService.update(selectTcMenu);
		} else {
			listTcMenus = tcMenuService.save(selectTcMenu);
			this.addElement();
			RequestContext.getCurrentInstance().execute("jQuery('#formMenus\\\\:hiddenUpdate').click();");
		}

		this.getMenuDataModel().setListMenu(listTcMenus);

	}

	public void onRowCancel(RowEditEvent event) {
		selectTcMenu = (TcMenus) event.getObject();
		if (null == selectTcMenu.getId()) {
			this.getMenuDataModel().getListMenu().remove(selectTcMenu.getIndex());
		}

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

	}

	public void addElement() {
		selectTcMenu = new TcMenus();

		listTcMenus.add(selectTcMenu);
		this.getMenuDataModel().setListMenu(listTcMenus);
		activateRowEdit(this.getMenuDataModel().getListMenu().size() - 1);
		RequestContext.getCurrentInstance().execute(
				"PF('menusWdg').paginator.setPage(" + this.getPage(this.getMenuDataModel().getListMenu()) + ");");
		if (listTcMenus.size() == 10) {
			RequestContext.getCurrentInstance().execute("jQuery('#formMenus\\\\:hiddenUpdate').click();");
		}

	}

	public void deleteRole(Integer index) {
		selectTcMenu = this.getMenuDataModel().getListMenu().get(index);
		if (null != selectTcMenu.getId() && 0 != selectTcMenu.getId()) {
			this.getMenuDataModel().setListMenu(tcMenuService.delete(selectTcMenu));
		}
	}

	public void activateRowEdit(final int index) {
		// LOGGER.info(":: Cerrar cuadro de dialogo ");
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	public int getPage(List<?> lis) {
		int rows = lis.size();
		int de = 1;
		double maxRow = 10;
		double pageActua = (rows * de) / maxRow;
		String page = "" + pageActua;
		return Integer.parseInt(this.getValue(page)[0]);
	}

	public String[] getValue(String data) {
		return data.split("\\.");
	}
}
