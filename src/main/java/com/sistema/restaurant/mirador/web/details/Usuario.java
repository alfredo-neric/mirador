/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.restaurant.mirador.web.details;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class Usuario implements UserDetails {
	/**
	 * .
	 */
    private static final Logger LOGGER = LoggerFactory.getLogger(Usuario.class);
    
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String nombre;    
    boolean status = false;
    private Collection<GrantedAuthority> list;

    public Usuario() {
        LOGGER.info("Creating a Usuario");   
    }

    public Usuario(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

   
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Login[ usuario=" + username + " ]";        
    }

    /**
     * Returns the authorities granted to the user. Cannot return
     * <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    public Collection<GrantedAuthority> getAuthorities() {
        return list;
    }

    
    public void setAuthorities(Collection<GrantedAuthority> roles) {
        this.list = roles;
    }

    /**
     * Regresa el identificador único de un usuario.
     * @return 
     */
    
    public String getUsername() {
        return username;
    }

    /**
     * Establece el identificador único de un usuario.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Método que establece el status de autenticación de un usuario.
     *
     * @param status
     */
    public void setAuthentication(boolean status) {
        this.status = status;
    }

    /**
     * Indicates whether the user's account has expired. An expired account
     * cannot be authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie
     * non-expired), <code>false</code> if no longer valid (ie expired)
     */
    
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code>
     * otherwise
     */
    
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie
     * non-expired), <code>false</code> if no longer valid (ie expired)
     */
    
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot
     * be authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code>
     * otherwise
     */
    
    public boolean isEnabled() {
        return true;
    }
}
