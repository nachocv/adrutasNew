package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the album database table.
 * 
 */
@Embeddable
public class AlbumPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String salida;

	@Column(name="id_persona", insertable=false, updatable=false)
	private int idPersona;

	private int cont;

	public AlbumPK() {
	}
	public String getSalida() {
		return this.salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	public int getIdPersona() {
		return this.idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public int getCont() {
		return this.cont;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AlbumPK)) {
			return false;
		}
		AlbumPK castOther = (AlbumPK)other;
		return 
			this.salida.equals(castOther.salida)
			&& (this.idPersona == castOther.idPersona)
			&& (this.cont == castOther.cont);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.salida.hashCode();
		hash = hash * prime + this.idPersona;
		hash = hash * prime + this.cont;
		
		return hash;
	}
}