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
 * The persistent class for the album database table.
 * 
 */
@Entity@Table(name = "album")
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlbumPK id;

	@Lob
	private String url;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona",insertable=false,updatable=false)
	private Persona persona;

	//bi-directional many-to-one association to Salida
	@ManyToOne
	@JoinColumn(name="salida",insertable=false,updatable=false)
	private Salida salidaBean;

	public Album() {
	}

	public AlbumPK getId() {
		return this.id;
	}

	public void setId(AlbumPK id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Salida getSalidaBean() {
		return this.salidaBean;
	}

	public void setSalidaBean(Salida salidaBean) {
		this.salidaBean = salidaBean;
	}

}