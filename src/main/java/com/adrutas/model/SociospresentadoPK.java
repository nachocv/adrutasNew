package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the album database table.
 * 
 */
@Embeddable
public class SociospresentadoPK implements Serializable {
	private static final long serialVersionUID = -6672911255783561443L;

	@Column(name="id_persona1", insertable=false, updatable=false)
	private int idPersona1;

	@Column(name="id_persona2", insertable=false, updatable=false)
	private int idPersona2;

	public SociospresentadoPK() {
	}

	public int getIdPersona1() {
		return idPersona1;
	}

	public void setIdPersona1(int idPersona1) {
		this.idPersona1 = idPersona1;
	}

	public int getIdPersona2() {
		return idPersona2;
	}

	public void setIdPersona2(int idPersona2) {
		this.idPersona2 = idPersona2;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SociospresentadoPK)) {
			return false;
		}
		SociospresentadoPK castOther = (SociospresentadoPK)other;
		return 
			this.idPersona1 == castOther.idPersona2
			&& this.idPersona2 == castOther.idPersona2;
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersona1;
		hash = hash * prime + this.idPersona2;

		return hash;
	}
}