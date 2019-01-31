package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the socio_relacion database table.
 * 
 */
@Entity
@Table(name="socio_relacion")
@NamedQuery(name="SocioRelacion.findAll", query="SELECT s FROM SocioRelacion s")
public class SocioRelacion implements Serializable {
	private static final long serialVersionUID = -928773241623014227L;

	@EmbeddedId
	private SocioRelacionPK id;

	@Lob
	private String relacion;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona1",insertable=false,updatable=false)
	private Persona persona1;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona2",insertable=false,updatable=false)
	private Persona persona2;

	public SocioRelacion() {
	}

	public SocioRelacionPK getId() {
		return id;
	}

	public void setId(SocioRelacionPK id) {
		this.id = id;
	}

	public String getRelacion() {
		return this.relacion;
	}

	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	public Persona getPersona1() {
		return persona1;
	}

	public void setPersona1(Persona persona1) {
		this.persona1 = persona1;
	}

	public Persona getPersona2() {
		return persona2;
	}

	public void setPersona2(Persona persona2) {
		this.persona2 = persona2;
	}
}