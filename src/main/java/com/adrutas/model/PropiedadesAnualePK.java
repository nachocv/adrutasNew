package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the propiedades_anuales database table.
 * 
 */
@Embeddable
public class PropiedadesAnualePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int anyo;

	private String propiedad;

	public PropiedadesAnualePK() {
	}
	public int getAnyo() {
		return this.anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	public String getPropiedad() {
		return this.propiedad;
	}
	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PropiedadesAnualePK)) {
			return false;
		}
		PropiedadesAnualePK castOther = (PropiedadesAnualePK)other;
		return 
			(this.anyo == castOther.anyo)
			&& this.propiedad.equals(castOther.propiedad);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.anyo;
		hash = hash * prime + this.propiedad.hashCode();
		
		return hash;
	}
}