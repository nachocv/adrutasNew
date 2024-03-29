package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the bono database table.
 * 
 */
@Embeddable
public class BonoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private short bono;

	private short uso;

	public BonoPK() {
	}
	public short getBono() {
		return this.bono;
	}
	public void setBono(short bono) {
		this.bono = bono;
	}
	public short getUso() {
		return this.uso;
	}
	public void setUso(short uso) {
		this.uso = uso;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BonoPK)) {
			return false;
		}
		BonoPK castOther = (BonoPK)other;
		return 
			(this.bono == castOther.bono)
			&& (this.uso == castOther.uso);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) this.bono);
		hash = hash * prime + ((int) this.uso);
		
		return hash;
	}
}