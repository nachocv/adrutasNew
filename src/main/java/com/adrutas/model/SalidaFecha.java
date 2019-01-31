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
 * The persistent class for the salida_fecha database table.
 * 
 */
@Entity
@Table(name="salida_fecha")
@NamedQuery(name="SalidaFecha.findAll", query="SELECT s FROM SalidaFecha s")
public class SalidaFecha implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SalidaFechaPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to Salida
	@ManyToOne
	@JoinColumn(name="salida",insertable=false,updatable=false)
	private Salida salidaBean;

	//bi-directional many-to-one association to FechaTipo
	@ManyToOne
	@JoinColumn(name="fecha_tipo",insertable=false,updatable=false)
	private FechaTipo fechaTipoBean;

	public SalidaFecha() {
	}

	public SalidaFechaPK getId() {
		return this.id;
	}

	public void setId(SalidaFechaPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Salida getSalidaBean() {
		return this.salidaBean;
	}

	public void setSalidaBean(Salida salidaBean) {
		this.salidaBean = salidaBean;
	}

	public FechaTipo getFechaTipoBean() {
		return this.fechaTipoBean;
	}

	public void setFechaTipoBean(FechaTipo fechaTipoBean) {
		this.fechaTipoBean = fechaTipoBean;
	}

}