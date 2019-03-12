package com.adrutas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.dao.Static;

import adrutas.com.Constante;

/**
 * The persistent class for the salida database table.
 * 
 */
@Entity@Table(name = "salida")
@NamedQuery(name="Salida.findAll", query="SELECT s FROM Salida s ORDER BY s.salida DESC")
@NamedQuery(name="Salida.findBySalida", query="SELECT s FROM Salida s WHERE s.salida=:salida")
@NamedQuery(name="Salida.quedanPlazas", query="SELECT s FROM Salida s LEFT JOIN s.salidaDetalles")
@NamedQuery(name="Salida.findByDate", query="SELECT s FROM Salida s LEFT JOIN s.salidaFechas f WHERE "
		+ "f.fechaTipoBean.fechaTipo=2 and f.fecha>=:date ORDER BY f.fecha")

public class Salida implements Serializable {
	private static final long serialVersionUID = 8476007606083178837L;
	private static final Logger log = Logger.getLogger(Salida.class.getName());
	@Id
	private String salida;

	private Integer anyo;

	private String bajadac;

	private String bajadal;

	private String contabilizada;

	private String cuadruples;

	private String descripcion;

	private String distanciac;

	private String distancial;

	private String dobles;

	private String escala;

	private String horariocorto;

	private String horariolargo;

	private Short listaE;

	private String mapa;

	private Short ocupadas;

	private Byte ocupadasJD;

	private Byte plazas;

	@Lob
	private String portada;

	private String recogida;

	private String recorridoLargo;

	private String recrridoCorto;

	private String responsables;

	private Byte rjd;

	private Byte salidaBono;

	private Byte salidaContabilizada;

	private String simples;

	private String subidac;

	private String subidal;

	private String textoSeguro;

	private String tipo;

	private String triples;

	@Lob
	private String url;

