package com.adrutas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the persona_mensaje database table.
 * 
 */
@Entity
@Table(name="persona_mensaje")
@NamedQuery(name="PersonaMensaje.findAll", query="SELECT p FROM PersonaMensaje p")
@NamedQuery(name="PersonaMensaje.find", query="SELECT m FROM PersonaMensaje m where id.salida<=:salida "
		+ "and id.idPersona=:idPersona order by salida desc,fecha_alta desc")
@NamedQuery(name="PersonaMensaje.findSalida", query="SELECT m FROM PersonaMensaje m "
		+ "where id.salida=:salida and id.idPersona=:idPersona")
public class PersonaMensaje implements Serializable {
	private static final long serialVersionUID = 8185790923177132543L;

	@EmbeddedId
	private PersonaMensajePK id;

	@Lob
	private String mensaje;

	public PersonaMensaje() {
	}

	public PersonaMensaje(String salida,int idPersona,Date fechaAlta,String mensaje) {
		id = new PersonaMensajePK();
		id.setFechaAlta(fechaAlta);
		id.setIdPersona(idPersona);
		id.setSalida(salida);
		this.mensaje = mensaje;
	}

	public PersonaMensajePK getId() {
		return this.id;
	}

	public void setId(PersonaMensajePK id) {
		this.id = id;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}