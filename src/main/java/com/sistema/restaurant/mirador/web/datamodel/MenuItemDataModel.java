package com.sistema.restaurant.mirador.web.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;

import com.sistema.restaurant.mirador.business.domain.TcMenuItem;
import com.sistema.restaurant.mirador.business.domain.TcMenus;
@ManagedBean(name  = "menuItemDataModel")
@ViewScoped
public class MenuItemDataModel extends LazyDataModel<TcMenuItem> {

	private List<TcMenuItem> listMenuItem;
	private Page<TcMenuItem> actualPage;

	private TcMenuItem lastRow;

	private Integer goToPage = 0;

	public List<TcMenuItem> getListMenuItem() {
		return listMenuItem;
	}

	public void setListMenuItem(List<TcMenuItem> listMenuItem) {
		this.listMenuItem = listMenuItem;
	}

	public Page<TcMenuItem> getActualPage() {
		return actualPage;
	}

	public void setActualPage(Page<TcMenuItem> actualPage) {
		this.actualPage = actualPage;
	}

	public TcMenuItem getLastRow() {
		return lastRow;
	}

	public void setLastRow(TcMenuItem lastRow) {
		this.lastRow = lastRow;
	}

	public Integer getGoToPage() {
		return goToPage;
	}

	public void setGoToPage(Integer goToPage) {
		this.goToPage = goToPage;
	}

	public MenuItemDataModel(List<TcMenuItem> listMenuItem) {
		this.listMenuItem = listMenuItem;
	}

	public TcMenuItem getRowData(String rowKey) {
		for (TcMenuItem item : listMenuItem) {
			if (item.getId().equals(rowKey)) {
				return item;
			}
		}
		return null;
	}

	public List<TcMenuItem> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<TcMenuItem> data = new ArrayList<TcMenuItem>();

		// filter
		for (TcMenuItem item : listMenuItem) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						String fieldValue = String.valueOf(item.getClass().getField(filterProperty).get(item));

						if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						match = false;
					}
				}
			}

			if (match) {
				data.add(item);
			}
		}

		// sort
		if (sortField != null) {
			//Collections.sort(data, new LazySorterItem(sortField, sortOrder));
			Collections.sort(data, new LazySorterItem(sortField, sortOrder));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}

}

class LazySorterItem implements Comparator<TcMenuItem> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorterItem(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(TcMenuItem o1, TcMenuItem o2) {
		try {
			Object value1 = TcMenus.class.getField(this.sortField).get(o1);
			Object value2 = TcMenus.class.getField(this.sortField).get(o2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
