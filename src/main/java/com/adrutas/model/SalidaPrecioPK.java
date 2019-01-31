package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the salida_precio database table.
 * 
 */
@Embeddable
public class SalidaPrecioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String salida;

	@Column(name="precio_tipo", insertable=false, updatable=false)
	private int precioTipo;

	public SalidaPrecioPK() {
	}
	public String getSalida() {
		return this.salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	public int getPrecioTipo() {
		return this.precioTipo;
	}
	public void setPrecioTipo(int precioTipo) {
		this.precioTipo = precioTipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SalidaPrecioPK)) {
			return false;
		}
		SalidaPrecioPK castOther = (SalidaPrecioPK)other;
		return 
			this.salida.equals(castOther.salida)
			&& (this.precioTipo == castOther.precioTipo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.salida.hashCode();
		hash = hash * prime + this.precioTipo;
		
		return hash;
	}
}