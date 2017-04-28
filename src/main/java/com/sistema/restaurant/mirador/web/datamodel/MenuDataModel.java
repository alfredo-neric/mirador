package com.sistema.restaurant.mirador.web.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;

import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.repository.TcMenuRepository;
import com.sistema.restaurant.mirador.service.TcMenuService;

/**
 * @author Mateo
 *
 */
@ManagedBean(name = "menuDataModel")
@ViewScoped
public class MenuDataModel extends LazyDataModel<TcMenus> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Integer CUURENT_PAGE = 0;

	public static final Integer NEXT_PAGE = 2;

	public static final Integer PREV_PAGE = 1;
	/**
	 * Repositorio de TcMenus.
	 */
	@ManagedProperty(value = "#{tcMenuService}")
	private TcMenuService tcMenuService;

	@ManagedProperty(value = "#{tcMenuRepository}")
	private TcMenuRepository tcMenuRepository;

	private List<TcMenus> listMenu;

	private Boolean insert = Boolean.FALSE;

	private Boolean reset = Boolean.FALSE;

	private TcMenus selected;

	private Integer count;

	private List<TcMenus> listTcMenus;

	private Integer lastPage;

	private Long totalElements;

	private Page<TcMenus> actualPage;

	private TcMenus lastRow;

	private Integer goToPage = 0;

	public MenuDataModel(List<TcMenus> listMenu) {
		this.listMenu = listMenu;
	}

	public TcMenuService getTcMenuService() {
		return tcMenuService;
	}

	public void setTcMenuService(TcMenuService tcMenuService) {
		this.tcMenuService = tcMenuService;
	}

	public TcMenuRepository getTcMenuRepository() {
		return tcMenuRepository;
	}

	public void setTcMenuRepository(TcMenuRepository tcMenuRepository) {
		this.tcMenuRepository = tcMenuRepository;
	}

	public List<TcMenus> getListTcMenus() {
		return listTcMenus;
	}

	public void setListTcMenus(List<TcMenus> listTcMenus) {
		this.listTcMenus = listTcMenus;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Page<TcMenus> getActualPage() {
		return actualPage;
	}

	public void setActualPage(Page<TcMenus> actualPage) {
		this.actualPage = actualPage;
	}

	public void setSelected(TcMenus selected) {
		this.selected = selected;
	}

	public TcMenus getRowData(String rowKey) {
		for (TcMenus menus : listMenu) {
			if (menus.getId().equals(rowKey)) {
				return menus;
			}
		}
		return null;
	}

	public List<TcMenus> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<TcMenus> listMenu) {
		this.listMenu = listMenu;
	}

	public Boolean getInsert() {
		return insert;
	}

	public void setInsert(Boolean insert) {
		this.insert = insert;
	}

	public Boolean getReset() {
		return reset;
	}

	public void setReset(Boolean reset) {
		this.reset = reset;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public TcMenus getLastRow() {
		return lastRow;
	}

	public void setLastRow(TcMenus lastRow) {
		this.lastRow = lastRow;
	}

	public Integer getGoToPage() {
		return goToPage;
	}

	public void setGoToPage(Integer goToPage) {
		this.goToPage = goToPage;
	}

	public TcMenus getSelected() {
		return selected;
	}

	public Object getRowKey(TcMenus tcMenus) {
		return tcMenus.getId();
	}

	public List<TcMenus> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<TcMenus> data = new ArrayList<TcMenus>();

		// filter
		for (TcMenus car : listMenu) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));

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
				data.add(car);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new LazySorter(sortField, sortOrder));
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

class LazySorter implements Comparator<TcMenus> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(TcMenus o1, TcMenus o2) {
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