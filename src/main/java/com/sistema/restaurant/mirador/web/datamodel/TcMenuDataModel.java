/**
 * 
 */
package com.sistema.restaurant.mirador.web.datamodel;

import static com.sistema.restaurant.mirador.repository.predicate.TcMenuPredicates.findByIconContains;
import static com.sistema.restaurant.mirador.repository.predicate.TcMenuPredicates.findByLabelContains;
import static com.sistema.restaurant.mirador.repository.predicate.TcMenuPredicates.findByStyleContains;
import static com.sistema.restaurant.mirador.repository.predicate.TcMenuPredicates.findByUrlContains;
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
import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.repository.TcMenuRepository;

/**
 * @author gauss
 *s
 */
@Service
@Scope("prototype")
public class TcMenuDataModel extends CustomPagingModel<TcMenus> {
	
	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Componente de servicio
	 */
	@Autowired
	private TcMenuRepository TcMenuRepository;
	

	@Override
	protected String getId(TcMenus entity) {
		return entity.getId().toString();
	}

	@Override
	protected int countData(Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		return Long.valueOf( TcMenuRepository.count(search) ).intValue();

	}

	@Override
	protected List<TcMenus> fetchData(Pageable pageable, Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		Page<TcMenus> page = TcMenuRepository.findAll(search,pageable);
		return page.getContent();
	}

	@Override
	protected List<TcMenus> fetchData(Pageable pageable) {
		Page<TcMenus> page = TcMenuRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	protected int countData() {
		return Long.valueOf( TcMenuRepository.count() ).intValue();
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
        return mainQuery;
    }

	@Override
	protected Sort orderBy() {
		return new Sort(Sort.Direction.DESC, "id");
	}   

}
