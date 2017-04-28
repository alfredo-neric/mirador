/**
 * .
 */
package com.sistema.restaurant.mirador.web.datamodel;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sistema.restaurant.mirador.business.domain.AbstractEntity;

/**
 * Clase de Paginacion Generica
 * @author gauss .
 *
 * @param <T> .
 */
public abstract class CustomPagingModel<T extends AbstractEntity> extends LazyDataModel<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> items;
	private int pageSize;
	private int rowIndex;
	private int rowCount;

	public CustomPagingModel() {
		super();
	}

	protected abstract String getId(T entity);

	protected abstract int countData(Map<String, Object> filters);

	protected abstract int countData();

	protected abstract List<T> fetchData(Pageable pageable, Map<String, Object> filters);

	protected abstract List<T> fetchData(Pageable pageable);

	protected abstract Sort orderBy();

	//@Override
	public List<T> load(int first, int pageSize,  Map<String, Object> filters) {

		Pageable page = new PageRequest(first / pageSize, pageSize, orderBy());
		setRowCount(countData(filters));
		return fetchData(page, filters);
	}


	public List<T> load(int first, int pageSize) {
		Pageable page = new PageRequest(first / pageSize, pageSize, orderBy());

		setRowCount(countData());
		return fetchData(page);
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
		Pageable page = new PageRequest(first / pageSize, pageSize, orderBy());

		setRowCount(countData(filters));
		return fetchData(page, filters);

	}

	@Override
	public boolean isRowAvailable() {
		if ( CollectionUtils.isEmpty(items) ) {
			return false;
		}

		int index = rowIndex % pageSize;
		return index >= 0 && index < items.size();
	}

	@Override
	public Object getRowKey(T entity) {
		return getId(entity);
	}

	@Override
	public T getRowData() {
		if (CollectionUtils.isEmpty(items)) {
			return null;
		}

		int index = rowIndex % pageSize;
		if (index > items.size()) {
			return null;
		}

		return items.get(index);
	}

	@Override
	public T getRowData(String rowKey) {
		if (items == null) {
			return null;
		}

		for (T entity : items) {
			if (getId(entity).equals(rowKey)) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getRowIndex() {
		return this.rowIndex;
	}

	@Override
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	@Override
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	@Override
	public int getRowCount() {
		return this.rowCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setWrappedData(Object list) {
		this.items = (List<T>) list;
	}

	@Override
	public Object getWrappedData() {
		return items;
	}
}