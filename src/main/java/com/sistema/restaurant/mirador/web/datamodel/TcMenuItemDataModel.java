/**
 * 
 */
package com.sistema.restaurant.mirador.web.datamodel;

import static com.sistema.restaurant.mirador.repository.predicate.TcMenuItemsPredicates.findByIconContains;
import static com.sistema.restaurant.mirador.repository.predicate.TcMenuItemsPredicates.findByIdMenu;
import static com.sistema.restaurant.mirador.repository.predicate.TcMenuItemsPredicates.findByLabelContains;
import static com.sistema.restaurant.mirador.repository.predicate.TcMenuItemsPredicates.findByStyleContains;
import static com.sistema.restaurant.mirador.repository.predicate.TcMenuItemsPredicates.findByUrlContains;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import com.sistema.restaurant.mirador.business.domain.TcMenuItem;
import com.sistema.restaurant.mirador.repository.TcMenuItemRepository;


/**
 * @author gauss
 *s
 */
@Service
@Scope("prototype")
public class TcMenuItemDataModel extends CustomPagingModel<TcMenuItem> {
	
	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Componente de servicio
	 */
	@Autowired
	private TcMenuItemRepository tcMenuItemRepository;
	

	@Override
	protected String getId(TcMenuItem entity) {
		return entity.getId().toString();
	}

	@Override
	protected int countData(Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		return Long.valueOf( tcMenuItemRepository.count(search) ).intValue();

	}

	@Override
	protected List<TcMenuItem> fetchData(Pageable pageable, Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		Page<TcMenuItem> page = tcMenuItemRepository.findAll(search,pageable);
		return page.getContent();
	}

	@Override
	protected List<TcMenuItem> fetchData(Pageable pageable) {
		Page<TcMenuItem> page = tcMenuItemRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	protected int countData() {
		return Long.valueOf( tcMenuItemRepository.count() ).intValue();
	}
	
	private Predicate constructSearchQuery(Map<String, Object> filters) {
        BooleanBuilder mainQuery = new BooleanBuilder();

		final String label = MapUtils.getString(filters,"label");
		if (isNotBlank(label)) {
			mainQuery.and(findByLabelContains(label));
		}

		final String url = MapUtils.getString(filters,"url");
		if (isNotBlank(url)) {
			mainQuery.and(findByUrlContains(url));
		}
		
		final String styleclass = MapUtils.getString(filters,"styleclass");
		if (isNotBlank(styleclass)) {
			mainQuery.and(findByStyleContains(styleclass));
		}
		
		final String icon = MapUtils.getString(filters,"icon");
		if (isNotBlank(icon)) {
			mainQuery.and(findByIconContains(icon));
		}
		
		final Long idMenu = MapUtils.getLong(filters, "menu.id");
		if (null!=idMenu) {
			mainQuery.and(findByIdMenu(idMenu));
		}
        return mainQuery;
    }

	@Override
	protected Sort orderBy() {
		return new Sort(Sort.Direction.DESC, "id");
	}   

}
