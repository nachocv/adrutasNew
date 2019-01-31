package com.adrutas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the bono database table.
 * 
 */
@Entity@Table(name = "bono")
@NamedQuery(name="Bono.findAll", query="SELECT b FROM Bono b")
public class Bono implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BonoPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Column(name="id_persona")
	private int idPersona;

	public Bono() {
	}

	public BonoPK getId() {
		return this.id;
	}

	public void setId(BonoPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

}