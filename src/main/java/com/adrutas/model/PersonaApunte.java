package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the persona_apunte database table.
 * 
 */
@Entity
@Table(name="persona_apunte")
@NamedQuery(name="PersonaApunte.findAll", query="SELECT p FROM PersonaApunte p")
public class PersonaApunte implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonaApuntePK id;

	public PersonaApunte() {
	}

	public PersonaApuntePK getId() {
		return this.id;
	}

	public void setId(PersonaApuntePK id) {
		this.id = id;
	}

}