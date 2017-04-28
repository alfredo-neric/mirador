package com.sistema.restaurant.mirador.web.security.providers;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sistema.restaurant.mirador.business.domain.TcRole;
import com.sistema.restaurant.mirador.business.domain.TcUsuario;
import com.sistema.restaurant.mirador.repository.TcUsuarioRepository;
import com.sistema.restaurant.mirador.web.model.UerUser;





@Component("authenticationService")
public class AuthenticationService implements UserDetailsService {


	/**
	 * Constante para utilizar el log de la aplicacion
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

	@Autowired
	private TcUsuarioRepository tcUsuarioRepository;
	
	

	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}

	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		LOGGER.info("Recibiendo {}",userName);	
		TcUsuario tcUsuario = tcUsuarioRepository.findByUsuario(userName);

		return buildUserForAuthentication( tcUsuario ,  buildUserAuthority( tcUsuario.getRole()));
	}

	private User buildUserForAuthentication(TcUsuario user, 
			List<GrantedAuthority> authorities) {

		Calendar cal = Calendar.getInstance();	

		return new UerUser(user.getUsuario(),  user.getContrasenia(), authorities, null,user.getNombre(), cal.getTime(),user.getRole().getNombre());
	}




	private List<GrantedAuthority> buildUserAuthority(TcRole role) {

		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();

		result.add(new SimpleGrantedAuthority(role.getClave()));


		return result;
	}





}
