package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the ficha database table.
 * 
 */
@Embeddable
public class FichaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_persona", insertable=false, updatable=false)
	private int idPersona;

	private int anyo;

	@Column(name="id_ficha")
	private short idFicha;

	public FichaPK() {
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
	public short getIdFicha() {
		return this.idFicha;
	}
	public void setIdFicha(short idFicha) {
		this.idFicha = idFicha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FichaPK)) {
			return false;
		}
		FichaPK castOther = (FichaPK)other;
		return 
			(this.idPersona == castOther.idPersona)
			&& (this.anyo == castOther.anyo)
			&& (this.idFicha == castOther.idFicha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersona;
		hash = hash * prime + this.anyo;
		hash = hash * prime + ((int) this.idFicha);
		
		return hash;
	}
}