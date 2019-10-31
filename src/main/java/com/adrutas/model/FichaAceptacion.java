package com.adrutas.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ficha_aceptacion database table.
 * 
 */
@Entity
@Table(name="ficha_aceptacion")
@NamedQuery(name="FichaAceptacion.findAll", query="SELECT f FROM FichaAceptacion f")
public class FichaAceptacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FichaAceptacionPK id;

	private byte aceptado;

	//bi-directional many-to-one association to Ficha
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="anyo", referencedColumnName="anyo"),
		@JoinColumn(name="id_ficha", referencedColumnName="id_ficha"),
		@JoinColumn(name="id_persona", referencedColumnName="id_persona")
		})
	private Ficha ficha;

	public FichaAceptacion() {
	}

	public FichaAceptacionPK getId() {
		return this.id;
	}

	public void setId(FichaAceptacionPK id) {
		this.id = id;
	}

	public byte getAceptado() {
		return this.aceptado;
	}

	public void setAceptado(byte aceptado) {
		this.aceptado = aceptado;
	}

	public Ficha getFicha() {
		return this.ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

}