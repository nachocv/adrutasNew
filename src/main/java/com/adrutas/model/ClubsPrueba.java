package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the clubs_prueba database table.
 * 
 */
@Entity
@Table(name="clubs_prueba")
@NamedQuery(name="ClubsPrueba.findAll", query="SELECT c FROM ClubsPrueba c")
public class ClubsPrueba implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String club;

	private String email;

	public ClubsPrueba() {
	}

	public String getClub() {
		return this.club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}