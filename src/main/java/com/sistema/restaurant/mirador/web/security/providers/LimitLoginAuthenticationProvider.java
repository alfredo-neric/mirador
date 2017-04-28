package com.sistema.restaurant.mirador.web.security.providers;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sistema.restaurant.mirador.business.domain.TwUserAttempt;
import com.sistema.restaurant.mirador.security.services.UserService;
import com.sistema.restaurant.mirador.web.bean.UsersMB;

@Component("authenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {
	
	/**
	 * Constante para utilizar el log de la aplicacion
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersMB.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("authenticationService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}
	
	@Autowired
	@Qualifier("passwordEncoder")
	public void setPasswordEncoder(PasswordEncoder passwordEncoder){
		super.setPasswordEncoder(passwordEncoder);

	}
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {

			Authentication auth = super.authenticate(authentication);
			userService.resetFailAttempts(authentication.getName());
			return auth;

		} catch (BadCredentialsException e) {
			LOGGER.error(e.getMessage());
			userService.updateFailAttempts(authentication.getName());
			throw e;

		} catch (LockedException e) {
			LOGGER.error(e.getMessage());
			String error = StringUtils.EMPTY;
			TwUserAttempt userAttempts = userService.getUserAttempts(authentication.getName());
			if (userAttempts != null) {
				Date lastAttempts = userAttempts.getLastmodified();
				error = "User account is locked! <br><br>Username : " + authentication.getName()
						+ "<br>Last Attempts : " + lastAttempts;
			} else {
				error = e.getMessage();
			}

			throw new LockedException(error);
		}

	}

}
