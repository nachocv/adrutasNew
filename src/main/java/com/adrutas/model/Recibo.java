package com.adrutas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the recibos database table.
 * 
 */
@Entity
@Table(name="recibos")
@NamedQuery(name="Recibo.findAll", query="SELECT r FROM Recibo r")
@NamedQuery(name="Recibo.getLast", query="SELECT r.idRecibo FROM Recibo r order by r.idRecibo desc")
public class Recibo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_recibo")
	private int idRecibo;

	private String directivo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private BigDecimal importe;

	private String tabla;

	//bi-directional many-to-one association to Ficha
	@OneToMany(mappedBy="recibo")
	private List<Ficha> fichas;

	//bi-directional many-to-one association to Formapago
	@ManyToOne
	@JoinColumn(name="estado")
	private Formapago formapago;

	//bi-directional many-to-one association to SalidaDetalle
	@OneToMany(mappedBy="recibo")
	private List<SalidaDetalle> salidaDetalles;

	public Recibo() {
	}

	public int getIdRecibo() {
		return this.idRecibo;
	}

	public void setIdRecibo(int idRecibo) {
		this.idRecibo = idRecibo;
	}

	public String getDirectivo() {
		return this.directivo;
	}

	public void setDirectivo(String directivo) {
		this.directivo = directivo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getTabla() {
		return this.tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public List<Ficha> getFichas() {
		return this.fichas;
	}

	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}

	public Ficha addFicha(Ficha ficha) {
		getFichas().add(ficha);
		ficha.setRecibo(this);

		return ficha;
	}

	public Ficha removeFicha(Ficha ficha) {
		getFichas().remove(ficha);
		ficha.setRecibo(null);

		return ficha;
	}

	public Formapago getFormapago() {
		return this.formapago;
	}

	public void setFormapago(Formapago formapago) {
		this.formapago = formapago;
	}

	public List<SalidaDetalle> getSalidaDetalles() {
		return this.salidaDetalles;
	}

	public void setSalidaDetalles(List<SalidaDetalle> salidaDetalles) {
		this.salidaDetalles = salidaDetalles;
	}

	public SalidaDetalle addSalidaDetalle(SalidaDetalle salidaDetalle) {
		getSalidaDetalles().add(salidaDetalle);
		salidaDetalle.setRecibo(this);

		return salidaDetalle;
	}

	public SalidaDetalle removeSalidaDetalle(SalidaDetalle salidaDetalle) {
		getSalidaDetalles().remove(salidaDetalle);
		salidaDetalle.setRecibo(null);

		return salidaDetalle;
	}

}