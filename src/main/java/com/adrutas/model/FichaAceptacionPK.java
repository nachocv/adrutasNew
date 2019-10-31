package com.adrutas.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ficha_aceptacion database table.
 * 
 */
@Embeddable
public class FichaAceptacionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_persona")
	private int idPersona;

	private int anyo;

	@Column(name="id_ficha")
	private short idFicha;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fecha;

	public FichaAceptacionPK() {
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
		if (!(other instanceof FichaAceptacionPK)) {
			return false;
		}
		FichaAceptacionPK castOther = (FichaAceptacionPK)other;
		return 
			(this.idPersona == castOther.idPersona)
			&& (this.anyo == castOther.anyo)
			&& (this.idFicha == castOther.idFicha)
			&& this.fecha.equals(castOther.fecha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersona;
		hash = hash * prime + this.anyo;
		hash = hash * prime + ((int) this.idFicha);
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
	}
}