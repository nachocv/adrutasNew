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
 * The persistent class for the fecha_tipo database table.
 * 
 */
@Entity
@Table(name="fecha_tipo")
@NamedQuery(name="FechaTipo.findAll", query="SELECT f FROM FechaTipo f")
public class FechaTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="fecha_tipo")
	private int fechaTipo;

	private String descripcion;

	//bi-directional many-to-one association to SalidaFecha
	@OneToMany(mappedBy="fechaTipoBean")
	private List<SalidaFecha> salidaFechas;

	public FechaTipo() {
	}

	public int getFechaTipo() {
		return this.fechaTipo;
	}

	public void setFechaTipo(int fechaTipo) {
		this.fechaTipo = fechaTipo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<SalidaFecha> getSalidaFechas() {
		return this.salidaFechas;
	}

	public void setSalidaFechas(List<SalidaFecha> salidaFechas) {
		this.salidaFechas = salidaFechas;
	}

	public SalidaFecha addSalidaFecha(SalidaFecha salidaFecha) {
		getSalidaFechas().add(salidaFecha);
		salidaFecha.setFechaTipoBean(this);

		return salidaFecha;
	}

	public SalidaFecha removeSalidaFecha(SalidaFecha salidaFecha) {
		getSalidaFechas().remove(salidaFecha);
		salidaFecha.setFechaTipoBean(null);

		return salidaFecha;
	}

}