package com.adrutas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the socio_email database table.
 * 
 */
@Entity
@Table(name="socio_email")
@NamedQuery(name="SocioEmail.findAll", query="SELECT s FROM SocioEmail s")
@NamedQuery(name="SocioEmail.del", query="DELETE FROM SocioEmail WHERE id.idPersona=:idPersona")
public class SocioEmail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SocioEmailPK id;

	@Temporal(TemporalType.DATE)
	private Date borrado;

	@Lob
	private String nota;

	private int orden;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona",insertable=false,updatable=false)
	private Persona persona;

	public SocioEmail() {
	}

	public SocioEmailPK getId() {
		return this.id;
	}

	public void setId(SocioEmailPK id) {
		this.id = id;
	}

	public Date getBorrado() {
		return this.borrado;
	}

	public void setBorrado(Date borrado) {
		this.borrado = borrado;
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