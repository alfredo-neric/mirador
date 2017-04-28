package com.sistema.restaurant.mirador.web.bean;

import static com.sistema.restaurant.mirador.util.UtilFront.generateNotificationFront;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.sistema.restaurant.mirador.business.domain.TcProveedor;
import com.sistema.restaurant.mirador.business.domain.TcStatus;

@ManagedBean(name = "proveedorMB")
@ViewScoped
public class ProveedorMB extends CommonsCatalog<TcProveedor> {

	public String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@PostConstruct
	public void init() {
		this.findInit();
	}

	public void delete(Integer index) {

	}

	@Override
	public void addElement() {
		select = new TcProveedor();
		this.getList().add(select);
		this.getDataModelGeneric().setListT(list);
		super.addElement();
	}

}
