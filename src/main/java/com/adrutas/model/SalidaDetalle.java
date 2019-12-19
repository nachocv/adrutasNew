package com.adrutas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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
@NamedQuery(name="SalidaDetalle.listAlfa", query = "SELECT s FROM SalidaDetalle s "
		+ "WHERE s.id.salida = :salida ORDER BY s.persona.apellido1,s.persona.apellido2,s.persona.nombre")
@NamedQuery(name="SalidaDetalle.find", query="SELECT new SalidaDetalle(s.id,s.bus,s.asiento,s.importe,s.pago,"
		+ "s.seguroDia,s.observacion,s.recibo,s.persona,s.salidaBean,b) FROM SalidaDetalle s LEFT JOIN BonoDetalle b "
		+ "ON s.id.salida=b.salidaBean.salida and s.id.idPersona=b.idPersona WHERE s.id.salida=:salida "
		+ "ORDER BY s.bus,s.asiento,s.recibo.idRecibo")
@NamedQuery(name="SalidaDetalle.findByPersona", query="SELECT s FROM SalidaDetalle s "
		+ "WHERE s.id.salida=:salida and s.id.idPersona=:idPersona")
@NamedQuery(name="SalidaDetalle.findByPersonaAndSalidas", query="SELECT s FROM SalidaDetalle s "
		+ "WHERE s.id.idPersona=:idPersona and s.id.salida >= :salidaIni and s.id.salida < :salidaFin")
@NamedQuery(name="SalidaDetalle.countByPersonaAndSalidas", query="SELECT count(s.id.salida) FROM SalidaDetalle s "
		+ "WHERE s.id.idPersona=:idPersona and s.id.salida >= :salidaIni and s.id.salida < :salidaFin")
@NamedQuery(name="SalidaDetalle.getLast", query="SELECT s.contador FROM SalidaDetalle s order by s.contador desc")
@NamedQuery(name="SalidaDetalle.findN",query="SELECT s FROM SalidaDetalle s WHERE salidaBean.tipo in ('N','R') and "
		+ "(salidaBean.anyo=:anyo OR salidaBean.anyo=:anyo-1) AND id_persona=:id_persona ORDER BY s.salidaBean.salida")

public class SalidaDetalle implements Serializable {
	private static final long serialVersionUID = -6755376347925749528L;
	private static final Logger log = Logger.getLogger(SalidaDetalle.class.getName());
	private static final List<Integer> precioTipoNoSocio = Arrays.asList(new Integer[] {5,6,7,8});

	@EmbeddedId
	private SalidaDetallePK id;

	private String anyo;

	private Short asiento;

	private String banco;

	private Short bono;

	private Short bus;

//	private byte contabilizado;
//
//	private byte contaContador;
//
	private int contador;
//
//	private String dondeRecoger;
//
//	@Temporal(TemporalType.DATE)
//	private Date fechaconta;

	private String fp;

//	private String habitacion;

	private BigDecimal importe;

//	private String jd;
//
//	private String numero;

	@Lob
	@Column(name="observacion")
	private String observacion;

	private BigDecimal pago;

//	private byte recoger;
//
//	private short salidabono;

	@Column(name="seguro_dia")
	private byte seguroDia;

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

	public SalidaDetalle() {
	}

