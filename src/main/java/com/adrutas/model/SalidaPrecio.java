package com.adrutas.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the salida_precio database table.
 * 
 */
@Entity
@Table(name="salida_precio")
@NamedQuery(name="SalidaPrecio.findAll", query="SELECT s FROM SalidaPrecio s")
public class SalidaPrecio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SalidaPrecioPK id;

	private BigDecimal precio;

	//bi-directional many-to-one association to Salida
	@ManyToOne
	@JoinColumn(name="salida",insertable=false,updatable=false)
	private Salida salidaBean;

	//bi-directional many-to-one association to PrecioTipo
	@ManyToOne
	@JoinColumn(name="precio_tipo",insertable=false,updatable=false)
	private PrecioTipo precioTipoBean;

	public SalidaPrecio() {
	}

	public SalidaPrecioPK getId() {
		return this.id;
	}

	public void setId(SalidaPrecioPK id) {
		this.id = id;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Salida getSalidaBean() {
		return this.salidaBean;
	}

	public void setSalidaBean(Salida salidaBean) {
		this.salidaBean = salidaBean;
	}

	public PrecioTipo getPrecioTipoBean() {
		return this.precioTipoBean;
	}

	public void setPrecioTipoBean(PrecioTipo precioTipoBean) {
		this.precioTipoBean = precioTipoBean;
	}

}