package com.adrutas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.dao.Static;


/**
 * The persistent class for the ficha database table.
 * 
 */
@Entity@Table(name = "ficha")
@NamedQuery(name="Ficha.findAll", query="SELECT f FROM Ficha f")
@NamedQuery(name="Ficha.find",
query="SELECT f FROM Ficha f WHERE f.id.idPersona=:idPersona and f.id.anyo=:anyo and f.id.idFicha=:idFicha")
public class Ficha implements Serializable {
	private static final long serialVersionUID = 6097850077775371167L;

	private static final Logger log = Logger.getLogger(Ficha.class.getName());

	@EmbeddedId
	private FichaPK id;

	private byte adulto;

	private String club;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String fp;

	private BigDecimal importecuota;

	private BigDecimal importelicencia;

	private byte regalo;

	@Column(name="tipo_licencia")
	private String tipoLicencia;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona",insertable=false,updatable=false)
	private Persona persona;

	//bi-directional many-to-one association to Recibo
	@ManyToOne
	@JoinColumn(name="id_recibo")
	private Recibo recibo;

	//bi-directional many-to-one association to Album
	@OneToMany
	@JoinColumns({    
		@JoinColumn(name = "id_persona", referencedColumnName = "id_persona"),
		@JoinColumn(name = "anyo", referencedColumnName = "anyo"),
		@JoinColumn(name = "id_ficha", referencedColumnName = "id_ficha")
	})
	private List<FichaOpcion> opciones;

	public Ficha() {
	}

	public FichaPK getId() {
		return this.id;
	}

	public void setId(FichaPK id) {
		this.id = id;
	}

	public byte getAdulto() {
		return this.adulto;
	}

	public void setAdulto(byte adulto) {
		this.adulto = adulto;
	}

	public String getClub() {
		return this.club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFp() {
		return this.fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
	}

	public BigDecimal getImportecuota() {
		return this.importecuota;
	}

	public void setImportecuota(BigDecimal importecuota) {
		this.importecuota = importecuota;
	}

	public BigDecimal getImportelicencia() {
		return this.importelicencia;
	}

	public void setImportelicencia(BigDecimal importelicencia) {
		this.importelicencia = importelicencia;
	}

	public byte getRegalo() {
		return this.regalo;
	}

	public void setRegalo(byte regalo) {
		this.regalo = regalo;
	}

	public String getTipoLicencia() {
		return this.tipoLicencia;
	}

	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Recibo getRecibo() {
		return this.recibo;
	}

	public void setRecibo(Recibo recibo) {
		this.recibo = recibo;
	}

	public List<FichaOpcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<FichaOpcion> opciones) {
		this.opciones = opciones;
	}

	public static Ficha find(EntityManager em,int idPersona,int anyo,short idFicha) {
		Ficha ficha = null;
		List<Ficha> list = em.createNamedQuery("Ficha.find", Ficha.class)
				.setParameter("idPersona", idPersona)
				.setParameter("anyo", anyo)
				.setParameter("idFicha", idFicha).getResultList();
		if (!list.isEmpty()) {
			ficha = list.get(0);
			ficha.getPersona().getSocioEmails().iterator();
			ficha.getPersona().getSocioTelefonos().iterator();
			ficha.getOpciones().iterator();
			ficha.getRecibo().getFormapago();
		}
		return ficha;
	}

	public static Ficha find (int idPersona,int anyo,short idFicha) {
		EntityManager em = null;
		Ficha ficha = null;
        try {
    		em = EntityManagerFactories.getEM();
    		ficha = find(em,idPersona,anyo,idFicha);
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No updata", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
        return ficha;
	}

	public static synchronized Ficha update(Map<String, Object> map) {
		Ficha ficha = null;
		EntityManager em = null;
        try {
    		FichaPK fichaPK = null;
    		Recibo recibo = null;
    		BigDecimal importecuota = null;
    		BigDecimal importelicencia = null;
    		String formapago = null;
    		int idPersona = Integer.parseInt((String) map.get("id_persona"));
    		int anyo = Integer.parseInt((String) map.get("anyo"));
    		short idFicha = 0;
    		Date fecha = new Date();
    		em = EntityManagerFactories.getEM();
			em.getTransaction().begin();
    		int idRecibo = em.createNamedQuery("Recibo.getLast", Integer.class).setMaxResults(1).getSingleResult() + 1;
    		ficha = find(em,idPersona,anyo,idFicha);
    		recibo = new Recibo();
    		recibo.setIdRecibo(idRecibo);
    		recibo.setFecha(fecha);
    		recibo.setFormapago(Static.getMformapago().get(formapago = (String) map.get("fp")));
    		recibo.setTabla("ficha");
    		recibo.setImporte((importecuota = new BigDecimal((String) map.get("importecuota"))).
    				add(importelicencia = new BigDecimal((String) map.get("importelicencia"))));
    		recibo.setDirectivo("0367");
			em.persist(recibo);
			if (ficha==null) {
    			ficha = new Ficha();
    			ficha.setId(fichaPK = new FichaPK());
    			fichaPK.setIdPersona(idPersona);
    			fichaPK.setAnyo(anyo);
    			fichaPK.setIdFicha(idFicha);
        		ficha.setFecha(fecha);
    			em.persist(ficha);
    		} else {
        		ficha.setFecha(fecha);
    		}
    		ficha.setTipoLicencia((String) map.get("tipo_licencia"));
    		ficha.setClub((String) map.get("club"));
    		ficha.setFp(formapago);
    		ficha.setRegalo((byte)((Boolean) map.get("regalo")? 1: 0));
    		ficha.setImportecuota(importecuota);
    		ficha.setImportelicencia(importelicencia);
			ficha.setRecibo(recibo);
			em.createNamedQuery("FichaOpcion.del").setParameter("idPersona",idPersona).
				setParameter("anyo",anyo).setParameter("idFicha",idFicha).executeUpdate();
			for (String opcion: (List<String>) map.get("opciones")) {
    			em.persist(new FichaOpcion(idPersona,anyo,idFicha,opcion));
			}
			em.getTransaction().commit();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No updata", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
        return ficha;
    }
}