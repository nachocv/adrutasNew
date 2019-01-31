package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the email_rechazo database table.
 * 
 */
@Embeddable
public class EmailRechazoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fecha;

	public EmailRechazoPK() {
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.util.Date getFecha() {
		return this.fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmailRechazoPK)) {
			return false;
		}
		EmailRechazoPK castOther = (EmailRechazoPK)other;
		return 
			this.email.equals(castOther.email)
			&& this.fecha.equals(castOther.fecha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.email.hashCode();
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
	}
}