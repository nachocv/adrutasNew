package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the propiedades_anuales database table.
 * 
 */
@Entity
@Table(name="propiedades_anuales")
@NamedQuery(name="PropiedadesAnuale.findAll", query="SELECT p FROM PropiedadesAnuale p")
public class PropiedadesAnuale implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PropiedadesAnualePK id;

	private String valor;

	public PropiedadesAnuale() {
	}

	public PropiedadesAnualePK getId() {
		return this.id;
	}

	public void setId(PropiedadesAnualePK id) {
		this.id = id;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}