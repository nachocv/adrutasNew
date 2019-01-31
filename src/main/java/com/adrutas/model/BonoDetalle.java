package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the bono_detalle database table.
 * 
 */
@Entity
@Table(name="bono_detalle")
@NamedQuery(name="BonoDetalle.findAll", query="SELECT b FROM BonoDetalle b")
@NamedQuery(name="BonoDetalle.getUltimo", query="SELECT b FROM BonoDetalle b WHERE idPersona=:idPersona ORDER BY bono desc,uso desc")
@NamedQuery(name="BonoDetalle.getSalida", query="SELECT b FROM BonoDetalle b WHERE salidaBean.salida=:salida")
@NamedQuery(name="BonoDetalle.findByIdPersona", query="SELECT b FROM BonoDetalle b WHERE salidaBean.salida=:salida and id_persona=:idPersona")
public class BonoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BonoDetallePK id;

	@Column(name="id_persona")
	private int idPersona;

	//bi-directional many-to-one association to Salida
	@ManyToOne
	@JoinColumn(name="salida")
	private Salida salidaBean;

	public BonoDetalle() {
	}

	public BonoDetalle(short bono,short uso,Salida salidaBean,int idPersona) {
		id = new BonoDetallePK();
		id.setBono(bono);
		id.setUso(uso);
		this.salidaBean = salidaBean;
		this.idPersona = idPersona;
	}

	public BonoDetallePK getId() {
		return this.id;
	}

	public void setId(BonoDetallePK id) {
		this.id = id;
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public Salida getSalidaBean() {
		return this.salidaBean;
	}

	public void setSalidaBean(Salida salidaBean) {
		this.salidaBean = salidaBean;
	}
}