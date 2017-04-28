package com.sistema.restaurant.mirador.web.bean;

import java.io.Serializable;
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

import com.sistema.restaurant.mirador.business.domain.TcMenuItem;
import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.repository.TcMenuItemRepository;
import com.sistema.restaurant.mirador.repository.TcMenuRepository;
import com.sistema.restaurant.mirador.web.datamodel.TcMenuItemDataModel;

/**
 * 
 * @author Gauss
 *
 */
@ManagedBean(name="tcMenuItemsMB")
@ViewScoped
public class TcMenusItemsMB  implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante para utilizar el log de la aplicacion
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TcMenusItemsMB.class);

	@ManagedProperty( value = "#{tcMenuItemDataModel}")
	private TcMenuItemDataModel tcMenuItemDataModel;

	private TcMenuItem selectedTcMenuItem;
	
	private TcMenuItem mirrorTcMenuItem;
	

	private String menuSelId;
	

	private String menuSelName;
	
	private List<TcMenus> menus;

	private TcMenuItem newTcMenuItem;
	
	private Long idMenuUpdate;
	
	

	public String entraMenuPapa(){

		FacesContext fc = FacesContext.getCurrentInstance();
		this.menuSelId = getCountryParam(fc);
		System.out.println("menuSelId"+menuSelId);
		System.out.println("menuSelName"+menuSelName);
		return "result";
	}

	//get value from "f:param"
	public String getCountryParam(FacesContext fc){

		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("menuSelId");

	}


	public Long getIdMenuUpdate() {
		return idMenuUpdate;
	}

	public void setIdMenuUpdate(Long idMenuUpdate) {
		this.idMenuUpdate = idMenuUpdate;
	}

	@Autowired
	@ManagedProperty(value = "#{tcMenuItemRepository}")
	private TcMenuItemRepository tcMenuItemRepository;
	
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
		LOGGER.info(":: En postconsruct tcMenuItemsMB ");
		menus = tcMenuRepository.findAll();
		System.out.println("menuSelId"+menuSelId);
		System.out.println("menuSelName"+menuSelName);
	}

	/**
	 * Prepara un nuevo Menu Item
	 * @param actionEvent
	 */
	public void prepareInsert() {
		newTcMenuItem = new TcMenuItem();
		newTcMenuItem.setLabel(StringUtils.EMPTY);
		newTcMenuItem.setUrl(StringUtils.EMPTY);
		newTcMenuItem.setStyleClass(StringUtils.EMPTY);
		newTcMenuItem.setIcon(StringUtils.EMPTY);
	}
	
	
	/**
	 * Prepara actualizacion de menu item
	 * @param actionEvent
	 */
	public void prepareUpdate() {
		if(null!=selectedTcMenuItem){
			LOGGER.debug( "rol::" +selectedTcMenuItem.getLabel());	
			idMenuUpdate = selectedTcMenuItem.getMenu().getId();
			mirrorTcMenuItem = tcMenuItemRepository.findOne(selectedTcMenuItem.getId());
		}
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione un Sub-Menu para actualizar"));

	}
	
	
	/**
	 * Actualiza un menu item
	 * @param actionEvent
	 */
	public void actualizar() {
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;

		try {
			
			
			System.out.println("****idMenuUpdate:   "+idMenuUpdate);
			TcMenus menuUpdt = new TcMenus();
			menuUpdt.setId(idMenuUpdate);
			selectedTcMenuItem.setMenu(menuUpdt);
			tcMenuItemRepository.save(selectedTcMenuItem);

			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("SubMenu [ %1$s ] Actualizado correctamente", selectedTcMenuItem.getLabel()) );
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);

	}

	/**
	 * Inserta un menu item
	 * @param actionEvent
	 */
	public void insert() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;

		try {
			System.out.println("newTcMenuItem"+newTcMenuItem);
			tcMenuItemRepository.save(newTcMenuItem);
		    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("SubMenu [ %1$s ] Agregado correctamente", newTcMenuItem.getLabel()) );
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);
	}

	public TcMenuItemDataModel getTcMenuItemDataModel() {
		return tcMenuItemDataModel;
	}

	public void setTcMenuItemDataModel(TcMenuItemDataModel tcMenuItemDataModel) {
		this.tcMenuItemDataModel = tcMenuItemDataModel;
	}

	public TcMenuItem getSelectedTcMenuItem() {
		return selectedTcMenuItem;
	}

	public void setSelectedTcMenuItem(TcMenuItem selectedTcMenuItem) {
		this.selectedTcMenuItem = selectedTcMenuItem;
	}

	public TcMenuItem getMirrorTcMenuItem() {
		return mirrorTcMenuItem;
	}

	public void setMirrorTcMenuItem(TcMenuItem mirrorTcMenuItem) {
		this.mirrorTcMenuItem = mirrorTcMenuItem;
	}

	public TcMenuItem getNewTcMenuItem() {
		return newTcMenuItem;
	}

	public void setNewTcMenuItem(TcMenuItem newTcMenuItem) {
		this.newTcMenuItem = newTcMenuItem;
	}

	public TcMenuItemRepository getTcMenuItemRepository() {
		return tcMenuItemRepository;
	}

	public void setTcMenuItemRepository(TcMenuItemRepository tcMenuItemRepository) {
		this.tcMenuItemRepository = tcMenuItemRepository;
	}

	public String getMenuSelId() {
		return menuSelId;
	}

	public void setMenuSelId(String menuSelId) {
		this.menuSelId = menuSelId;
	}

	public String getMenuSelName() {
		return menuSelName;
	}

	public void setMenuSelName(String menuSelName) {
		this.menuSelName = menuSelName;
	}

	public List<TcMenus> getMenus() {
		return menus;
	}

	public void setMenus(List<TcMenus> menus) {
		this.menus = menus;
	}



}
