package com.sistema.restaurant.mirador.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.sistema.restaurant.mirador.business.domain.TcMenuItem;
import com.sistema.restaurant.mirador.business.domain.TcMenus;
import com.sistema.restaurant.mirador.business.domain.TcRole;
import com.sistema.restaurant.mirador.business.domain.TrRolesMenus;
import com.sistema.restaurant.mirador.repository.TcMenuRepository;
import com.sistema.restaurant.mirador.repository.TcRoleRepository;
import com.sistema.restaurant.mirador.web.datamodel.TcRolDataModel;

import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

/**
 * 
 * @author Gauss
 *
 */
@ManagedBean(name="rolesMB")
@ViewScoped
public class RolesMB  implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante para utilizar el log de la aplicacion
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RolesMB.class);

	@ManagedProperty( value = "#{tcRolDataModel}")
	private TcRolDataModel tcRolDataModel;

	private TcRole selectedTcRol;
	
	private TcRole mirrorTcRol;
	
	public TcRole getMirrorTcRol() {
		return mirrorTcRol;
	}

	public void setMirrorTcRol(TcRole mirrorTcRol) {
		this.mirrorTcRol = mirrorTcRol;
	}

	@Autowired
	@Qualifier("newTcRole")
	private TcRole newTcRole;
	
	private List<TcMenus> menus;
	private List<String> selectedMenus;
	private List<String> selectedMenusMirror;
	
	public List<String> getSelectedMenusMirror() {
		return selectedMenusMirror;
	}

	public void setSelectedMenusMirror(List<String> selectedMenusMirror) {
		this.selectedMenusMirror = selectedMenusMirror;
	}

	public List<String> getSelectedMenus() {
		return selectedMenus;
	}

	public void setSelectedMenus(List<String> selectedMenus) {
		this.selectedMenus = selectedMenus;
	}

	@ManagedProperty(value = "#{tcRoleRepository}")
	private TcRoleRepository tcRoleRepository;
	
	@Autowired
	@ManagedProperty(value = "#{tcMenuRepository}")
	private TcMenuRepository tcMenuRepository;
	
	public TcMenuRepository getTcMenuRepository() {
		return tcMenuRepository;
	}

	public void setTcMenuRepository(TcMenuRepository tcMenuRepository) {
		this.tcMenuRepository = tcMenuRepository;
	}
	
	private TreeNode detalleRol;

	public TreeNode getDetalleRol() {
		return detalleRol;
	}

	public void setDetalleRol(TreeNode detalleRol) {
		this.detalleRol = detalleRol;
	}

	/**
	 * Inicializa los objetos
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct rolesMB ");
		menus = tcMenuRepository.findAll();
		selectedMenus = new ArrayList<String>();
		detalleRol = new DefaultTreeNode();
	}

	/**
	 * Prepara un nuevo Role
	 * @param actionEvent
	 */
	public void prepareInsert() {
		newTcRole = new TcRole();
		newTcRole.setClave(StringUtils.EMPTY);
		newTcRole.setNombre(StringUtils.EMPTY);

	}
	
	
	/**
	 * Prepara actualizacion de rol
	 * @param actionEvent
	 */
	public void prepareUpdate() {
		if(null!=selectedTcRol){
			LOGGER.debug( "rol::" +selectedTcRol.getNombre()   );	
				
			mirrorTcRol = tcRoleRepository.findOne(selectedTcRol.getId());
			
			List<TcMenus>lmenus= selectedTcRol.getMenus();
			selectedMenusMirror = new ArrayList<String>();
			if(null!=lmenus && !lmenus.isEmpty()){
				for(TcMenus men : lmenus){
					selectedMenusMirror.add(men.getId().toString());
				}
			}
			
		}
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione un Rol para actualizar"));

		
		
	}
	
	/**
	 * MuestraDetalle de rol
	 * @param actionEvent
	 */
	public void getRolDetail() {
		
		if(null!=selectedTcRol){
			LOGGER.debug( "detalle::" +selectedTcRol.getNombre()   );	
			
			detalleRol = new DefaultTreeNode(selectedTcRol.getNombre(), null);
			List<TcMenus> listMenus = (List<TcMenus>)selectedTcRol.getMenus();
			if(null!=listMenus&& !listMenus.isEmpty()){
				for(TcMenus menu: listMenus){
					TreeNode nodo = new DefaultTreeNode(menu.getLabel(),detalleRol);
					List<TcMenuItem> lmenuItems = (List<TcMenuItem>)menu.getMenuItems();
					if(null!=lmenuItems&&!lmenuItems.isEmpty()){
						for(TcMenuItem menuItem:lmenuItems){
							new DefaultTreeNode(menuItem.getLabel(),nodo);
						}
					}
				}
			}
		}
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione un Rol para actualizar"));

//		mirrorTcRol = tcRoleRepository.findOne(selectedTcRol.getId());
//		
//		LOGGER.debug( "menus"+mirrorTcRol.getMenus());
//		LOGGER.debug( "menus as");
		
	}
	
	
	/**
	 * Actualiza un rol
	 * @param actionEvent
	 */
	public void actualizar() {
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;

		try {
			
			tcRoleRepository.save(selectedTcRol);
		//	tcRoleRepository.insertnewRol(newTcRole.getClave(), newTcRole.getNombre());
			Long idRol = selectedTcRol.getId();
			if(selectedMenusMirror!=null && !selectedMenusMirror.isEmpty()){
				tcRoleRepository.deletenewRolMenu(idRol);
				for(String menuSel: selectedMenusMirror){
					TrRolesMenus rolMenu = new TrRolesMenus();
					rolMenu.setIdRol(idRol);
					rolMenu.setIdMenu(Long.parseLong(menuSel));
					System.out.println("rol: "+rolMenu.getIdRol()+", menu: "+rolMenu.getIdMenu());
					tcRoleRepository.insertnewRolMenu(rolMenu.getIdRol(), rolMenu.getIdMenu());
					
				//	tcRoleRepository.save(rolMenu);
				}
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("Rol [ %1$s ] Actualizado correctamente", selectedTcRol.getNombre()) );
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);

	}

	/**
	 * Inserta un rol
	 * @param actionEvent
	 */
	public void insert() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;

		try {
			System.out.println("newTcRole"+newTcRole);
		//	tcRoleRepository.insertnewRol(newTcRole.getClave(), newTcRole.getNombre());
			Long idRol = tcRoleRepository.save(newTcRole).getId();
			if(selectedMenus!=null && !selectedMenus.isEmpty()){
				for(String menuSel: selectedMenus){
					TrRolesMenus rolMenu = new TrRolesMenus();
					rolMenu.setIdRol(idRol);
					rolMenu.setIdMenu(Long.parseLong(menuSel));
					System.out.println("rol: "+rolMenu.getIdRol()+", menu: "+rolMenu.getIdMenu());
					tcRoleRepository.insertnewRolMenu(rolMenu.getIdRol(), rolMenu.getIdMenu());
					
				//	tcRoleRepository.save(rolMenu);
				}
			}
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("Rol [ %1$s ] Agregado correctamente", newTcRole.getNombre()) );
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);
	}

	/**
	 * @return
	 */
	public TcRolDataModel getTcRolDataModel() {
		return tcRolDataModel;
	}

	/**
	 * @param tcRolDataModel
	 */
	public void setTcRolDataModel(TcRolDataModel tcRolDataModel) {
		this.tcRolDataModel = tcRolDataModel;
	}

	/**
	 * @return
	 */
	public TcRole getSelectedTcRol() {
		return selectedTcRol;
	}

	/**
	 * @param selectedTcRol
	 */
	public void setSelectedTcRol(TcRole selectedTcRol) {
		this.selectedTcRol = selectedTcRol;
	}

	public TcRole getNewTcRole() {
		return newTcRole;
	}

	public void setNewTcRole(TcRole newTcRole) {
		this.newTcRole = newTcRole;
	}

	public List<TcMenus> getMenus() {
		return menus;
	}

	public void setMenus(List<TcMenus> menus) {
		this.menus = menus;
	}

	public TcRoleRepository getTcRoleRepository() {
		return tcRoleRepository;
	}

	public void setTcRoleRepository(TcRoleRepository tcRoleRepository) {
		this.tcRoleRepository = tcRoleRepository;
	}


}
