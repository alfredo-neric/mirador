/**
 * 
 */
package com.sistema.restaurant.mirador.security.services;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;

import com.sistema.restaurant.mirador.business.domain.TcUsuario;
import com.sistema.restaurant.mirador.business.domain.TwUserAttempt;
import com.sistema.restaurant.mirador.repository.TcUsuarioRepository;
import com.sistema.restaurant.mirador.repository.TwUserAttemptRepository;



/**
 * @author developer
 *
 */
@Repository("userService")
public class UserServiceImpl implements UserService {
	
	/**
	 * Constante para utilizar el log de la aplicacion
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	/**
	 * Numero Maximo de intentos
	 */
	private static final int MAX_ATTEMPTS = 3;
	
	/**
	 * Numero Maximo de intentos
	 */
	private static final int INIT_CUONTER = 1;
	
	
	@Autowired
	private TcUsuarioRepository tcUsuarioRepository;
	
	@Autowired
	private TwUserAttemptRepository  twUserAttemptRepository;
	
	
	/* (non-Javadoc)
	 * @see com.uer.sistema.web.security.services.UserService#updateFailAttempts(java.lang.String)
	 */
	public void updateFailAttempts(String username) {
		// buscamos a ver si ya existe en nuestra tabla
		final TwUserAttempt twUserAttempt = getUserAttempts(username);
		
		//buscamos que el registro exista en nuestra tabla de usuarios
		final TcUsuario usuario = tcUsuarioRepository.findByUsuario( username);
		
		if( null == twUserAttempt){
			
			if( null != usuario){
				//Si si es un registro valido. entonces insertamo un nuevo registro en la tabla de  user attemps
				final TwUserAttempt userAttempt = new TwUserAttempt();
				userAttempt.setUsuario(username);
				userAttempt.setAttempts(INIT_CUONTER);
				userAttempt.setLastmodified(Calendar.getInstance().getTime());
				twUserAttemptRepository.save(userAttempt);
				
			}
		}else{
			if( null != usuario){
				final int currentAttemps=twUserAttempt.getAttempts();
				twUserAttempt.setAttempts(currentAttemps+1);
				twUserAttempt.setLastmodified(Calendar.getInstance().getTime());
				
				twUserAttemptRepository.save(twUserAttempt);
			}
			
			if(twUserAttempt.getAttempts()>= MAX_ATTEMPTS){
				LOGGER.info("Usuario antes de actualizar" );
//				if(null!=usuario){
//					//tcUsuarioRepository.setAccountNonLockedFor(Boolean.FALSE,usuario.getId());	
//				}
				
				
				throw new LockedException("La cuenta esta Bloqueada!");
			}
			
		}

	}

	/* (non-Javadoc)
	 * @see com.uer.sistema.web.security.services.UserService#resetFailAttempts(java.lang.String)
	 */
	public void resetFailAttempts(String username) {
		// buscamos a ver si ya existe en nuestra tabla
				final TwUserAttempt twUserAttempt =  getUserAttempts(username);
				if ( null != twUserAttempt ) {
				twUserAttempt.setAttempts(0);
				twUserAttempt.setLastmodified(null);
				twUserAttemptRepository.save(twUserAttempt);
				}
	}

	/* (non-Javadoc)
	 * @see com.uer.sistema.web.security.services.UserService#getUserAttempts(java.lang.String)
	 */
	public TwUserAttempt getUserAttempts(String username) {
		return twUserAttemptRepository.findByUsuario(username);
	}

	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}

	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}

	public TwUserAttemptRepository getTwUserAttemptRepository() {
		return twUserAttemptRepository;
	}

	public void setTwUserAttemptRepository(TwUserAttemptRepository twUserAttemptRepository) {
		this.twUserAttemptRepository = twUserAttemptRepository;
	}
	
	
	
	
}
