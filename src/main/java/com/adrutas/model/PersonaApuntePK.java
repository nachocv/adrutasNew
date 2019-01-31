package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the persona_apunte database table.
 * 
 */
@Embeddable
public class PersonaApuntePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_persona1")
	private int idPersona1;

	@Column(name="id_persona2")
	private int idPersona2;

	@Temporal(TemporalType.DATE)
	private java.util.Date inicio;

	@Temporal(TemporalType.DATE)
	private java.util.Date fin;

	public PersonaApuntePK() {
	}
	public int getIdPersona1() {
		return this.idPersona1;
	}
	public void setIdPersona1(int idPersona1) {
		this.idPersona1 = idPersona1;
	}
	public int getIdPersona2() {
		return this.idPersona2;
	}
	public void setIdPersona2(int idPersona2) {
		this.idPersona2 = idPersona2;
	}
	public java.util.Date getInicio() {
		return this.inicio;
	}
	public void setInicio(java.util.Date inicio) {
		this.inicio = inicio;
	}
	public java.util.Date getFin() {
		return this.fin;
	}
	public void setFin(java.util.Date fin) {
		this.fin = fin;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonaApuntePK)) {
			return false;
		}
		PersonaApuntePK castOther = (PersonaApuntePK)other;
		return 
			(this.idPersona1 == castOther.idPersona1)
			&& (this.idPersona2 == castOther.idPersona2)
			&& this.inicio.equals(castOther.inicio)
			&& this.fin.equals(castOther.fin);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersona1;
		hash = hash * prime + this.idPersona2;
		hash = hash * prime + this.inicio.hashCode();
		hash = hash * prime + this.fin.hashCode();
		
		return hash;
	}
}