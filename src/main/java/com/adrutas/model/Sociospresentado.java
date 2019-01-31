package com.adrutas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the sociospresentados database table.
 * 
 */
@Entity
@Table(name="sociospresentados")
@NamedQuery(name="Sociospresentado.findAll", query="SELECT s FROM Sociospresentado s")
public class Sociospresentado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SociospresentadoPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String salidaCompensada;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona1",insertable=false,updatable=false)
	private Persona persona1;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona2",insertable=false,updatable=false)
	private Persona persona2;

	public Sociospresentado() {
	}

	public SociospresentadoPK getId() {
		return id;
	}

	public void setId(SociospresentadoPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getSalidaCompensada() {
		return this.salidaCompensada;
	}

	public void setSalidaCompensada(String salidaCompensada) {
		this.salidaCompensada = salidaCompensada;
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