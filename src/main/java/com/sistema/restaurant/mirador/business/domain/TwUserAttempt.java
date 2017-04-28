package com.sistema.restaurant.mirador.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tw_user_attempts database table.
 * 
 */
@Entity
@Table(name="tw_user_attempts")
@NamedQuery(name="TwUserAttempt.findAll", query="SELECT t FROM TwUserAttempt t")
public class TwUserAttempt extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer attempts;

	private Date lastmodified;

	private String usuario;

	public TwUserAttempt() {
	}


	public Integer getAttempts() {
		return this.attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public Date getLastmodified() {
		return this.lastmodified;
	}

	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}