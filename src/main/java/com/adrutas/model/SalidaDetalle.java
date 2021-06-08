package com.adrutas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.dao.Static;

import adrutas.com.Constante;

/**
 * The persistent class for the salida_detalle database table.
 * 
 */
@Entity
@Table(name="salida_detalle")
@NamedQuery(name="SalidaDetalle.findAll", query="SELECT s FROM SalidaDetalle s")
@NamedQuery(name="SalidaDetalle.findCount", query = "SELECT s FROM SalidaDetalle s WHERE s.id.salida = :salida")
@NamedQuery(name="SalidaDetalle.listAlfa", query = "SELECT s FROM SalidaDetalle s WHERE s.participo=1 and "
		+ "s.id.salida = :salida ORDER BY s.persona.apellido1,s.persona.apellido2,s.persona.nombre")
@NamedQuery(name="SalidaDetalle.listAsientos", query="SELECT new SalidaDetalle(s.id,s.participo,s.bus,s.asiento,"
		+ "s.importe,s.pago,s.seguroDia,s.observacion,s.recibo,s.persona,s.salidaBean,b) FROM SalidaDetalle s "
		+ "LEFT JOIN BonoDetalle b ON s.id.salida=b.salidaBean.salida and s.id.idPersona=b.idPersona WHERE "
		+ "s.participo=1 and s.id.salida=:salida ORDER BY s.bus,s.asiento,s.recibo.idRecibo")
@NamedQuery(name="SalidaDetalle.find", query="SELECT new SalidaDetalle(s.id,s.participo,s.bus,s.asiento,s.importe,"
		+ "s.pago,s.seguroDia,s.observacion,s.recibo,s.persona,s.salidaBean,b) FROM SalidaDetalle s LEFT JOIN "
		+ "BonoDetalle b ON s.id.salida=b.salidaBean.salida and s.id.idPersona=b.idPersona WHERE s.id.salida=:salida "
		+ "ORDER BY s.bus,s.asiento,s.recibo.idRecibo")
@NamedQuery(name="SalidaDetalle.findByPersona", query="SELECT s FROM SalidaDetalle s "
		+ "WHERE s.id.salida=:salida and s.id.idPersona=:idPersona")
@NamedQuery(name="SalidaDetalle.findByPersonaAndFechas", query="SELECT s FROM SalidaDetalle s WHERE s.id.idPersona="
		+ ":idPersona and s.participo=1 and s.salidaBean.salida in (select f1.salidaBean.salida from SalidaFecha f1 where "
		+ "f1.fechaTipoBean.fechaTipo=1 and f1.salidaBean.salida>=:salidaIni and f1.fecha<=:fechaFin)")
@NamedQuery(name="SalidaDetalle.countByPersonaAndSalidas", query="SELECT count(s.id.salida) FROM SalidaDetalle s "
		+ "WHERE s.id.idPersona=:idPersona and s.id.salida >= :salidaIni and s.id.salida < :salidaFin and s.participo=1")
@NamedQuery(name="SalidaDetalle.getLast", query="SELECT s.contador FROM SalidaDetalle s order by s.contador desc")
@NamedQuery(name="SalidaDetalle.findN",query="SELECT s FROM SalidaDetalle s WHERE salidaBean.tipo in ('N','R') and "
		+ "s.recibo.formapago.codigo in :setFP and salidaBean.anyo=:anyo "
		+ "and salidaBean.salida<:salida AND id_persona=:idPersona ORDER BY s.salidaBean.salida")
@NamedQuery(name="SalidaDetalle.findByAnyoAndPersona", query="SELECT s FROM SalidaDetalle s "
		+ "WHERE s.salidaBean.anyo=:anyo and s.id.idPersona=:idPersona")

public class SalidaDetalle implements Serializable {
	private static final long serialVersionUID = -6755376347925749528L;
	private static final Logger log = Logger.getLogger(SalidaDetalle.class.getName());
	private static final List<Integer> precioTipoNoSocio = Arrays.asList(new Integer[] {5,6,7,8});
	private static final List<String> lTipos = Arrays.asList(new String[] {"ME","BO"});

	@EmbeddedId
	private SalidaDetallePK id = new SalidaDetallePK();

	private String anyo;

	private Short asiento;

	private String banco;

	private Short bono;

	private Short bus;

	private int contador;

	private String fp;

	private BigDecimal importe;

	@Lob
	@Column(name="observacion")
	private String observacion;

	private BigDecimal pago;

	@Column(name="seguro_dia")
	private Byte seguroDia;

