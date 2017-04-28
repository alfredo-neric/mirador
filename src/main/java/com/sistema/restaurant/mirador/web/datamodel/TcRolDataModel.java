/**
 * 
 */
package com.sistema.restaurant.mirador.web.datamodel;

import static com.sistema.restaurant.mirador.repository.predicate.TcRolePredicates.findByClaveContains;
import static com.sistema.restaurant.mirador.repository.predicate.TcRolePredicates.findByNombreContains;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

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
import com.sistema.restaurant.mirador.business.domain.TcRole;
import com.sistema.restaurant.mirador.repository.TcRoleRepository;

/**
 * @author gauss
 *s
 */
@Service
@Scope("prototype")
public class TcRolDataModel extends CustomPagingModel<TcRole> {
	
	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Componente de servicio
	 */
	@Autowired
	private TcRoleRepository TcRoleRepository;
	

	@Override
	protected String getId(TcRole entity) {
		return entity.getId().toString();
	}

	@Override
	protected int countData(Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		return Long.valueOf( TcRoleRepository.count(search) ).intValue();

	}

	@Override
	protected List<TcRole> fetchData(Pageable pageable, Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		Page<TcRole> page = TcRoleRepository.findAll(search,pageable);
		return page.getContent();
	}

	@Override
	protected List<TcRole> fetchData(Pageable pageable) {
		Page<TcRole> page = TcRoleRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	protected int countData() {
		return Long.valueOf( TcRoleRepository.count() ).intValue();
	}
	
	private Predicate constructSearchQuery(Map<String, Object> filters) {
        BooleanBuilder mainQuery = new BooleanBuilder();
       
        final String clave = MapUtils.getString(filters, "clave");
		if (isNotBlank(clave)) {
			mainQuery.and(findByClaveContains(trimToEmpty(clave)));
		}

		final String nombre = MapUtils.getString(filters,"nombre");
		if (isNotBlank(nombre)) {
			mainQuery.and(findByNombreContains(nombre));
		}

        return mainQuery;
    }

	@Override
	protected Sort orderBy() {
		return new Sort(Sort.Direction.DESC, "id");
	}   

}
