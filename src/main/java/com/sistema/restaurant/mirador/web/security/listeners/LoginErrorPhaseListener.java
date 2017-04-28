/**
 * 
 */
package com.sistema.restaurant.mirador.web.security.listeners;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;

/**
 * @author gauss
 *
 */
public class LoginErrorPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	public void beforePhase(PhaseEvent arg0) {
		Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
				WebAttributes.AUTHENTICATION_EXCEPTION);

		if (e instanceof InternalAuthenticationServiceException)
		{
			printMessage("Usuario no existente en el sistema");
		}else  if (e instanceof BadCredentialsException)
		{
			printMessage("Usuario o pasword invalido.");
		}else if( e instanceof LockedException ){
			printMessage("Usuario Bloqueado.");
		}else if( e instanceof AuthenticationException){
			printMessage(e.getMessage());
		}

	}


	private void printMessage(final String message){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
				WebAttributes.AUTHENTICATION_EXCEPTION, null);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(message));
	}


	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */

	public PhaseId getPhaseId() {

		return PhaseId.RENDER_RESPONSE;
	}

}
