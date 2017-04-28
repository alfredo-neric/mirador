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

import com.sistema.restaurant.mirador.business.domain.TcMenuItem;
import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.service.TcMenuItemService;
import com.sistema.restaurant.mirador.web.datamodel.MenuItemDataModel;

@ManagedBean(name = "menuItemMB")
@ViewScoped
public class MenuItemMB {

	/**
	 * Habilitar el modo de edicion
	 */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('span.ui-icon-pencil').eq(";
	/**
	 * Habilitar el modo de edicion
	 */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";

	protected static final String FOCUS_BY_ROWID = "PrimeFaces.focus('formItems:objects:%1$s:label');";

	private Boolean modify;

	private TcMenuItem selectItem;

	private List<TcMenuItem> listItem;

	private MenuItemDataModel menuItemDataModel;

	@ManagedProperty("#{tcMenuItemService}")
	private TcMenuItemService tcMenuItemService;

	public Boolean getModify() {
		return modify;
	}

	public void setModify(Boolean modify) {
		this.modify = modify;
	}

	public TcMenuItem getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(TcMenuItem selectItem) {
		this.selectItem = selectItem;
	}

	public List<TcMenuItem> getListItem() {
		return listItem;
	}

	public void setListItem(List<TcMenuItem> listItem) {
		this.listItem = listItem;
	}

	public MenuItemDataModel getMenuItemDataModel() {
		return menuItemDataModel;
	}

	public void setMenuItemDataModel(MenuItemDataModel menuItemDataModel) {
		this.menuItemDataModel = menuItemDataModel;
	}

	public TcMenuItemService getTcMenuItemService() {
		return tcMenuItemService;
	}

	public void setTcMenuItemService(TcMenuItemService tcMenuItemService) {
		this.tcMenuItemService = tcMenuItemService;
	}

	@PostConstruct
	public void init() {
		listItem = new ArrayList<TcMenuItem>();
		listItem = tcMenuItemService.orderBy();
		menuItemDataModel = new MenuItemDataModel(listItem);
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
		selectItem = (TcMenuItem) event.getObject();
		if (this.getModify() == Boolean.TRUE) {
			listItem = tcMenuItemService.update(selectItem);
		} else {
			listItem = tcMenuItemService.save(selectItem);
			RequestContext.getCurrentInstance().execute("jQuery('#formMenus\\\\:hiddenUpdate').click();");
		}

		this.getMenuItemDataModel().setListMenuItem(listItem);

	}

	public void onRowCancel(RowEditEvent event) {
		selectItem = (TcMenuItem) event.getObject();
		if (null == selectItem.getId()) {
			this.getMenuItemDataModel().getListMenuItem().remove(selectItem.getIndex());
		}

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

	}

	public void addElement() {
		selectItem = new TcMenuItem();

		listItem.add(selectItem);
		this.getMenuItemDataModel().setListMenuItem(listItem);
		activateRowEdit(this.getMenuItemDataModel().getListMenuItem().size() - 1);
		RequestContext.getCurrentInstance().execute("PF('menusWdg').paginator.setPage("
				+ this.getPage(this.getMenuItemDataModel().getListMenuItem()) + ");");
		if (listItem.size() == 10) {
			RequestContext.getCurrentInstance().execute("jQuery('#formMenus\\\\:hiddenUpdate').click();");
		}

	}

	public void deleteRole(Integer index) {
		selectItem = this.getMenuItemDataModel().getListMenuItem().get(index);
		if (null != selectItem.getId() && 0 != selectItem.getId()) {
			this.getMenuItemDataModel().setListMenuItem(tcMenuItemService.delete(selectItem));
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
