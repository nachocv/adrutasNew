package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the socio_telefono database table.
 * 
 */
@Embeddable
public class SocioTelefonoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_persona", insertable=false, updatable=false)
	private int idPersona;

	private String telefono;

	public SocioTelefonoPK() {
	}
	public int getIdPersona() {
		return this.idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getTelefono() {
		return this.telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SocioTelefonoPK)) {
			return false;
		}
		SocioTelefonoPK castOther = (SocioTelefonoPK)other;
		return 
			(this.idPersona == castOther.idPersona)
			&& this.telefono.equals(castOther.telefono);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersona;
		hash = hash * prime + this.telefono.hashCode();
		
		return hash;
	}
}