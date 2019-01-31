package com.adrutas.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ficha_opcion database table.
 * 
 */
@Entity
@Table(name="ficha_opcion")
@NamedQuery(name="FichaOpcion.findAll", query="SELECT f FROM FichaOpcion f")
@NamedQuery(name="FichaOpcion.del", query="DELETE FROM FichaOpcion WHERE id.idPersona=:idPersona and id.anyo=:anyo and id.idFicha=:idFicha")
public class FichaOpcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FichaOpcionPK id;

	public FichaOpcion() {
	}

	public FichaOpcion(int idPersona,int anyo,short idFicha,String tipoOpcion) {
		id = new FichaOpcionPK();
		id.setIdPersona(idPersona);
		id.setAnyo(anyo);
		id.setIdFicha(idFicha);
		id.setTipoOpcion(tipoOpcion);
	}

	public FichaOpcionPK getId() {
		return this.id;
	}

	public void setId(FichaOpcionPK id) {
		this.id = id;
	}

}