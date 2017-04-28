/**
 * 
 */
package com.sistema.restaurant.mirador.security.services;

import com.sistema.restaurant.mirador.business.domain.TwUserAttempt;

/**
 * @author developer
 *
 */
public interface UserService {
	
	/**
	 * Actualiza los intentos fallidos
	 * @param username
	 */
	void updateFailAttempts(String username);

	/**
	 * restablece los intentos fallidos
	 * @param username
	 */
	void resetFailAttempts(String username);
	
	/**
	 * Obtiene datos del intento fallido
	 * @param username
	 * @return
	 */
	TwUserAttempt getUserAttempts(String username);


}
