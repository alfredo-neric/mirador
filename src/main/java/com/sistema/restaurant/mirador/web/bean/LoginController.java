
package com.sistema.restaurant.mirador.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Named()
@RequestScoped
public class LoginController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private String user;
	private String password;




	/*************************************************/

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@SuppressWarnings("rawtypes")
	public String login() throws ServletException, IOException { 
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		ServletRequest requestContext =  (ServletRequest) context.getRequest();
		ServletResponse responseContext =  (ServletResponse) context.getResponse();


		RequestDispatcher dispatcher = requestContext.getRequestDispatcher("/j_spring_security_check");

		Iterator entries = requestContext.getParameterMap().entrySet().iterator();
		while (entries.hasNext()) {
			Entry entry = (Entry) entries.next();
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());
			LOGGER.debug("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());        
		}



		dispatcher.forward(requestContext, responseContext);
		FacesContext.getCurrentInstance().responseComplete();

		return null;
	}


}