	public SalidaDetalle(SalidaDetallePK id, Short bus, Short asiento, BigDecimal importe, BigDecimal pago,
			byte seguroDia, String observacion, Recibo recibo, Persona persona, Salida salidaBean, BonoDetalle bonoDetalle) {
		this.id = id;
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

//	public byte getContabilizado() {
//		return this.contabilizado;
//	}
//
//	public void setContabilizado(byte contabilizado) {
//		this.contabilizado = contabilizado;
//	}
//
//	public byte getContaContador() {
//		return this.contaContador;
//	}
//
//	public void setContaContador(byte contaContador) {
//		this.contaContador = contaContador;
//	}

	public int getContador() {
		return this.contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

//	public String getDondeRecoger() {
//		return this.dondeRecoger;
//	}
//
//	public void setDondeRecoger(String dondeRecoger) {
//		this.dondeRecoger = dondeRecoger;
//	}
//
//	public Date getFechaconta() {
//		return this.fechaconta;
//	}
//
//	public void setFechaconta(Date fechaconta) {
//		this.fechaconta = fechaconta;
//	}

	public String getFp() {
		return this.fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
	}

//	public String getHabitacion() {
//		return this.habitacion;
//	}
//
//	public void setHabitacion(String habitacion) {
//		this.habitacion = habitacion;
//	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

//	public String getJd() {
//		return this.jd;
//	}
//
//	public void setJd(String jd) {
//		this.jd = jd;
//	}
//
//	public String getNumero() {
//		return this.numero;
//	}
//
//	public void setNumero(String numero) {
//		this.numero = numero;
//	}

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

	public byte getSeguroDia() {
		return this.seguroDia;
	}

	public void setSeguroDia(byte seguroDia) {
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
		Map<String,Map<String,String>> mSalidas = new HashMap<String,Map<String,String>>();
		Map<String,List<String>> mEspeciales = new HashMap<String,List<String>>();
		List<String> lEspeciales;
		Map<String,String> mBean;
		mResult.put("salidas",mSalidas);
		mResult.put("especiales",mEspeciales);
        try {
    		em = EntityManagerFactories.getEM();
    		int idPersona = ((Double) map.get("id_persona")).intValue();
    		for (String salida: (List<String>) map.get("array")) {
        		Salida beanSalida = em.createNamedQuery("Salida.findBySalida", Salida.class)
        				.setParameter("salida",salida).getSingleResult();
        		mEspeciales.put(salida, lEspeciales=new ArrayList<String>());
        		for (SalidaDetalle salidaDetalle: em.createNamedQuery("SalidaDetalle.findByPersonaAndSalidas",
        				SalidaDetalle.class).setParameter("idPersona",idPersona).setParameter("salidaIni",
        				beanSalida.getSalidaDesde()).setParameter("salidaFin",salida).getResultList()) {
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
    	SalidaDetalle detalle = new SalidaDetalle();
        try {
    		em = EntityManagerFactories.getEM();
			em.getTransaction().begin();
        	SalidaDetallePK id = new SalidaDetallePK();
        	Recibo recibo = new Recibo();
			Salida beanSalida = em.createNamedQuery("Salida.findBySalida",Salida.class)
					.setParameter("salida",salida).getSingleResult();
			if (beanSalida.getPlazas()<=beanSalida.getSalidaDetalles().size()) {
				return "Ya no hay plazas";
			}
			Map<Integer,Ficha> mFicha = new HashMap<Integer,Ficha>();
			int anyo = beanSalida.getAnyo();
			for (Ficha ficha: em.createNamedQuery("Ficha.findAnyo", Ficha.class).setParameter("idPersona", idPersona)
					.setParameter("anyo", anyo).getResultList()) {
				mFicha.put(ficha.getId().getAnyo(),ficha);
			}
			Date baja;
			boolean isDirectivo = false;
	        boolean esSocio = false;
			Date fechaInicio = beanSalida.getFechaInicio();
			Ficha ficha = null;
			Ficha fichaAct = mFicha.get(anyo);
	        boolean esSocioAct = fichaAct==null || fichaAct.getImportecuota().signum()==0;
			if (fichaAct!=null) {
				for (Directiva directiva: fichaAct.getPersona().getDirectivas()) {
					if (fechaInicio.compareTo(directiva.getId().getAlta())>=0
							&& ((baja = directiva.getBaja())==null || fechaInicio.compareTo(baja)<=0)) {
						isDirectivo = true;
						break;
					}
				}
			}
            if (isDirectivo) {
                detalle.setFp("JD");
            } else {
            	Integer anyoAnt = 0;
            	int contGS = 0;
            	boolean tieneGC = false;
            	detalle.setFp("ME");
            	String formaPago;
            	String tipo = beanSalida.getTipo();
                if ("N".equals(tipo) || "R".equals(tipo)) {
                    for (SalidaDetalle bean: em.createNamedQuery("SalidaDetalle.findN",SalidaDetalle.class).setParameter(
                    		"anyo",beanSalida.getAnyo()).setParameter("id_persona",idPersona).getResultList()) {
                    	if (!bean.getSalidaBean().getAnyo().equals(anyoAnt)) {
                            anyoAnt = bean.getSalidaBean().getAnyo();
                            contGS = 0;
                	        esSocio = (ficha = mFicha.get(anyoAnt))!=null && ficha.getImportecuota().signum()!=0;
                        }
                        if ("GS".equals(formaPago = bean.getRecibo().getFormapago().getCodigo())) {
                            contGS = 0;
                        } else if ("GC".equals(formaPago) && anyoAnt==anyo) {
                        	tieneGC = true;
                        }
                        if (Constante.ORDINARIAS.contains(formaPago) && esSocio) {
                            contGS++;
                        }
                    }
                	if (contGS>=Constante.numGS) {
                    	detalle.setFp("GS");
                	} else if (!tieneGC && "N".equals(tipo) && esSocioAct) {
                    	detalle.setFp("GC");
                    } else {
                    	List<BonoDetalle> lBonos = em.createNamedQuery("BonoDetalle.getUltimo",BonoDetalle.class)
                    			.setParameter("idPersona",idPersona).setMaxResults(1).getResultList();
                    	if (!lBonos.isEmpty()) {
                        	BonoDetalle bono = lBonos.get(0);
                        	if (bono.getId().getUso()<10) {
                        		BonoDetalle bonoDetalle = new BonoDetalle();
                        		bonoDetalle.setIdPersona(idPersona);
                        		bonoDetalle.setSalidaBean(beanSalida);
                        		BonoDetallePK bonoDetallePK = new BonoDetallePK();
                        		bonoDetalle.setId(bonoDetallePK);
                        		bonoDetallePK.setBono(bono.getId().getBono());
                        		bonoDetallePK.setUso((short) (bono.getId().getUso()+1));
                        		detalle.setBonoDetalle(bonoDetalle);
                        		detalle.setFp("BO");
                        		em.persist(bonoDetalle);
                        	}
                    	}
                    }
                }
            }

//			Calculo del precio
	        Map<Object, BigDecimal> precio = new HashMap<Object, BigDecimal>();
	        boolean soloSocios = true;
	        int precioTipo;
			for (SalidaPrecio salidaPrecio: beanSalida.getSalidaPrecios()) {
	            precio.put((precioTipo = salidaPrecio.getPrecioTipoBean().getPrecioTipo()), salidaPrecio.getPrecio());
            	if (precioTipoNoSocio.contains(precioTipo)) {
            		soloSocios = false;
            	}
	        }
	        BigDecimal importe;
	        BigDecimal ingreso;
	        BigDecimal pago = BigDecimal.ZERO;
        	BigDecimal impSegDia = new BigDecimal(Static.getPropiedadesanuales(beanSalida.getAnyo(),"S1D"));
	        boolean seguro_dia = fichaAct==null || "".equals(fichaAct.getTipoLicencia());
	        if (esSocioAct) {

//	            No socio
            	if (soloSocios) {
            		return "Salida solo para socios";
            	}
	            if (seguro_dia) {

//	                No socio sin federar
	                if ((importe = precio.get(6))==null) {

//	                    Salida sin precio para no socio sin federar
	                    pago = impSegDia;
	                    importe = precio.get(5).add(pago);
	                } else {

//	                    Salida con precio para no socio sin federar
	                    pago = importe.subtract(precio.get(5));
	                }
	            } else {

//	                No socio federado
	                importe = precio.get(5);
	            }
	        } else {

//	            Socio
	            if (seguro_dia) {

//	                Socio con seguro diario
	                if ((importe = precio.get(3))==null) {

//	                    Salida sin precio para socio sin federar
	                    pago = impSegDia;
	                    importe = precio.get(2).add(pago);
	                } else {

//	                    Salida con precio para socio sin federar
	                    pago = importe.subtract(precio.get(2));
	                }
	            } else {

//	                Socio federado
	                importe = precio.get(2);
	            }
	        }
	        if (isDirectivo) {
	            if ((ingreso = precio.get(4))==null) {
	                ingreso = BigDecimal.ZERO;
	            }
	        } else {
	            if (Constante.SIN_IMPORTE.contains(detalle.getFp())) {
	                importe = pago;
	            }
	            ingreso = importe;
	        }
			recibo.setIdRecibo(em.createNamedQuery("Recibo.getLast", Integer.class)
					.setMaxResults(1).getSingleResult() + 1);
			recibo.setTabla("salida_detalle");
			recibo.setFecha(new Date());
			recibo.setImporte(importe);
			recibo.setFormapago(Static.mFormaPagoAll.get(detalle.getFp()));
			detalle.setIngreso(ingreso);
			detalle.setSalidaBean(beanSalida);
			detalle.setId(id);
        	id.setIdPersona(idPersona);
        	id.setSalida(salida);
        	detalle.setRecibo(recibo);
        	detalle.setPago(pago);
        	detalle.setSeguroDia((byte) (seguro_dia? 1: 0));
        	detalle.setImporte(importe);
        	detalle.setContador(em.createNamedQuery("SalidaDetalle.getLast", Integer.class)
					.setMaxResults(1).getSingleResult() + 1);
			em.persist(recibo);
			em.persist(detalle);
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

	public static synchronized void update(Map<String,Object> listaApuntados) {
		EntityManager em = null;
    	String bonoUso;
    	String[] bono;
    	int idPersona;
    	String observacion;
    	String mensaje;
    	String asiento;
    	String bus;
    	SalidaDetalle detalle;
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
			TypedQuery<SalidaDetalle> querySalida = em.createNamedQuery(
					"SalidaDetalle.findByPersona",SalidaDetalle.class).setParameter("salida",salida);
			TypedQuery<PersonaMensaje> queryMensaje = em.createNamedQuery(
					"PersonaMensaje.find",PersonaMensaje.class).setParameter("salida", salida);
			
			for (BonoDetalle bean:em.createNamedQuery("BonoDetalle.getSalida",BonoDetalle.class).
					setParameter("salida", salida).getResultList()) {
				mBonoDetalle.put(bean.getIdPersona(),bean);
			}
			for (Map<String,Object> map: (List<Map<String,Object>>) listaApuntados.get("apuntados")) {
		    	detalle = querySalida.setParameter("idPersona",
						idPersona = Integer.parseInt((String) map.get("id_persona"))).getSingleResult();
				detalle.fp = (String) map.get("estado");
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
		        		} else {
		        			bonoDetalle.getId().setBono(Short.parseShort((bono = bonoUso.split("-"))[0]));
		        			bonoDetalle.getId().setUso(Short.parseShort(bono[1]));
		        		}
	        			em.persist(bonoDetalle);
		        	}
	        	} else if (bonoDetalle!=null) {
	        		em.remove(bonoDetalle);
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
//	        					em.persist(personaMensaje);
	        				} else {
	        					em.persist(new PersonaMensaje(salida,idPersona,now,mensaje));
	        				}
	        			}
	        		}
	        	}
	        	recibo = detalle.recibo;
				recibo.setFecha(now);
				recibo.setFormapago(Static.mFormaPagoAll.get(detalle.fp));
				recibo.setImporte(detalle.ingreso);
//				em.persist(detalle);
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
        try {
    		em = EntityManagerFactories.getEM();
			em.getTransaction().begin();
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
			em.getTransaction().commit();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "SalidaDetalle.del", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
	}
}