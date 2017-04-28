package com.sistema.restaurant.mirador.repository.predicate;

import java.math.BigInteger;

import com.mysema.query.types.Predicate;
import com.sistema.restaurant.mirador.business.domain.QTcStatus;

/**
 * @author Mateo
 *
 */
public class TcStatusPredicate {

	/**
	 * @param idStatus
	 * @return
	 */
	public static Predicate findBy(BigInteger idStatus) {
		return QTcStatus.tcStatus.idStatus.eq(idStatus);
	}

	/**
	 * @param descripcion
	 * @return
	 */
	public static Predicate findByDescripcion(String descripcion) {
		return QTcStatus.tcStatus.descripcion.likeIgnoreCase(descripcion);
	}
}
