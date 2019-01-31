package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the email_rechazo database table.
 * 
 */
@Entity
@Table(name="email_rechazo")
@NamedQuery(name="EmailRechazo.findAll", query="SELECT e FROM EmailRechazo e")
public class EmailRechazo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmailRechazoPK id;

	public EmailRechazo() {
	}

	public EmailRechazoPK getId() {
		return this.id;
	}

	public void setId(EmailRechazoPK id) {
		this.id = id;
	}

}