	@Lob
	private String urlPortada;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="salidaBean")
	private List<Album> albums;

	//bi-directional many-to-one association to BonoDetalle
	@OneToMany(mappedBy="salidaBean")
	private List<BonoDetalle> bonoDetalles;

	//bi-directional many-to-one association to SalidaDetalle
	@OneToMany(mappedBy="salidaBean")
	private List<SalidaDetalle> salidaDetalles;

	//bi-directional many-to-one association to SalidaFecha
	@OneToMany(mappedBy="salidaBean")
	private List<SalidaFecha> salidaFechas;

	//bi-directional many-to-one association to SalidaPrecio
	@OneToMany(mappedBy="salidaBean")
	private List<SalidaPrecio> salidaPrecios;

	@Transient
	private Date fechaInicio;

	@Transient
	private Date fechaFin;

	@Transient
	private Date fechaApunte;

	@Transient
	private Date fechaCierre;

	@Transient
	private List<Salida> fotosPortada = null;

	public Salida() {
	}

	public Salida(String salida, Date fechaInicio, String descripcion) {
		this.salida = salida;
		this.fechaInicio = fechaInicio;
		this.descripcion = descripcion;
	}

	public String getSalida() {
		return this.salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public Integer getAnyo() {
		return this.anyo;
	}

	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}

	public String getBajadac() {
		return this.bajadac;
	}

	public void setBajadac(String bajadac) {
		this.bajadac = bajadac;
	}

	public String getBajadal() {
		return this.bajadal;
	}

	public void setBajadal(String bajadal) {
		this.bajadal = bajadal;
	}

	public String getContabilizada() {
		return this.contabilizada;
	}

	public void setContabilizada(String contabilizada) {
		this.contabilizada = contabilizada;
	}

	public String getCuadruples() {
		return this.cuadruples;
	}

	public void setCuadruples(String cuadruples) {
		this.cuadruples = cuadruples;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDistanciac() {
		return this.distanciac;
	}

	public void setDistanciac(String distanciac) {
		this.distanciac = distanciac;
	}

	public String getDistancial() {
		return this.distancial;
	}

	public void setDistancial(String distancial) {
		this.distancial = distancial;
	}

	public String getDobles() {
		return this.dobles;
	}

	public void setDobles(String dobles) {
		this.dobles = dobles;
	}

	public String getEscala() {
		return this.escala;
	}

	public void setEscala(String escala) {
		this.escala = escala;
	}

	public String getHorariocorto() {
		return this.horariocorto;
	}

	public void setHorariocorto(String horariocorto) {
		this.horariocorto = horariocorto;
	}

	public String getHorariolargo() {
		return this.horariolargo;
	}

	public void setHorariolargo(String horariolargo) {
		this.horariolargo = horariolargo;
	}

	public Short getListaE() {
		return this.listaE;
	}

	public void setListaE(Short listaE) {
		this.listaE = listaE;
	}

	public String getMapa() {
		return this.mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

	public Short getOcupadas() {
		return this.ocupadas;
	}

	public void setOcupadas(Short ocupadas) {
		this.ocupadas = ocupadas;
	}

	public Byte getOcupadasJD() {
		return this.ocupadasJD;
	}

	public void setOcupadasJD(Byte ocupadasJD) {
		this.ocupadasJD = ocupadasJD;
	}

	public Byte getPlazas() {
		return this.plazas;
	}

	public void setPlazas(Byte plazas) {
		this.plazas = plazas;
	}

	public String getPortada() {
		return this.portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public String getRecogida() {
		return this.recogida;
	}

	public void setRecogida(String recogida) {
		this.recogida = recogida;
	}

	public String getRecorridoLargo() {
		return this.recorridoLargo;
	}

	public void setRecorridoLargo(String recorridoLargo) {
		this.recorridoLargo = recorridoLargo;
	}

	public String getRecrridoCorto() {
		return this.recrridoCorto;
	}

	public void setRecrridoCorto(String recrridoCorto) {
		this.recrridoCorto = recrridoCorto;
	}

	public String getResponsables() {
		return this.responsables;
	}

	public void setResponsables(String responsables) {
		this.responsables = responsables;
	}

	public Byte getRjd() {
		return this.rjd;
	}

	public void setRjd(Byte rjd) {
		this.rjd = rjd;
	}

	public Byte getSalidaBono() {
		return this.salidaBono;
	}

	public void setSalidaBono(Byte salidaBono) {
		this.salidaBono = salidaBono;
	}

	public Byte getSalidaContabilizada() {
		return this.salidaContabilizada;
	}

	public void setSalidaContabilizada(Byte salidaContabilizada) {
		this.salidaContabilizada = salidaContabilizada;
	}

	public String getSimples() {
		return this.simples;
	}

	public void setSimples(String simples) {
		this.simples = simples;
	}

	public String getSubidac() {
		return this.subidac;
	}

	public void setSubidac(String subidac) {
		this.subidac = subidac;
	}

	public String getSubidal() {
		return this.subidal;
	}

	public void setSubidal(String subidal) {
		this.subidal = subidal;
	}

	public String getTextoSeguro() {
		return this.textoSeguro;
	}

	public void setTextoSeguro(String textoSeguro) {
		this.textoSeguro = textoSeguro;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTriples() {
		return this.triples;
	}

	public void setTriples(String triples) {
		this.triples = triples;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlPortada() {
		return this.urlPortada;
	}

	public void setUrlPortada(String urlPortada) {
		this.urlPortada = urlPortada;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setSalidaBean(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setSalidaBean(null);

		return album;
	}

	public List<BonoDetalle> getBonoDetalles() {
		return this.bonoDetalles;
	}

	public void setBonoDetalles(List<BonoDetalle> bonoDetalles) {
		this.bonoDetalles = bonoDetalles;
	}

	public BonoDetalle addBonoDetalle(BonoDetalle bonoDetalle) {
		getBonoDetalles().add(bonoDetalle);
		bonoDetalle.setSalidaBean(this);

		return bonoDetalle;
	}

	public BonoDetalle removeBonoDetalle(BonoDetalle bonoDetalle) {
		getBonoDetalles().remove(bonoDetalle);
		bonoDetalle.setSalidaBean(null);

		return bonoDetalle;
	}

	public List<SalidaDetalle> getSalidaDetalles() {
		return this.salidaDetalles;
	}

	public void setSalidaDetalles(List<SalidaDetalle> salidaDetalles) {
		this.salidaDetalles = salidaDetalles;
	}

	public SalidaDetalle addSalidaDetalle(SalidaDetalle salidaDetalle) {
		getSalidaDetalles().add(salidaDetalle);
		salidaDetalle.setSalidaBean(this);

		return salidaDetalle;
	}

	public SalidaDetalle removeSalidaDetalle(SalidaDetalle salidaDetalle) {
		getSalidaDetalles().remove(salidaDetalle);
		salidaDetalle.setSalidaBean(null);

		return salidaDetalle;
	}

	public List<SalidaFecha> getSalidaFechas() {
		return this.salidaFechas;
	}

	public void setSalidaFechas(List<SalidaFecha> salidaFechas) {
		this.salidaFechas = salidaFechas;
	}

	public SalidaFecha addSalidaFecha(SalidaFecha salidaFecha) {
		getSalidaFechas().add(salidaFecha);
		salidaFecha.setSalidaBean(this);

		return salidaFecha;
	}

	public SalidaFecha removeSalidaFecha(SalidaFecha salidaFecha) {
		getSalidaFechas().remove(salidaFecha);
		salidaFecha.setSalidaBean(null);

		return salidaFecha;
	}

	public List<SalidaPrecio> getSalidaPrecios() {
		return this.salidaPrecios;
	}

	public void setSalidaPrecios(List<SalidaPrecio> salidaPrecios) {
		this.salidaPrecios = salidaPrecios;
	}

	public SalidaPrecio addSalidaPrecio(SalidaPrecio salidaPrecio) {
		getSalidaPrecios().add(salidaPrecio);
		salidaPrecio.setSalidaBean(this);

		return salidaPrecio;
	}

	public SalidaPrecio removeSalidaPrecio(SalidaPrecio salidaPrecio) {
		getSalidaPrecios().remove(salidaPrecio);
		salidaPrecio.setSalidaBean(null);

		return salidaPrecio;
	}

	public void putFechas() {
		SalidaFechaPK fechaPK;
    	Calendar calendar;
		for (SalidaFecha fecha: salidaFechas) {
			if ((fechaPK = fecha.getId()).getFechaTipo()==1) {
                fechaInicio = fecha.getFecha();
                if (anyo==null) {
                	calendar = new GregorianCalendar();
                	calendar.setTime(fechaInicio);
                	anyo = calendar.get(Calendar.YEAR);
                }
			}
			if (fechaPK.getFechaTipo()==2) {
				fechaFin = fecha.getFecha();
			}
			if (fechaPK.getFechaTipo()==7) {
				fechaApunte = fecha.getFecha();
			}
			if (fechaPK.getFechaTipo()==8) {
				fechaCierre = fecha.getFecha();
			}
		}
	}

	public Date getFechaInicio() {
		if (fechaInicio==null) {
			putFechas();
		}
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		if (fechaFin==null) {
			putFechas();
		}
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaApunte() {
		if (fechaApunte==null) {
			putFechas();
		}
		return fechaApunte;
	}

	public void setFechaApunte(Date fechaApunte) {
		this.fechaApunte = fechaApunte;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getsFechaInicio() {
        return Constante.dF8.format(fechaInicio);
	}

	public String getsDiaDe() {
		StringBuffer dia = new StringBuffer(Constante.dF7.format(fechaInicio));
        dia.replace(0, 1, dia.substring(0, 1).toUpperCase());
		return Constante.dF6.format(fechaInicio) + dia;
	}

	public boolean presentarMensaje(Date now) {
        return now.compareTo(fechaFin)<=0 && (fechaApunte==null || now.compareTo(fechaApunte)>=0);
	}

	public boolean hayPlazas() {
		EntityManager em = null;
		boolean hayPlazas = false;
		try {
			em = EntityManagerFactories.getEM();
			hayPlazas = plazas>em.createNamedQuery("SalidaDetalle.findCount", Number.class)
					.setParameter("salida", salida).getSingleResult().intValue();
		} catch (Exception e) {
			log.log(Level.SEVERE, "No lee SalidaDetalle.findCount", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
		return hayPlazas;
	}

	public static Map<String, Object> find(String salida,Date date,Persona persona) {
		EntityManager em = null;
		Map<String, Object> map = new HashMap<String, Object>();
		Date now = new Date();
        try {
    		em = EntityManagerFactories.getEM();
    		Salida beanSalida;
    		if (salida!=null && !salida.isEmpty()) {
        		beanSalida = em.createNamedQuery("Salida.findBySalida", Salida.class)
        				.setParameter("salida",salida).setMaxResults(1).getSingleResult();
    		} else {
        		beanSalida = em.createNamedQuery("Salida.findByDate", Salida.class)
        				.setParameter("date",date).setMaxResults(1).getSingleResult();
    		}
        	map.put("salida", beanSalida.getSalida());
        	beanSalida.putFechas();
            Date fechaApunte = beanSalida.fechaApunte;
            Date fechaCierre = beanSalida.fechaCierre;
            boolean abiertoApunte = fechaApunte!=null && fechaCierre!=null
            		&& now.compareTo(fechaApunte)>=0 && now.compareTo(fechaCierre)<=0;
    		if (abiertoApunte) {
    			map.put("hayPlazas",beanSalida.plazas>beanSalida.salidaDetalles.size());
    		}
            map.put("abiertoApunte",abiertoApunte);
            if (persona!=null) {
            	map.put("id_persona", persona.getIdPersona());
            	map.put("usuario", persona.getUsuario().isEmpty()? persona.getNombre(): persona.getUsuario());
            	if (abiertoApunte && em.createNamedQuery("Persona.findByIdPersona", Persona.class)
            			.setParameter("idPersona",persona.getIdPersona()).getSingleResult()
            			.esSocio(beanSalida.anyo)) {
            		map.put("apuntado",!em.createNamedQuery("SalidaDetalle.findByPersona", SalidaDetalle.class)
            				.setParameter("salida",beanSalida.getSalida())
            				.setParameter("idPersona",persona.getIdPersona()).getResultList().isEmpty());
            	}
            }
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Salida.findBySalida", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
        return map;
	}

	public List<Salida> getFotosPortada() {
		if (fotosPortada==null) {
	        int i = 3;
	        fotosPortada = new ArrayList<Salida>();
	        for (Salida salida: Static.getMsalida().values()) {
	        	if (salida.getFechaInicio().compareTo(fechaInicio)<0) {
	                fotosPortada.add(salida);
	                if (--i<=0) {
	                    break;
	                }
	        	}
	        }
		}
		return fotosPortada;
	}

	public static Map<String, Object> findApunte(String salida) {
		EntityManager em = null;
		BonoDetalle bono;
		Persona persona;
		String ingreso;
		int idPersona;
		String mensaje;
		String observacion;
		List<PersonaMensaje> lMensajes;
    	Map<String, Object> map = new HashMap<String, Object>();
        try {
    		em = EntityManagerFactories.getEMF().createEntityManager();
        	List<Map<String, Object>> lBeans = new ArrayList<Map<String, Object>>();
        	Map<String, Object> mBean;
        	map.put("salidas",lBeans);
    		for (Salida bean: em.createNamedQuery("Salida.findAll", Salida.class).getResultList()) {
        		lBeans.add(mBean = new HashMap<String, Object>());
        		mBean.put("fechaInicio",Constante.dF12.format(bean.getFechaInicio()));
        		mBean.put("salida",bean.getSalida());
        		mBean.put("descripcion",bean.getDescripcion());
        		mBean.put("plazas",bean.getPlazas());
        		mBean.put("tipo",bean.getTipo());
        	}
        	if (salida==null || "null".equals(salida)) {
        		salida = (String) lBeans.get(0).get("salida");
        	}
        	map.put("salida", salida);
        	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        	map.put("detalles", list);
        	map.put("fp",Static.getLfp());
        	for (SalidaDetalle bean: em.createNamedQuery("SalidaDetalle.find", SalidaDetalle.class).
        			setParameter("salida", salida).getResultList()) {
        		list.add(mBean = new HashMap<String, Object>());
        		mBean.put("fp", bean.getRecibo().getFormapago().getCodigo());
        		mBean.put("bus", bean.getBus()==null? "": bean.getBus());
        		mBean.put("asiento", bean.getAsiento()==null? "": bean.getAsiento());
        		mBean.put("importe", (bean.getSeguroDia()==0? "": "SD ") + Constante.nF4.format(bean.getImporte()));
        		if (bean.getRecibo().getImporte()==null) {
        			ingreso = Constante.nF4.format(BigDecimal.ZERO);
        		} else {
        			ingreso = Constante.nF4.format(bean.getRecibo().getImporte());
        		}
        		mBean.put("ingreso", ingreso);
        		mBean.put("pago", Constante.nF4.format(bean.getPago()));
        		if ((bono = bean.getBonoDetalle())==null) {
            		mBean.put("bono", "");
        		} else {
            		mBean.put("bono", bono.getId().getBono() + "-" + bono.getId().getUso());
        		}
        		mBean.put("idPersona", Constante.nF2.format(idPersona = bean.getId().getIdPersona()));
        		persona = bean.getPersona();
        		mBean.put("observacion", (observacion = bean.getObservacion())==null? "": observacion);
        		mBean.put("nombre", ((persona.getNombre()==null? "": persona.getNombre().trim())
        				+ (persona.getApellido1()==null? "": " " + persona.getApellido1().trim())
        				+ (persona.getApellido2()==null ? "": " " + persona.getApellido2().trim())).trim().toUpperCase());
        		lMensajes = em.createNamedQuery("PersonaMensaje.find",PersonaMensaje.class).setParameter("salida", salida)
        				.setParameter("idPersona", idPersona).setMaxResults(1).getResultList();
        		mBean.put("mensaje",lMensajes.isEmpty()? "": (mensaje = lMensajes.get(0).getMensaje())==null? "": mensaje);
        	}
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Salida.findApunte", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
		return map;
	}

	public static Salida findBySalida(String salida) {
		EntityManager em = null;
        try {
    		em = EntityManagerFactories.getEM();
    		return em.createNamedQuery("Salida.findBySalida", Salida.class)
    				.setParameter("salida", salida).getSingleResult();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Salida.findBySalida", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
		return null;
	}

	public static Salida findByDate(Date date) {
		EntityManager em = null;
        try {
    		em = EntityManagerFactories.getEM();
    		List<Salida> list = em.createNamedQuery("Salida.findByDate", Salida.class)
    				.setParameter("date", date).setMaxResults(1).getResultList();
    		if (!list.isEmpty()) {
    			return list.get(0);
    		}
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Salida.findBySalida", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
		return null;
	}
}