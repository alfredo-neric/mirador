package com.sistema.restaurant.mirador.dao;

import java.util.List;

import com.sistema.restaurant.mirador.business.domain.TcRole;
import com.sistema.restaurant.mirador.business.domain.TcUsuario;

/**
 * @author Mateo
 *
 */
public interface PanelControlDAO {

	/**
	 * @param tcRole
	 * @return
	 */
	List<TcRole> saveRole(TcRole tcRole);

	/**
	 * @param tcRole
	 * @return
	 */
	List<TcRole> modifyRole(TcRole tcRole);

	/**
	 * @param tcRole
	 * @return
	 */
	List<TcRole> deleteRole(TcRole tcRole);

	/**
	 * @param tcRole
	 * @return
	 */
	Boolean existRole(TcRole tcRole);

	/**
	 * @return
	 */
	List<TcRole> findAllAndOrdeByAsc();

		/**
		 * dao de usuarios
		 */
	
		/**
		 * @param TcUsuario
		 * @return
		 */
		List<TcUsuario> saveUsr(TcUsuario tcUsuario);
	
		/**
		 * @param TcUsuario
		 * @return
		 */
		List<TcUsuario> modifyUsr(TcUsuario tcUsuario);
	
		/**
		 * @param TcUsuario
		 * @return
		 */
		List<TcUsuario> deleteUsr(TcUsuario tcUsuario);
	
		/**
		 * @param TcUsuario
		 * @return
		 */
		Boolean existUsr(TcUsuario tcUsuario);
	
		/**
		 * @return
		 */
		List<TcUsuario> findAllAndOrdeByAscUrs();
	
	/**
	 * @return
	 */
	TcUsuario addUsr();

}
