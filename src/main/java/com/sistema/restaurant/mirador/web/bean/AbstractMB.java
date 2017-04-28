/**
 * 
 */
package com.sistema.restaurant.mirador.web.bean;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.sistema.restaurant.mirador.web.model.UerUser;


/**
 * @author Mateo
 *
 */
public class AbstractMB {
	
	
	
	/**
	 * getUserDetails Metodo para obtener el usuario logueado
	 * @return
	 */
	public UerUser getUserDetails() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            return (UerUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }
}
