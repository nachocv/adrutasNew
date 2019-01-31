package com.adrutas.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the licencia_opciones database table.
 * 
 */
@Entity
@Table(name="licencia_opciones")
@NamedQuery(name="LicenciaOpcione.findAll", query="SELECT l FROM LicenciaOpcione l")
public class LicenciaOpcione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LicenciaOpcionePK id;

	private BigDecimal importe;

	private String nombre;

	//bi-directional many-to-one association to Licencia
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="anyo", referencedColumnName="anyo",insertable=false,updatable=false),
		@JoinColumn(name="tipo_licencia", referencedColumnName="tipo_licencia",insertable=false,updatable=false)
		})
	private Licencia licencia;

	public LicenciaOpcione() {
	}

	public LicenciaOpcionePK getId() {
		return this.id;
	}

	public void setId(LicenciaOpcionePK id) {
		this.id = id;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Licencia getLicencia() {
		return this.licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}

}