package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the licencias database table.
 * 
 */
@Embeddable
public class LicenciaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="tipo_licencia")
	private String tipoLicencia;

	private int anyo;

	public LicenciaPK() {
	}
	public String getTipoLicencia() {
		return this.tipoLicencia;
	}
	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
	public int getAnyo() {
		return this.anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LicenciaPK)) {
			return false;
		}
		LicenciaPK castOther = (LicenciaPK)other;
		return 
			this.tipoLicencia.equals(castOther.tipoLicencia)
			&& (this.anyo == castOther.anyo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tipoLicencia.hashCode();
		hash = hash * prime + this.anyo;
		
		return hash;
	}
}