package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the ficha_opcion database table.
 * 
 */
@Embeddable
public class FichaOpcionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_persona")
	private int idPersona;

	@Column(name="anyo")
	private int anyo;

	@Column(name="id_ficha")
	private short idFicha;

	@Column(name="tipo_opcion")
	private String tipoOpcion;

	public FichaOpcionPK() {
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
	public String getTipoOpcion() {
		return this.tipoOpcion;
	}
	public void setTipoOpcion(String tipoOpcion) {
		this.tipoOpcion = tipoOpcion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FichaOpcionPK)) {
			return false;
		}
		FichaOpcionPK castOther = (FichaOpcionPK)other;
		return 
			(this.idPersona == castOther.idPersona)
			&& (this.anyo == castOther.anyo)
			&& (this.idFicha == castOther.idFicha)
			&& this.tipoOpcion.equals(castOther.tipoOpcion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersona;
		hash = hash * prime + this.anyo;
		hash = hash * prime + ((int) this.idFicha);
		hash = hash * prime + this.tipoOpcion.hashCode();
		
		return hash;
	}
}