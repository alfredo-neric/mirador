package com.sistema.restaurant.mirador.web.bean;

import java.io.Serializable;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.repository.TcMenuRepository;
import com.sistema.restaurant.mirador.web.datamodel.TcMenuDataModel;

/**
 * 
 * @author Gauss
 *
 */
@ManagedBean(name="tcMenusMB")
@ViewScoped
public class TcMenusMB  implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante para utilizar el log de la aplicacion
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TcMenusMB.class);

	@ManagedProperty( value = "#{tcMenuDataModel}")
	private TcMenuDataModel tcMenuDataModel;

	private TcMenus selectedTcMenu;
	
	private TcMenus mirrorTcMenu;
	


	@Autowired
	@Qualifier("newTcMenu")
	private TcMenus newTcMenu;
	
	
	@Autowired
	@ManagedProperty(value = "#{tcMenuRepository}")
	private TcMenuRepository tcMenuRepository;
	
	public TcMenuRepository getTcMenuRepository() {
		return tcMenuRepository;
	}

	public void setTcMenuRepository(TcMenuRepository tcMenuRepository) {
		this.tcMenuRepository = tcMenuRepository;
	}
	

	/**
	 * Inicializa los objetos
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct tcMenusMB ");
	}

	/**
	 * Prepara un nuevo Menu
	 * @param actionEvent
	 */
	public void prepareInsert() {
		newTcMenu = new TcMenus();
		newTcMenu.setLabel(StringUtils.EMPTY);
		newTcMenu.setUrl(StringUtils.EMPTY);
		newTcMenu.setStyleClass(StringUtils.EMPTY);
		newTcMenu.setIcon(StringUtils.EMPTY);
	}
	
	
	/**
	 * Prepara actualizacion de menu
	 * @param actionEvent
	 */
	public void prepareUpdate() {
		if(null!=selectedTcMenu){
			LOGGER.debug( "rol::" +selectedTcMenu.getLabel());	
			mirrorTcMenu = tcMenuRepository.findOne(selectedTcMenu.getId());
		}
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione un Menu para actualizar"));


	}
	
	
	/**
	 * Actualiza un menu
	 * @param actionEvent
	 */
	public void actualizar() {
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;

		try {
			
			tcMenuRepository.save(selectedTcMenu);

			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("Menu [ %1$s ] Actualizado correctamente", selectedTcMenu.getLabel()) );
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);

	}

	/**
	 * Inserta un menu
	 * @param actionEvent
	 */
	public void insert() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;

		try {
			System.out.println("newTcMenu"+newTcMenu);
			tcMenuRepository.save(newTcMenu);
		    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("Menu [ %1$s ] Agregado correctamente", newTcMenu.getLabel()) );
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);
	}


	public TcMenus getNewTcMenu() {
		return newTcMenu;
	}

	public void setNewTcMenu(TcMenus newTcMenu) {
		this.newTcMenu = newTcMenu;
	}

	public TcMenuDataModel getTcMenuDataModel() {
		return tcMenuDataModel;
	}

	public void setTcMenuDataModel(TcMenuDataModel tcMenuDataModel) {
		this.tcMenuDataModel = tcMenuDataModel;
	}

	public TcMenus getSelectedTcMenu() {
		return selectedTcMenu;
	}

	public void setSelectedTcMenu(TcMenus selectedTcMenu) {
		this.selectedTcMenu = selectedTcMenu;
	}

	public TcMenus getMirrorTcMenu() {
		return mirrorTcMenu;
	}

	public void setMirrorTcMenu(TcMenus mirrorTcMenu) {
		this.mirrorTcMenu = mirrorTcMenu;
	}


}
