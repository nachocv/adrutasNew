package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the directiva database table.
 * 
 */
@Embeddable
public class DirectivaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_persona", insertable=false, updatable=false)
	private int idPersona;

	@Temporal(TemporalType.DATE)
	private java.util.Date alta;

	public DirectivaPK() {
	}
	public int getIdPersona() {
		return this.idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public java.util.Date getAlta() {
		return this.alta;
	}
	public void setAlta(java.util.Date alta) {
		this.alta = alta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DirectivaPK)) {
			return false;
		}
		DirectivaPK castOther = (DirectivaPK)other;
		return 
			(this.idPersona == castOther.idPersona)
			&& this.alta.equals(castOther.alta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersona;
		hash = hash * prime + this.alta.hashCode();
		
		return hash;
	}
}