package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cuotas database table.
 * 
 */
@Entity
@Table(name="cuotas")
@NamedQuery(name="Cuota.findAll", query="SELECT c FROM Cuota c")
public class Cuota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String anyo;

	private float cuota1;

	private float cuota2;

	public Cuota() {
	}

	public String getAnyo() {
		return this.anyo;
	}

	public void setAnyo(String anyo) {
		this.anyo = anyo;
	}

	public float getCuota1() {
		return this.cuota1;
	}

	public void setCuota1(float cuota1) {
		this.cuota1 = cuota1;
	}

	public float getCuota2() {
		return this.cuota2;
	}

	public void setCuota2(float cuota2) {
		this.cuota2 = cuota2;
	}

}