/**
 * 
 */
package com.sistema.restaurant.mirador.web.datamodel;

import static com.sistema.restaurant.mirador.repository.predicate.TcUsuarioPredicates.findByIdRole;
import static com.sistema.restaurant.mirador.repository.predicate.TcUsuarioPredicates.findByUsuarioContains;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import com.sistema.restaurant.mirador.business.domain.TcUsuario;
import com.sistema.restaurant.mirador.repository.TcUsuarioRepository;

/**
 * @author gauss
 *s
 */
@Service
@Scope("prototype")
public class TcUsuarioDataModel extends CustomPagingModel<TcUsuario> {

	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Componente de servicio
	 */
	@Autowired
	private TcUsuarioRepository tcUsuarioRepository;


	@Override
	protected String getId(TcUsuario entity) {
		return entity.getId().toString();
	}

	@Override
	protected int countData(Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		return Long.valueOf( tcUsuarioRepository.count(search) ).intValue();

	}

	@Override
	protected List<TcUsuario> fetchData(Pageable pageable, Map<String, Object> filters) {
		Predicate search = constructSearchQuery(filters);
		Page<TcUsuario> page = tcUsuarioRepository.findAll(search,pageable);
		return page.getContent();
	}

	@Override
	protected List<TcUsuario> fetchData(Pageable pageable) {
		Page<TcUsuario> page = tcUsuarioRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	protected int countData() {
		return Long.valueOf( tcUsuarioRepository.count() ).intValue();
	}

	private Predicate constructSearchQuery(Map<String, Object> filters) {		
		final BooleanBuilder mainQuery = new BooleanBuilder();
		//for (Entry<String, Object> entry : filters.entrySet() ) {
		final String user = MapUtils.getString(filters, "usuario");
		if (isNotBlank(user)) {
			mainQuery.and(findByUsuarioContains(trimToEmpty(user)));
		}

		final Long idRole = MapUtils.getLong(filters, "role.id");
		if (null!=idRole) {
			mainQuery.and(findByIdRole(idRole));
		}
		//}


		return mainQuery;
	}

	@Override
	protected Sort orderBy() {
		return new Sort(Sort.Direction.DESC, "id");
	}   

}
