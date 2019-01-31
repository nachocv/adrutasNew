package com.adrutas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the precio_tipo database table.
 * 
 */
@Entity
@Table(name="precio_tipo")
@NamedQuery(name="PrecioTipo.findAll", query="SELECT p FROM PrecioTipo p")
public class PrecioTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="precio_tipo")
	private int precioTipo;

	private String descripcion;

	//bi-directional many-to-one association to SalidaPrecio
	@OneToMany(mappedBy="precioTipoBean")
	private List<SalidaPrecio> salidaPrecios;

	public PrecioTipo() {
	}

	public int getPrecioTipo() {
		return this.precioTipo;
	}

	public void setPrecioTipo(int precioTipo) {
		this.precioTipo = precioTipo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<SalidaPrecio> getSalidaPrecios() {
		return this.salidaPrecios;
	}

	public void setSalidaPrecios(List<SalidaPrecio> salidaPrecios) {
		this.salidaPrecios = salidaPrecios;
	}

	public SalidaPrecio addSalidaPrecio(SalidaPrecio salidaPrecio) {
		getSalidaPrecios().add(salidaPrecio);
		salidaPrecio.setPrecioTipoBean(this);

		return salidaPrecio;
	}

	public SalidaPrecio removeSalidaPrecio(SalidaPrecio salidaPrecio) {
		getSalidaPrecios().remove(salidaPrecio);
		salidaPrecio.setPrecioTipoBean(null);

		return salidaPrecio;
	}

}