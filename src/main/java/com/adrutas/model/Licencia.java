package com.adrutas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the licencias database table.
 * 
 */
@Entity
@Table(name="licencias")
@NamedQuery(name="Licencia.findAll", query="SELECT l FROM Licencia l")
public class Licencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LicenciaPK id;

	@Lob
	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fin;

	private BigDecimal importe;

	@Column(name="importe_joven")
	private BigDecimal importeJoven;

	@Column(name="importe_menor")
	private BigDecimal importeMenor;

	@Temporal(TemporalType.DATE)
	private Date inicio;

	private String nombre;

	@Column(name="tipo_licencia_cont")
	private Integer tipoLicenciaCont;

	//bi-directional many-to-one association to LicenciaOpcione
	@OneToMany(mappedBy="licencia")
	private List<LicenciaOpcione> licenciaOpciones;

	public Licencia() {
	}

	public LicenciaPK getId() {
		return this.id;
	}

	public void setId(LicenciaPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFin() {
		return this.fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getImporteJoven() {
		return importeJoven;
	}

	public void setImporteJoven(BigDecimal importeJoven) {
		this.importeJoven = importeJoven;
	}

	public BigDecimal getImporteMenor() {
		return this.importeMenor;
	}

	public void setImporteMenor(BigDecimal importeMenor) {
		this.importeMenor = importeMenor;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getTipoLicenciaCont() {
		return this.tipoLicenciaCont;
	}

	public void setTipoLicenciaCont(Integer tipoLicenciaCont) {
		this.tipoLicenciaCont = tipoLicenciaCont;
	}

	public List<LicenciaOpcione> getLicenciaOpciones() {
		return this.licenciaOpciones;
	}

	public void setLicenciaOpciones(List<LicenciaOpcione> licenciaOpciones) {
		this.licenciaOpciones = licenciaOpciones;
	}

	public LicenciaOpcione addLicenciaOpcione(LicenciaOpcione licenciaOpcione) {
		getLicenciaOpciones().add(licenciaOpcione);
		licenciaOpcione.setLicencia(this);

		return licenciaOpcione;
	}

	public LicenciaOpcione removeLicenciaOpcione(LicenciaOpcione licenciaOpcione) {
		getLicenciaOpciones().remove(licenciaOpcione);
		licenciaOpcione.setLicencia(null);

		return licenciaOpcione;
	}

}