package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the licencia_opciones database table.
 * 
 */
@Embeddable
public class LicenciaOpcionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="tipo_licencia", insertable=false, updatable=false)
	private String tipoLicencia;

	@Column(insertable=false, updatable=false)
	private int anyo;

	@Column(name="tipo_opcion")
	private String tipoOpcion;

	public LicenciaOpcionePK() {
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
		if (!(other instanceof LicenciaOpcionePK)) {
			return false;
		}
		LicenciaOpcionePK castOther = (LicenciaOpcionePK)other;
		return 
			this.tipoLicencia.equals(castOther.tipoLicencia)
			&& (this.anyo == castOther.anyo)
			&& this.tipoOpcion.equals(castOther.tipoOpcion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tipoLicencia.hashCode();
		hash = hash * prime + this.anyo;
		hash = hash * prime + this.tipoOpcion.hashCode();
		
		return hash;
	}
}