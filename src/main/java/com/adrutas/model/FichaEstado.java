package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ficha_estado database table.
 * 
 */
@Entity
@Table(name="ficha_estado")
@NamedQuery(name="FichaEstado.findAll", query="SELECT f FROM FichaEstado f")
public class FichaEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FichaEstadoPK id;

	public FichaEstado() {
	}

	public FichaEstadoPK getId() {
		return this.id;
	}

	public void setId(FichaEstadoPK id) {
		this.id = id;
	}

}