	//bi-directional many-to-one association to Salida
	@ManyToOne
	@JoinColumn(name="salida",insertable=false,updatable=false)
	private Salida salidaBean;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona",insertable=false,updatable=false)
	private Persona persona;

	//bi-directional many-to-one association to Recibo
	@ManyToOne
	@JoinColumn(name="id_recibo")
	private Recibo recibo;

	@Transient
	private BonoDetalle bonoDetalle;

	@Transient
	private Formapago formapago;

	@Transient
	private BigDecimal ingreso;

	@Transient
	private short uso;

	@Transient
	private short puntos;

	private boolean participo = true;

	public SalidaDetalle() {
	}

	public SalidaDetalle(String salida,int idPersona) {
		id.setIdPersona(idPersona);
		id.setSalida(salida);
	}

	public SalidaDetalle(SalidaDetallePK id, boolean participo, Short bus, Short asiento, BigDecimal importe, BigDecimal pago,
			Byte seguroDia, String observacion, Recibo recibo, Persona persona, Salida salidaBean, BonoDetalle bonoDetalle) {
		this.id = id;
		this.participo = participo;
		this.bus = bus;
		this.asiento = asiento;
		this.importe = importe;
		this.pago = pago;
		this.seguroDia = seguroDia;
		this.observacion = observacion;
		this.recibo = recibo;
		this.persona = persona;
		this.salidaBean = salidaBean;
		this.bonoDetalle = bonoDetalle;
	}

	public SalidaDetallePK getId() {
		return this.id;
	}

	public void setId(SalidaDetallePK id) {
		this.id = id;
	}

	public String getAnyo() {
		return this.anyo;
	}

	public void setAnyo(String anyo) {
		this.anyo = anyo;
	}

	public Short getAsiento() {
		return this.asiento;
	}

	public void setAsiento(Short asiento) {
		this.asiento = asiento;
	}

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Short getBono() {
		return this.bono;
	}

	public void setBono(Short bono) {
		this.bono = bono;
	}

	public Short getBus() {
		return this.bus;
	}

	public void setBus(Short bus) {
		this.bus = bus;
	}

