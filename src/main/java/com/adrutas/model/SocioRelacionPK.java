package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the album database table.
 * 
 */
@Embeddable
public class SocioRelacionPK implements Serializable {
	private static final long serialVersionUID = -6672911255783561443L;

	@Column(name="id_persona1", insertable=false, updatable=false)
	private int id_persona1;

	@Column(name="id_persona2", insertable=false, updatable=false)
	private int id_persona2;

	public SocioRelacionPK() {
	}

	public int getId_persona1() {
		return id_persona1;
	}

	public void setId_persona1(int id_persona1) {
		this.id_persona1 = id_persona1;
	}

	public int getId_persona2() {
		return id_persona2;
	}

	public void setId_persona2(int id_persona2) {
		this.id_persona2 = id_persona2;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SocioRelacionPK)) {
			return false;
		}
		SocioRelacionPK castOther = (SocioRelacionPK)other;
		return 
			this.id_persona1 == castOther.id_persona1
			&& this.id_persona2 == castOther.id_persona2;
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id_persona1;
		hash = hash * prime + this.id_persona2;

		return hash;
	}
}