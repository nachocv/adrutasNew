package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the ficha_estado database table.
 * 
 */
@Embeddable
public class FichaEstadoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_persona")
	private int idPersona;

	private int anyo;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fecha;

	public FichaEstadoPK() {
	}
	public int getIdPersona() {
		return this.idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public int getAnyo() {
		return this.anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	public String getEstado() {
		return this.estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
		if (!(other instanceof FichaEstadoPK)) {
			return false;
		}
		FichaEstadoPK castOther = (FichaEstadoPK)other;
		return 
			(this.idPersona == castOther.idPersona)
			&& (this.anyo == castOther.anyo)
			&& this.estado.equals(castOther.estado)
			&& this.fecha.equals(castOther.fecha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersona;
		hash = hash * prime + this.anyo;
		hash = hash * prime + this.estado.hashCode();
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
	}
}