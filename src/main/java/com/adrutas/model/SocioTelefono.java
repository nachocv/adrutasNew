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
 * The persistent class for the socio_telefono database table.
 * 
 */
@Entity
@Table(name="socio_telefono")
@NamedQuery(name="SocioTelefono.findAll", query="SELECT s FROM SocioTelefono s")
@NamedQuery(name="SocioTelefono.del", query="DELETE FROM SocioTelefono WHERE id.idPersona=:idPersona")
public class SocioTelefono implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SocioTelefonoPK id;

	@Lob
	private String nota;

	private int orden;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona",insertable=false,updatable=false)
	private Persona persona;

	public SocioTelefono() {
	}

	public SocioTelefonoPK getId() {
		return this.id;
	}

	public void setId(SocioTelefonoPK id) {
		this.id = id;
	}

	public String getNota() {
		return this.nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}