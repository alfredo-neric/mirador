package com.sistema.restaurant.mirador.web.model;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UerUser extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * CENTRAL / MUNICIPAL
	 */
	private String userType;

	/**
	 *  NOMBRE COMPLETO
	 */
	private String fullName;
	
	
	private Date lastLoginDate;
	
	private String managementAdmin;
	
	private String role;
	
	private Long idManagementAdmin;
	
	private String municipio;
	
	private Long idMunicipio;
	
	
	/**
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public UerUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	
	
	public UerUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String userType, String fullName, Date lastLoginDate, String managementAdmin, String role) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userType = userType;
		this.fullName = fullName;
		this.lastLoginDate = lastLoginDate;
		this.managementAdmin = managementAdmin;
		this.role = role;
	}
	
	
	
	public UerUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String userType, String fullName, Date lastLoginDate, String managementAdmin, String role, Long idManagementAdmin, String municipio , Long idMunicipio) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userType = userType;
		this.fullName = fullName;
		this.lastLoginDate = lastLoginDate;
		this.managementAdmin = managementAdmin;
		this.role = role;
		this.idManagementAdmin = idManagementAdmin;
		this.municipio = municipio;
		this.idMunicipio = idMunicipio;
		
	}	
	

	/**
	 * @param username
	 * @param password
	 * @param authorities
	 * @param userType
	 * @param fullName
	 * @param lastLoginDate
	 */
	public UerUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String userType, String fullName, Date lastLoginDate) {
		super(username, password, authorities);
		this.userType = userType;
		this.fullName = fullName;
		this.lastLoginDate = lastLoginDate;
	}
	
	
	
	

	/**
	 * @param username
	 * @param password
	 * @param authorities
	 * @param userType
	 * @param fullName
	 * @param lastLoginDate
	 * @param managementAdmin
	 * @param role
	 */
	public UerUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String userType, String fullName, Date lastLoginDate, String role) {
		super(username, password, authorities);
		this.userType = userType;
		this.fullName = fullName;
		this.lastLoginDate = lastLoginDate;
		this.managementAdmin = managementAdmin;
		this.role = role;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	
	
	
	public String getManagementAdmin() {
		return managementAdmin;
	}

	public void setManagementAdmin(String managementAdmin) {
		this.managementAdmin = managementAdmin;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	
	
	
	public Long getIdManagementAdmin() {
		return idManagementAdmin;
	}



	public void setIdManagementAdmin(Long idManagementAdmin) {
		this.idManagementAdmin = idManagementAdmin;
	}



	public String getMunicipio() {
		return municipio;
	}



	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}



	public Long getIdMunicipio() {
		return idMunicipio;
	}



	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}



	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