	public int getContador() {
		return this.contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public String getFp() {
		return this.fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public BigDecimal getPago() {
		return this.pago;
	}

	public void setPago(BigDecimal pago) {
		this.pago = pago;
	}

//	public byte getRecoger() {
//		return this.recoger;
//	}
//
//	public void setRecoger(byte recoger) {
//		this.recoger = recoger;
//	}
//
//	public short getSalidabono() {
//		return this.salidabono;
//	}
//
//	public void setSalidabono(short salidabono) {
//		this.salidabono = salidabono;
//	}

	public Byte getSeguroDia() {
		return this.seguroDia;
	}

	public void setSeguroDia(Byte seguroDia) {
		this.seguroDia = seguroDia;
	}

	public Salida getSalidaBean() {
		return this.salidaBean;
	}

	public void setSalidaBean(Salida salidaBean) {
		this.salidaBean = salidaBean;
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

	public Formapago getFormapago() {
		return formapago;
	}

	public void setFormapago(Formapago formapago) {
		this.formapago = formapago;
	}

	public BigDecimal getIngreso() {
		return ingreso;
	}

	public void setIngreso(BigDecimal ingreso) {
		this.ingreso = ingreso;
	}

	public short getUso() {
		return uso;
	}

	public void setUso(short uso) {
		this.uso = uso;
	}

	public BonoDetalle getBonoDetalle() {
		return bonoDetalle;
	}

	public void setBonoDetalle(BonoDetalle bonoDetalle) {
		this.bonoDetalle = bonoDetalle;
	}

	public short getPuntos() {
		return puntos;
	}

	public void setPuntos(short puntos) {
		this.puntos = puntos;
	}

	public boolean isParticipo() {
		return participo;
	}

	public void setParticipo(boolean participo) {
		this.participo = participo;
	}

	public static SalidaDetalle findByPersona(String salida,int id_persona) {
		EntityManager em = null;
        try {
    		em = EntityManagerFactories.getEM();
    		return em.createNamedQuery("SalidaDetalle.findByPersona", SalidaDetalle.class)
    				.setParameter("salida",salida).setParameter("idPersona",id_persona).getSingleResult();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee SalidaDetalle.findByPersona", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
		return null;
	}

	public static Map<String, Object> find(Map<String,Object> map) {
		EntityManager em = null;
		Map<String,Object> mResult = new HashMap<String,Object>();
		Map<String,Map<String,String>> mSalidas = new TreeMap<String,Map<String,String>>();
		Map<String,List<String>> mEspeciales = new HashMap<String,List<String>>();
		List<String> lEspeciales;
		Map<String,String> mBean;
		mResult.put("salidas",mSalidas);
		mResult.put("especiales",mEspeciales);
        try {
    		em = EntityManagerFactories.getEM();
    		int idPersona = ((Double) map.get("id_persona")).intValue();
    		Date fechaFin = null;
    		for (String salida: (List<String>) map.get("array")) {
        		Salida beanSalida = em.createNamedQuery("Salida.findBySalida", Salida.class)
        				.setParameter("salida",salida).getSingleResult();
        		mEspeciales.put(salida, lEspeciales=new ArrayList<String>());
        		fechaFin = beanSalida.getFechaPreapunteIni();
        		if (fechaFin==null) {
        			fechaFin = beanSalida.getFechaInicio();
        		}
        		for (SalidaDetalle salidaDetalle: em.createNamedQuery("SalidaDetalle.findByPersonaAndFechas",
        				SalidaDetalle.class).setParameter("idPersona",idPersona).setParameter("salidaIni",
        				beanSalida.getSalidaDesde()).setParameter("fechaFin",fechaFin).getResultList()) {
        			mSalidas.put(salidaDetalle.getId().getSalida(), mBean = new HashMap<String,String>());
        			mBean.put("descripcion",salidaDetalle.getSalidaBean().getDescripcion());
        			mBean.put("inicio",Constante.dF12.format(salidaDetalle.getSalidaBean().getFechaInicio()));
        			lEspeciales.add(salidaDetalle.getId().getSalida());
        		}
    		}
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Salida.findBySalida", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
        return mResult;
	}

	public static synchronized String insert(String salida,int idPersona) {
		EntityManager em = null;
        try {
    		em = EntityManagerFactories.getEM();
			em.getTransaction().begin();
			insert(em,salida,idPersona);
			em.getTransaction().commit();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "SalidaDetalle.insert", e);
        	return e.getMessage();
		} finally {
			if (em!=null) {
				em.close();
			}
		}
        return null;
	}

	public static synchronized String insert(String salida,List<Integer> lPersonas) {
		EntityManager em = null;
        try {
    		em = EntityManagerFactories.getEM();
			em.getTransaction().begin();
			for (Integer idPersona: lPersonas) {
				del(em,salida,idPersona);
				insert(em,salida,idPersona);
			}
			em.getTransaction().commit();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "SalidaDetalle.insert", e);
        	return e.getMessage();
		} finally {
			if (em!=null) {
				em.close();
			}
		}
        return null;
	}

	private static void insert(EntityManager em,String salida,int idPersona) throws Exception {
		Salida beanSalida = em.createNamedQuery("Salida.findBySalida",Salida.class)
				.setParameter("salida",salida).getSingleResult();
		if (beanSalida.getPlazas()<=beanSalida.getSalidaDetalles().size()) {
    		throw new Exception("Ya no hay plazas");
		}
    	SalidaDetalle detalle = new SalidaDetalle(salida,idPersona);
    	detalle.setSalidaBean(beanSalida);
    	detalle.putImporte(em);
    	detalle.setContador(em.createNamedQuery("SalidaDetalle.getLast", Integer.class).setMaxResults(1).getSingleResult()+1);
    	Recibo recibo = new Recibo();
		recibo.setIdRecibo(em.createNamedQuery("Recibo.getLast", Integer.class)
				.setMaxResults(1).getSingleResult() + 1);
		recibo.setTabla("salida_detalle");
		recibo.setFecha(new Date());
		recibo.setImporte(detalle.getImporte());
		recibo.setFormapago(Static.mFormaPagoAll.get(detalle.getFp()));
    	detalle.setRecibo(recibo);
		em.persist(recibo);
		em.persist(detalle);
	}

	private void putImporte(EntityManager em) throws Exception {
		int idPersona;
		Map<Integer,Ficha> mFicha = new HashMap<Integer,Ficha>();
		int anyo = salidaBean.getAnyo();
		Set<Integer> setAnyos = new HashSet<Integer>(Arrays.asList(new Integer[] {anyo,anyo-1}));
		for (Ficha ficha: em.createNamedQuery("Ficha.findAnyos", Ficha.class).setParameter("idPersona",
				idPersona=id.getIdPersona()).setParameter("setAnyos", setAnyos).getResultList()) {
			mFicha.put(ficha.getId().getAnyo(),ficha);
		}
		Date baja;
		boolean isDirectivo = false;
		Date fechaInicio = salidaBean.getFechaInicio();
		Ficha fichaAct = mFicha.get(anyo);
        boolean esSocioAct = fichaAct!=null && fichaAct.getImportecuota().signum()>0;
		if (fichaAct!=null) {
			for (Directiva directiva: fichaAct.getPersona().getDirectivas()) {
				if (fechaInicio.compareTo(directiva.getId().getAlta())>=0
						&& ((baja = directiva.getBaja())==null || fechaInicio.compareTo(baja)<=0)) {
					isDirectivo = true;
					break;
				}
			}
		}
    	setFp("ME");
        if (isDirectivo) {
            setFp("JD");
        } else if (esSocioAct) {
        	String tipo = salidaBean.getTipo();
            if ("N".equals(tipo)) {
            	int contGS = 0;
            	boolean tieneGC = false;
            	boolean tieneGS = false;
            	String formaPago;
            	Set<String> setFP2 = new HashSet<String>(Constante.ORDINARIAS);
            	setFP2.add("GS");
            	Set<String> setFP1 = new HashSet<String>(setFP2);
            	setFP1.add("GC");
            	for (SalidaDetalle bean: em.createNamedQuery("SalidaDetalle.findN",SalidaDetalle.class).
            			setParameter("setFP",setFP1).setParameter("anyo",salidaBean.getAnyo()).
                		setParameter("salida",salidaBean.getSalida()).setParameter("idPersona",id.getIdPersona()).getResultList()) {
                	formaPago = bean.getRecibo().getFormapago().getCodigo();
                    if ("GS".equals(formaPago)) {
                        contGS = 0;
                        tieneGS = true;
                    } else if ("GC".equals(formaPago)) {
                    	tieneGC = true;
                    } else {
                        contGS++;
                    }
                }
                Ficha fichaAnt = mFicha.get(anyo-1);
                if (!tieneGC && "N".equals(tipo)) {
                	setFp("GC");
                } else if (contGS>=Constante.numGS) {
                	setFp("GS");
                } else if (contGS==0 && !tieneGS && fichaAnt!=null && fichaAnt.getImportecuota().signum()>0) {

                	//Calcula si tiene derecho a GS por el año anterior
                	for (SalidaDetalle bean: em.createNamedQuery("SalidaDetalle.findN",SalidaDetalle.class).
                			setParameter("setFP",setFP2).setParameter("anyo",salidaBean.getAnyo()-1).
                    		setParameter("salida",salidaBean.getSalida()).setParameter("idPersona",id.getIdPersona()).getResultList()) {
                		formaPago = bean.getRecibo().getFormapago().getCodigo();
                		if ("GS".equals(formaPago)) {
                			contGS = 0;
                		} else {
                			contGS++;
                		}
                	}
                	if (contGS>=Constante.numGS) {
                		setFp("GS");
                	}
                }
                if ("ME".equals(fp)) {
                	if (bonoDetalle==null) {
                    	List<BonoDetalle> lBonos = em.createNamedQuery("BonoDetalle.getUltimo",BonoDetalle.class)
                    			.setParameter("idPersona",idPersona).setMaxResults(1).getResultList();
                    	if (!lBonos.isEmpty()) {
                        	BonoDetalle bono = lBonos.get(0);
                        	if (!bono.getSalidaBean().getSalida().equals(this.salidaBean.getSalida())
                        			&& bono.getId().getUso()<10) {
                        		bonoDetalle = new BonoDetalle();
                        		bonoDetalle.setIdPersona(idPersona);
                        		bonoDetalle.setSalidaBean(salidaBean);
                        		BonoDetallePK bonoDetallePK = new BonoDetallePK();
                        		bonoDetalle.setId(bonoDetallePK);
                        		bonoDetallePK.setBono(bono.getId().getBono());
                        		bonoDetallePK.setUso((short) (bono.getId().getUso()+1));
                        		fp = "BO";
                        		em.persist(bonoDetalle);
                        	}
                    	}
                	} else {
                		fp = "BO";
                	}
                }
            }
        }

//		Calculo del precio
        Map<Object, BigDecimal> precio = new HashMap<Object, BigDecimal>();
        boolean soloSocios = true;
        int precioTipo;
		for (SalidaPrecio salidaPrecio: salidaBean.getSalidaPrecios()) {
            precio.put((precioTipo = salidaPrecio.getPrecioTipoBean().getPrecioTipo()), salidaPrecio.getPrecio());
        	if (precioTipoNoSocio.contains(precioTipo)) {
        		soloSocios = false;
        	}
        }
        pago = BigDecimal.ZERO;
    	BigDecimal impSegDia = new BigDecimal(Static.getPropiedadesanuales(salidaBean.getAnyo(),"S1D"));
        boolean seguro_dia = fichaAct==null || "".equals(fichaAct.getTipoLicencia());
        if (esSocioAct) {

//            Socio
            if (seguro_dia) {

//                Socio con seguro diario
                if ((importe = precio.get(3))==null) {

//                    Salida sin precio para socio sin federar
                    pago = impSegDia;
                    importe = precio.get(2).add(pago);
                } else {

//                    Salida con precio para socio sin federar
                    pago = importe.subtract(precio.get(2));
                }
            } else {

//                Socio federado
                importe = precio.get(2);
            }
        } else {

//            No socio
        	if (soloSocios) {
        		throw new Exception("Salida solo para socios");
        	}
            if (seguro_dia) {

//                No socio sin federar
                if ((importe = precio.get(6))==null) {

//                    Salida sin precio para no socio sin federar
                    pago = impSegDia;
                    importe = precio.get(5).add(pago);
                } else {

//                    Salida con precio para no socio sin federar
                    pago = importe.subtract(precio.get(5));
                }
            } else {

//                No socio federado
                importe = precio.get(5);
            }
        }
        if (isDirectivo) {
            if ((ingreso = precio.get(4))==null) {
                ingreso = BigDecimal.ZERO;
            }
        } else {
            if (Constante.SIN_IMPORTE.contains(getFp())) {
                importe = pago;
            }
            ingreso = importe;
        }
    	setSeguroDia((byte) (seguro_dia? 1: 0));
	}

	public static synchronized void update(Map<String,Object> listaApuntados) {
		EntityManager em = null;
    	String bonoUso;
    	String[] bono;
    	int idPersona;
    	String observacion;
    	String mensaje;
    	String asiento;
    	String bus;
    	Recibo recibo;
    	String salida = (String) listaApuntados.get("salida");
    	List<PersonaMensaje> lMensajes;
    	PersonaMensaje personaMensaje;
    	Date now = new Date();
    	Map<Integer,BonoDetalle> mBonoDetalle = new HashMap<Integer,BonoDetalle>();
    	BonoDetalle bonoDetalle;
        try {
    		em = EntityManagerFactories.getEM();
			em.getTransaction().begin();
			TypedQuery<PersonaMensaje> queryMensaje = em.createNamedQuery(
					"PersonaMensaje.find",PersonaMensaje.class).setParameter("salida", salida);
			Salida beanSalida = em.createNamedQuery("Salida.findBySalida",Salida.class)
					.setParameter("salida",salida).getSingleResult();
			for (BonoDetalle bean:em.createNamedQuery("BonoDetalle.getSalida",BonoDetalle.class).
					setParameter("salida", salida).getResultList()) {
				mBonoDetalle.put(bean.getIdPersona(),bean);
			}
			Map<Integer,Map<String,Object>> mApuntados = new HashMap<Integer,Map<String,Object>>();
			Map<String,Object> map;
			for (Map<String,Object> bean: (List<Map<String,Object>>) listaApuntados.get("apuntados")) {
				mApuntados.put(Integer.parseInt((String) bean.get("id_persona")),bean);
			}
			for (SalidaDetalle detalle: beanSalida.getSalidaDetalles()) {
		    	map = mApuntados.get(idPersona = detalle.getId().getIdPersona());
		    	detalle.setBonoDetalle(mBonoDetalle.get(idPersona));
		    	detalle.putImporte(em);
		    	if (lTipos.contains(detalle.fp)) {
					detalle.fp = (String) map.get("estado");
		    	}
				detalle.participo = (boolean) map.get("participo");
				detalle.asiento = (asiento = (String) map.get("asiento")).isEmpty()? null :Short.parseShort(asiento);
	        	detalle.bus = (bus = (String) map.get("bus")).isEmpty()? null : Short.parseShort(bus);
	        	detalle.ingreso = new BigDecimal(Constante.nF4.parse((String) map.get("ingreso")).longValue());
	        	detalle.pago = new BigDecimal(Constante.nF4.parse((String) map.get("pago")).longValue());
	        	bonoDetalle = mBonoDetalle.get(idPersona);
	        	if ("BO".equals(detalle.fp)) {
		        	if ((bonoUso = (String) map.get("bono")).isEmpty()) {
		        		if (bonoDetalle!=null) {
		        			em.remove(bonoDetalle);
		        		}
		        	} else {
		        		if (bonoDetalle==null) {
		        			bonoDetalle = new BonoDetalle(Short.parseShort((bono = bonoUso.split("-"))[0]),
		        					Short.parseShort(bono[1]),detalle.salidaBean,idPersona);
		        			detalle.bonoDetalle = bonoDetalle;
		        			em.persist(detalle.bonoDetalle);
		        		} else {
		        			bonoDetalle.getId().setBono(Short.parseShort((bono = bonoUso.split("-"))[0]));
		        			bonoDetalle.getId().setUso(Short.parseShort(bono[1]));
		        		}
		        	}
	        	} else {
	        		if (bonoDetalle!=null) {
	        			em.remove(bonoDetalle);
	        		}
	        		if (detalle.bonoDetalle!=null) {
	        			em.remove(detalle.bonoDetalle);
	        			detalle.bonoDetalle = null;
	        		}
	        	}
	        	if ((observacion = (String) map.get("observacion")).isEmpty()) {
	        		detalle.setObservacion(null);
	        	} else {
	        		detalle.setObservacion(observacion);
	        	}
	        	lMensajes = queryMensaje.setParameter("idPersona", idPersona).setMaxResults(2).getResultList();
	        	if ((mensaje = (String) map.get("mensaje")).isEmpty()) {
	        		if (lMensajes.isEmpty()) {
	        			//No hay que hacer nada
	        		} else {
	        			personaMensaje = lMensajes.get(0);
	        			if (personaMensaje.getMensaje()==null) {
	        				//No hay que hacer nada
	        			} else {
	        				if (personaMensaje.getId().getSalida().equals(salida)) {
	        					if (lMensajes.size()>1) {
		        					if (lMensajes.get(1).getMensaje()==null) {
		        						em.remove(personaMensaje);
		        					} else {
		        						personaMensaje.setMensaje(null);
//		        						em.persist(personaMensaje);
		        					}
	        					} else {
	        						em.remove(personaMensaje);
	        					}
	        				} else {
	        					em.persist(new PersonaMensaje(salida,idPersona,now,null));
	        				}
	        			}
	        		}
	        	} else {
	        		if (lMensajes.isEmpty()) {
    					em.persist(new PersonaMensaje(salida,idPersona,now,mensaje));
	        		} else {
	        			personaMensaje = lMensajes.get(0);
	        			if (mensaje.equals(personaMensaje.getMensaje())) {
	        				//No hay que hacer nada
	        			} else {
	        				if (personaMensaje.getId().getSalida().equals(salida)) {
	        					personaMensaje.setMensaje(mensaje);
	        				} else {
	        					em.persist(new PersonaMensaje(salida,idPersona,now,mensaje));
	        				}
	        			}
	        		}
	        	}
	        	recibo = detalle.recibo;
				recibo.setFormapago(Static.mFormaPagoAll.get(detalle.fp));
				recibo.setImporte(detalle.ingreso);
			}
			em.getTransaction().commit();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "SalidaDetalle.update", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
	}

	public static synchronized void del(String salida,int idPersona) {
		EntityManager em = null;
		EntityTransaction t = null;
        try {
    		em = EntityManagerFactories.getEM();
    		t = em.getTransaction();
    		t.begin();
			del(em,salida,idPersona);
			t.commit();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "SalidaDetalle.del", e);
        	t.rollback();
		} finally {
			if (em!=null) {
				em.close();
			}
		}
	}

	public static void del(EntityManager em,String salida,int idPersona) {
		SalidaDetalle detalle = em.createNamedQuery("SalidaDetalle.findByPersona",SalidaDetalle.class)
				.setParameter("salida",salida).setParameter("idPersona",idPersona).getSingleResult();
    	for (PersonaMensaje bean: em.createNamedQuery("PersonaMensaje.findSalida",PersonaMensaje.class)
    			.setParameter("salida", salida).setParameter("idPersona", idPersona).getResultList()) {
			em.remove(bean);
    	}
    	for (BonoDetalle bean: em.createNamedQuery("BonoDetalle.findByIdPersona",BonoDetalle.class)
    			.setParameter("salida",salida).setParameter("idPersona",idPersona).getResultList()) {
			em.remove(bean);
    	}
		em.remove(detalle);
	}
}