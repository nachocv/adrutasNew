package com.adrutas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adrutas.dao.EntityManagerFactories;


/**
 * The persistent class for the directiva database table.
 * 
 */
@Entity@Table(name = "directiva")
@NamedQuery(name="Directiva.findAll", query="SELECT d FROM Directiva d")
@NamedQuery(name="Directiva.findByIdPersona", query="SELECT d FROM Directiva d WHERE d.id.idPersona = :idPersona "
		+ "and (d.baja IS NULL or d.baja >= :now) and d.id.alta <= :now")
public class Directiva implements Serializable {
	private static final long serialVersionUID = 5459475126717397459L;
	private static final Logger log = Logger.getLogger(Directiva.class.getName());

	@EmbeddedId
	private DirectivaPK id;

	@Temporal(TemporalType.DATE)
	private Date baja;

	private int idPerfil;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona",insertable=false,updatable=false)
	private Persona persona;

	public Directiva() {
	}

	public DirectivaPK getId() {
		return this.id;
	}

	public void setId(DirectivaPK id) {
		this.id = id;
	}

	public Date getBaja() {
		return this.baja;
	}

	public void setBaja(Date baja) {
		this.baja = baja;
	}

	public int getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

    public static boolean isDirectivo(Integer idPersona) {
		EntityManager em = null;
        try {
    		em = EntityManagerFactories.getEM();
    		return !(em.createNamedQuery("Directiva.findByIdPersona", Directiva.class).setParameter("idPersona"
    				, idPersona).setParameter("now", new Date()).getResultList()).isEmpty();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Directiva.findByIdPersona", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
		return false;
    }
}