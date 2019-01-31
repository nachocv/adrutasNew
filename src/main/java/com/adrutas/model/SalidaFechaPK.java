package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the salida_fecha database table.
 * 
 */
@Embeddable
public class SalidaFechaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String salida;

	@Column(name="fecha_tipo", insertable=false, updatable=false)
	private int fechaTipo;

	public SalidaFechaPK() {
	}
	public String getSalida() {
		return this.salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	public int getFechaTipo() {
		return this.fechaTipo;
	}
	public void setFechaTipo(int fechaTipo) {
		this.fechaTipo = fechaTipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SalidaFechaPK)) {
			return false;
		}
		SalidaFechaPK castOther = (SalidaFechaPK)other;
		return 
			this.salida.equals(castOther.salida)
			&& (this.fechaTipo == castOther.fechaTipo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.salida.hashCode();
		hash = hash * prime + this.fechaTipo;
		
		return hash;
	}
}