package com.sistema.restaurant.mirador.web.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.business.domain.TcRole;
import com.sistema.restaurant.mirador.business.domain.TcUsuario;
import com.sistema.restaurant.mirador.repository.TcMenuItemRepository;
import com.sistema.restaurant.mirador.repository.TcMenuRepository;
import com.sistema.restaurant.mirador.repository.TcRoleRepository;
import com.sistema.restaurant.mirador.repository.TcUsuarioRepository;
import com.sistema.restaurant.mirador.web.datamodel.TcUsuarioDataModel;



/**
 * 
 * @author Gauss
 *
 */
@ManagedBean(name="usersMB")
@ViewScoped
public class UsersMB  implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante para utilizar el log de la aplicacion
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersMB.class);

	@ManagedProperty( value = "#{tcUsuarioDataModel}")
	private TcUsuarioDataModel tcUsuarioDataModel;

	private TcUsuario selectedTcUsuario;
	
	private TcUsuario mirrorTcUsuario;

	private TcUsuario newTcUsuario;

	@ManagedProperty(value = "#{tcUsuarioRepository}")
	private TcUsuarioRepository tcUsuarioRepository;


	@Autowired
	@ManagedProperty(value = "#{tcMenuRepository}")
	private TcMenuRepository tcMenuRepository;

	@ManagedProperty(value = "#{tcRoleRepository}")
	private TcRoleRepository tcRoleRepository;
	

	
	@ManagedProperty(value = "#{tcMenuItemRepository}")	
	private TcMenuItemRepository tcMenuItemRepository;
	
	@ManagedProperty(value = "#{passwordEncoder}")
	private PasswordEncoder passwordEncoder;
	
	


	private List<TcMenus> menus;
	
	private List<TcRole> roles;

	private Map<Long, String[]> selectedMenuItems = new HashMap<Long, String[]>();



	/**
	 * Inicializa los objetos
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct UsersMB ");
		menus = tcMenuRepository.findAll();
		roles = tcRoleRepository.findAll();
	}

	/**
	 * Prepara un nuevo Usuario
	 * @param actionEvent
	 */
	public void prepareInsert() {
		newTcUsuario = new TcUsuario();
		newTcUsuario.setNombre(StringUtils.EMPTY);
		newTcUsuario.setUsuario(StringUtils.EMPTY);
		newTcUsuario.setContrasenia(StringUtils.EMPTY);
		newTcUsuario.setAccountnonlocked(0);
		newTcUsuario.setAccountnonexpired(0);
		newTcUsuario.setHabilitado(0);
		newTcUsuario.setCredentialsnonexpired(0);
	}
	
	
	/**
	 * Prepara un nuevo Usuario
	 * @param actionEvent
	 */
	public void prepareUpdate() {
		LOGGER.debug( "usuario::" +selectedTcUsuario.getNombre()   );	
			
		mirrorTcUsuario = tcUsuarioRepository.findOne(selectedTcUsuario.getId());
		
	}
	
	
	/**
	 * Actualiza un usuario
	 * @param actionEvent
	 */
	public void actualizar() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("Usuario  %1$s  Actualizado correctamente", selectedTcUsuario.getUsuario()) );

		try {
			
			if( !StringUtils.equals(selectedTcUsuario.getContrasenia(), mirrorTcUsuario.getContrasenia())){
				final String pass = selectedTcUsuario.getContrasenia();
				selectedTcUsuario.setContrasenia(passwordEncoder.encode(pass));
			} else {
				selectedTcUsuario.setContrasenia( mirrorTcUsuario.getContrasenia());
			}
			
			final TcRole tcRole = tcRoleRepository.findOne(selectedTcUsuario.getRole().getId());
			selectedTcUsuario.setRole(tcRole);
			tcUsuarioRepository.save(selectedTcUsuario);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);
	}

	/**
	 * Inserta un usuaario
	 * @param actionEvent
	 */
	public void insert() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;

		try {
			final String pass = newTcUsuario.getContrasenia();
			newTcUsuario.setContrasenia(passwordEncoder.encode(pass));
			
			tcUsuarioRepository.save(newTcUsuario);
			
			
			
			tcUsuarioRepository.save(newTcUsuario);
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("Usuario [ %1$s ] Agregado correctamente", newTcUsuario.getUsuario()) );
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);
	}


	


	public TcUsuarioDataModel getTcUsuarioDataModel() {
		return tcUsuarioDataModel;
	}

	public void setTcUsuarioDataModel(TcUsuarioDataModel tcUsuarioDataModel) {
		this.tcUsuarioDataModel = tcUsuarioDataModel;
	}

	/**
	 * @return the selectedTcUsuario
	 */
	public TcUsuario getSelectedTcUsuario() {
		return selectedTcUsuario;
	}


	/**
	 * @param selectedTcUsuario the selectedTcUsuario to set
	 */
	public void setSelectedTcUsuario(TcUsuario selectedTcUsuario) {
		this.selectedTcUsuario = selectedTcUsuario;
	}


	/**
	 * @return the tcUsuarioRepository
	 */
	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}


	/**
	 * @param tcUsuarioRepository the tcUsuarioRepository to set
	 */
	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}




	

	
	/**
	 * @return the newTcUsuario
	 */
	public TcUsuario getNewTcUsuario() {
		return newTcUsuario;
	}


	/**
	 * @param newTcUsuario the newTcUsuario to set
	 */
	public void setNewTcUsuario(TcUsuario newTcUsuario) {
		this.newTcUsuario = newTcUsuario;
	}


	


	

	/**
	 * @return the tcMenuRepository
	 */
	public TcMenuRepository getTcMenuRepository() {
		return tcMenuRepository;
	}

	/**
	 * @param tcMenuRepository the tcMenuRepository to set
	 */
	public void setTcMenuRepository(TcMenuRepository tcMenuRepository) {
		this.tcMenuRepository = tcMenuRepository;
	}

	/**
	 * @return the menus
	 */
	public List<TcMenus> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus(List<TcMenus> menus) {
		this.menus = menus;
	}

	
	


	/**
	 * @return the selectedMenuItems
	 */
	public Map<Long, String[]> getSelectedMenuItems() {
		return selectedMenuItems;
	}

	/**
	 * @param selectedMenuItems the selectedMenuItems to set
	 */
	public void setSelectedMenuItems(Map<Long, String[]> selectedMenuItems) {
		this.selectedMenuItems = selectedMenuItems;
	}

	/**
	 * @return the tcMenuItemRepository
	 */
	public TcMenuItemRepository getTcMenuItemRepository() {
		return tcMenuItemRepository;
	}

	/**
	 * @param tcMenuItemRepository the tcMenuItemRepository to set
	 */
	public void setTcMenuItemRepository(TcMenuItemRepository tcMenuItemRepository) {
		this.tcMenuItemRepository = tcMenuItemRepository;
	}

	public TcRoleRepository getTcRoleRepository() {
		return tcRoleRepository;
	}

	public void setTcRoleRepository(TcRoleRepository tcRoleRepository) {
		this.tcRoleRepository = tcRoleRepository;
	}

	public List<TcRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TcRole> roles) {
		this.roles = roles;
	}

	public TcUsuario getMirrorTcUsuario() {
		return mirrorTcUsuario;
	}

	public void setMirrorTcUsuario(TcUsuario mirrorTcUsuario) {
		this.mirrorTcUsuario = mirrorTcUsuario;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
