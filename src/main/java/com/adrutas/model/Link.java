package com.adrutas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the link database table.
 * 
 */
@Entity@Table(name = "link")
@NamedQuery(name="Link.findAll", query="SELECT l FROM Link l")
@NamedQuery(name="Link.count",query="select count(l) from Link l where link = :link")
@NamedQuery(name="Link.find",query="select l from Link l where link = :link")
@NamedQuery(name="Link.findByPersona",query="select l from Link l where persona.idPersona = :idPersona")
@NamedQuery(name="Link.deletePersona", query="DELETE Link l WHERE persona.idPersona=:idPersona")
@NamedQuery(name="Link.delete", query="DELETE Link l WHERE fecha<:fecha")
@NamedQuery(name="Link.findOld", query="select l from Link l WHERE fecha<:fecha")
public class Link implements Serializable {

	@Id
	private String link;

//	@Column(name="id_persona")
//	private int idPersona;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="id_persona")
	private Persona persona;

	public Link() {
	}

//	public int getIdPersona() {
//		return this.idPersona;
//	}
//
//	public void setIdPersona(int idPersona) {
//		this.idPersona = idPersona;
//	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public static void deleteOlds(EntityManager em) {
        em.createNamedQuery("Link.delete").setParameter("fecha",new Date()).executeUpdate();
	}
}