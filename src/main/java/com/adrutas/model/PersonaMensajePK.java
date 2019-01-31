package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the persona_mensaje database table.
 * 
 */
@Embeddable
public class PersonaMensajePK implements Serializable {
	private static final long serialVersionUID = 113509628904170826L;

	@Column(name="id_persona")
	private int idPersona;

	private String salida;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private java.util.Date fechaAlta;

	public PersonaMensajePK() {
	}
	public int getIdPersona() {
		return this.idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getSalida() {
		return this.salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	public java.util.Date getFechaAlta() {
		return this.fechaAlta;
	}
	public void setFechaAlta(java.util.Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonaMensajePK)) {
			return false;
		}
		PersonaMensajePK castOther = (PersonaMensajePK)other;
		return 
			(this.idPersona == castOther.idPersona)
			&& this.salida.equals(castOther.salida)
			&& this.fechaAlta.equals(castOther.fechaAlta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersona;
		hash = hash * prime + this.salida.hashCode();
		hash = hash * prime + this.fechaAlta.hashCode();
		
		return hash;
	}
}