package com.adrutas.model;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the formapago database table.
 * 
 */
@Entity@Table(name = "formapago")
@NamedQuery(name="Formapago.findAll", query="SELECT f FROM Formapago f")
@NamedQuery(name="Formapago.findFicha", query="SELECT f FROM Formapago f WHERE ficha='S'")
public class Formapago implements Serializable {
	private static final long serialVersionUID = -499274925687192274L;
	private static final Logger log = Logger.getLogger(Formapago.class.getName());

	@Id
	private String codigo;

	private String descripcion;

	private String ficha;

	//bi-directional many-to-one association to Recibo
	@OneToMany(mappedBy="formapago")
	private List<Recibo> recibos;

	public Formapago() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFicha() {
		return this.ficha;
	}

	public void setFicha(String ficha) {
		this.ficha = ficha;
	}

	public List<Recibo> getRecibos() {
		return this.recibos;
	}

	public void setRecibos(List<Recibo> recibos) {
		this.recibos = recibos;
	}

	public Recibo addRecibo(Recibo recibo) {
		getRecibos().add(recibo);
		recibo.setFormapago(this);

		return recibo;
	}

	public Recibo removeRecibo(Recibo recibo) {
		getRecibos().remove(recibo);
		recibo.setFormapago(null);

		return recibo;
	}
}