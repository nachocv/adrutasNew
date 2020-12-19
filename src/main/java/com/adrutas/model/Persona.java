package com.adrutas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.dao.Static;
import com.google.gson.Gson;

import adrutas.com.Constante;

/**
 * The persistent class for the persona database table.
 * 
 */
@Entity@Table(name = "persona")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
@NamedQuery(name="Persona.find", query="SELECT p FROM Persona p WHERE salida=:salida")
@NamedQuery(name="Persona.findByNombre", query="SELECT p FROM Persona p WHERE TRIM(UPPER(CONCAT(nombre, ' ', apellido1"
		+ ", ' ', apellido2))) = :filtro OR UPPER(usuario) = :filtro")
@NamedQuery(name="Persona.findByDni", query="SELECT p FROM Persona p WHERE dni = :filtro")
@NamedQuery(name="Persona.findByTelefono", query="SELECT p FROM Persona p LEFT JOIN p.socioTelefonos t "
		+ "WHERE t.id.telefono = :filtro")
@NamedQuery(name="Persona.findByEmail", query="SELECT p FROM Persona p "
		+ "LEFT JOIN p.socioEmails e WHERE e.id.email = :filtro")
@NamedQuery(name="Persona.findByIdPersona", query="SELECT p FROM Persona p LEFT JOIN p.socioEmails e LEFT JOIN "
		+ "p.socioTelefonos t WHERE p.idPersona = :idPersona")
@NamedQuery(name="Persona.filterByNombre", query="SELECT p FROM Persona p WHERE TRIM(UPPER(CONCAT(nombre, ' ', "
		+ "apellido1, ' ', apellido2))) LIKE CONCAT('%',UPPER(:filtro),'%') OR usuario LIKE CONCAT(:filtro,'%')")
@NamedQuery(name="Persona.filterByDni", query="SELECT p FROM Persona p WHERE dni LIKE CONCAT(:filtro,'%')")
@NamedQuery(name="Persona.filterByTelefono", query="SELECT p FROM Persona p LEFT JOIN p.socioTelefonos t "
		+ "WHERE t.id.telefono LIKE CONCAT(:filtro,'%')")
@NamedQuery(name="Persona.filterByEmail", query="SELECT p FROM Persona p LEFT JOIN p.socioEmails e "
		+ "WHERE e.id.email LIKE CONCAT(:filtro,'%')")
@NamedQuery(name="Persona.getIdPersona",
		query="SELECT p.idPersona FROM Persona p WHERE p.idPersona not in (9999,8888) order by p.idPersona desc")

public class Persona implements Serializable {
	private static final long serialVersionUID = 2031642939280211222L;
	private static final Logger log = Logger.getLogger(Persona.class.getName());
//	private static final DateFormat dF1 = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
//	private static final DateFormat dF2 = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "US"));

	@Id
	@Column(name="id_persona")
	private int idPersona;

	@Column(name="apellido1")
	private String apellido1;

	@Column(name="apellido2")
	private String apellido2;

	private String baja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cad_pasaporte")
	private Date cadPasaporte;

	@Column(name="codigo_postal")
	private String codigoPostal;

	private String colaborador;

	private byte correo;

	@Lob
	private String deuda;

	private String dni;

	private String domicilio;

	@Temporal(TemporalType.DATE)
	@Column(name="`FECHA BAJA`")
	private Date fecha_baja;

	private String gratisExtras;

	private String gratisFotos;

	private String gratisNS;

	private String gratisnumsalidas;

	private Integer idPerfil;

	@Temporal(TemporalType.DATE)
	private Date ingreso;

	private String jd;

	@Temporal(TemporalType.DATE)
	private Date nacimiento;

	private String nombre;

	@Lob
	private String nota;

	private String pasaporte;

	private String password;

	private String poblacion;

	private Byte poseconta;

	@Column(name="provincia")
	private String provincia;

	@Column(name="pz_castilla")
	private String pzCastilla;

	@Column(name="sexo")
	private String sexo;

	private String socio;

	private String talla;

	private Integer tipoIdentificacion;

	private String usuario;

	private byte vetado;

	private String veto;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="persona")
	private List<Album> albums;

	//bi-directional many-to-one association to Directiva
	@OneToMany(mappedBy="persona")
	private List<Directiva> directivas;

	//bi-directional many-to-one association to Ficha
	@OneToMany(mappedBy="persona")
	private List<Ficha> fichas;

	@Transient
	private Map<Integer,Ficha> mFichas = null;

	//bi-directional many-to-one association to SalidaDetalle
	@OneToMany(mappedBy="persona")
	private List<SalidaDetalle> salidaDetalles;

	//bi-directional many-to-one association to SocioEmail
	@OneToMany(mappedBy="persona")
	@OrderBy("orden")
	private List<SocioEmail> socioEmails;

	//bi-directional many-to-one association to SocioTelefono
	@OneToMany(mappedBy="persona")
	@OrderBy("orden")
	private List<SocioTelefono> socioTelefonos;

	public Persona() {
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getBaja() {
		return this.baja;
	}

	public void setBaja(String baja) {
		this.baja = baja;
	}

	public Date getCadPasaporte() {
		return this.cadPasaporte;
	}

	public void setCadPasaporte(Date cadPasaporte) {
		this.cadPasaporte = cadPasaporte;
	}

	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getColaborador() {
		return this.colaborador;
	}

	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}

	public byte getCorreo() {
		return this.correo;
	}

	public void setCorreo(byte correo) {
		this.correo = correo;
	}

	public String getDeuda() {
		return this.deuda;
	}

	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFecha_baja() {
		return this.fecha_baja;
	}

	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public String getGratisExtras() {
		return this.gratisExtras;
	}

	public void setGratisExtras(String gratisExtras) {
		this.gratisExtras = gratisExtras;
	}

	public String getGratisFotos() {
		return this.gratisFotos;
	}

	public void setGratisFotos(String gratisFotos) {
		this.gratisFotos = gratisFotos;
	}

	public String getGratisNS() {
		return this.gratisNS;
	}

	public void setGratisNS(String gratisNS) {
		this.gratisNS = gratisNS;
	}

	public String getGratisnumsalidas() {
		return this.gratisnumsalidas;
	}

	public void setGratisnumsalidas(String gratisnumsalidas) {
		this.gratisnumsalidas = gratisnumsalidas;
	}

	public Integer getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Date getIngreso() {
		return this.ingreso;
	}

	public void setIngreso(Date ingreso) {
		this.ingreso = ingreso;
	}

	public String getJd() {
		return this.jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public Date getNacimiento() {
		return this.nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNota() {
		return this.nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getPasaporte() {
		return this.pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public Byte getPoseconta() {
		return this.poseconta;
	}

	public void setPoseconta(Byte poseconta) {
		this.poseconta = poseconta;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPzCastilla() {
		return this.pzCastilla;
	}

	public void setPzCastilla(String pzCastilla) {
		this.pzCastilla = pzCastilla;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSocio() {
		return this.socio;
	}

	public void setSocio(String socio) {
		this.socio = socio;
	}

	public String getTalla() {
		return this.talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public Integer getTipoIdentificacion() {
		return this.tipoIdentificacion;
	}

	public void setTipoIdentificacion(Integer tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public byte getVetado() {
		return this.vetado;
	}

	public void setVetado(byte vetado) {
		this.vetado = vetado;
	}

	public String getVeto() {
		return this.veto;
	}

	public void setVeto(String veto) {
		this.veto = veto;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setPersona(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setPersona(null);

		return album;
	}

	public List<Directiva> getDirectivas() {
		return this.directivas;
	}

	public void setDirectivas(List<Directiva> directivas) {
		this.directivas = directivas;
	}

	public Directiva addDirectiva(Directiva directiva) {
		getDirectivas().add(directiva);
		directiva.setPersona(this);

		return directiva;
	}

	public Directiva removeDirectiva(Directiva directiva) {
		getDirectivas().remove(directiva);
		directiva.setPersona(null);

		return directiva;
	}

	public List<Ficha> getFichas() {
		return this.fichas;
	}

	public Ficha getFicha(int anyo) {
		if (mFichas==null) {
			mFichas = new TreeMap<Integer,Ficha>();
			for (Ficha ficha: fichas) {
				mFichas.put(ficha.getId().getAnyo(), ficha);
			}
		}
		return mFichas.get(anyo);
	}

	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}

	public Ficha addFicha(Ficha ficha) {
		getFichas().add(ficha);
		ficha.setPersona(this);
		mFichas = null;
		return ficha;
	}

	public Ficha removeFicha(Ficha ficha) {
		getFichas().remove(ficha);
		ficha.setPersona(null);
		mFichas = null;

		return ficha;
	}

	public List<SalidaDetalle> getSalidaDetalles() {
		return this.salidaDetalles;
	}

	public void setSalidaDetalles(List<SalidaDetalle> salidaDetalles) {
		this.salidaDetalles = salidaDetalles;
	}

	public SalidaDetalle addSalidaDetalle(SalidaDetalle salidaDetalle) {
		getSalidaDetalles().add(salidaDetalle);
		salidaDetalle.setPersona(this);

		return salidaDetalle;
	}

	public SalidaDetalle removeSalidaDetalle(SalidaDetalle salidaDetalle) {
		getSalidaDetalles().remove(salidaDetalle);
		salidaDetalle.setPersona(null);

		return salidaDetalle;
	}

	public List<SocioEmail> getSocioEmails() {
		return this.socioEmails;
	}

	public void setSocioEmails(List<SocioEmail> socioEmails) {
		this.socioEmails = socioEmails;
	}

	public SocioEmail addSocioEmail(SocioEmail socioEmail) {
		getSocioEmails().add(socioEmail);
		socioEmail.setPersona(this);

		return socioEmail;
	}

	public SocioEmail removeSocioEmail(SocioEmail socioEmail) {
		getSocioEmails().remove(socioEmail);
		socioEmail.setPersona(null);

		return socioEmail;
	}

	public List<SocioTelefono> getSocioTelefonos() {
		return this.socioTelefonos;
	}

	public void setSocioTelefonos(List<SocioTelefono> socioTelefonos) {
		this.socioTelefonos = socioTelefonos;
	}

	public SocioTelefono addSocioTelefono(SocioTelefono socioTelefono) {
		getSocioTelefonos().add(socioTelefono);
		socioTelefono.setPersona(this);

		return socioTelefono;
	}

	public SocioTelefono removeSocioTelefono(SocioTelefono socioTelefono) {
		getSocioTelefonos().remove(socioTelefono);
		socioTelefono.setPersona(null);

		return socioTelefono;
	}

	public boolean esSocio(int anyo) {
        Ficha ficha = getFicha(anyo);
        return ficha!=null && ficha.getImportecuota().signum()!=0;
	}

	public static List<Persona> findAll() {
		EntityManager em = null;
        try {
    		em = EntityManagerFactories.getEM();
    		return em.createNamedQuery("Persona.findAll", Persona.class).getResultList();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Salida.findAll", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
		return new ArrayList<Persona>();
	}

	public static List<Persona> find(String salida) {
		EntityManager em = null;
        try {
    		em = EntityManagerFactories.getEM();
    		return em.createNamedQuery("Persona.find", Persona.class).setParameter("salida", salida).getResultList();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Persona.find", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
		return new ArrayList<Persona>();
	}

	public static Map<String, Object> findByIdPersona2(int idPersona) {
		EntityManager em = null;
    	Map<String, Object> mPersona = new HashMap<String, Object>();
        try {
    		em = EntityManagerFactories.getEM();
    		Persona persona = em.createNamedQuery("Persona.findByIdPersona", Persona.class)
    				.setParameter("idPersona", idPersona).getSingleResult();
    		if (persona!=null) {
    	    	List<Map<String, Object>> lSalidas;
    	    	Map<String, Object> mSalida;
    			List<BonoDetalle> lBonoDetalle;
    			BonoDetalle bonoDetalle;
    			String salida;
    	    	Map<Integer,List<Map<String, Object>>> mFichas = new TreeMap<Integer,List<Map<String, Object>>>();
            	mPersona.put("usuario", persona.getUsuario());
            	mPersona.put("fichas", mFichas);
    			TypedQuery<BonoDetalle> queryFindByIdPersona = em.createNamedQuery(
    					"BonoDetalle.findByIdPersona",BonoDetalle.class);
    			TypedQuery<SalidaDetalle>  queryFindByAnyoAndPersona = em.createNamedQuery(
    					"SalidaDetalle.findByAnyoAndPersona",SalidaDetalle.class);
            	int anyo = -1;
            	for (Ficha ficha: persona.getFichas()) {
            		mFichas.put(anyo = ficha.getId().getAnyo(),lSalidas = new ArrayList<Map<String, Object>>());
            		for (SalidaDetalle bean: queryFindByAnyoAndPersona.setParameter("anyo", anyo).
            				setParameter("idPersona", idPersona).getResultList()) {
                		lSalidas.add(mSalida = new HashMap<String, Object>());
                		mSalida.put("salida", salida=bean.getSalidaBean().getSalida());
                		mSalida.put("fechIni", Constante.dF12.format(bean.getSalidaBean().getFechaInicio()));
                		mSalida.put("descripcion", bean.getSalidaBean().getDescripcion());
            			mSalida.put("vino",bean.isParticipo()? "Si": "No");
            			mSalida.put("tipo",bean.getSalidaBean().getTipo());
            			mSalida.put("fp",bean.getRecibo().getFormapago().getCodigo());
            			if (!(lBonoDetalle = queryFindByIdPersona.setParameter("salida",salida).
            					setParameter("idPersona", idPersona).getResultList()).isEmpty()) {
            				mSalida.put("bono",String.valueOf((bonoDetalle = lBonoDetalle.get(0)).getId().getBono())
            						+ "-" + String.valueOf(bonoDetalle.getId().getUso()));
            			}
            		}
            	}
            }
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Persona.find", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
        return mPersona;
	}


	public static Map<String, Object> findByIdPersona(int idPersona) {
		EntityManager em = null;
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Map<String, Object>> list;
    	Map<String, Object> mapBean1;
    	Map<Integer,Map<String, Object>> mapBean2;
    	Map<String, Object> mapBean3;
    	int fichaYear;
        try {
    		em = EntityManagerFactories.getEM();
    		Persona persona = em.createNamedQuery("Persona.findByIdPersona", Persona.class)
    				.setParameter("idPersona", idPersona).getSingleResult();
    		if (persona!=null) {
    			List<BonoDetalle> lBonoDetalle;
    			BonoDetalle bonoDetalle;
    			String salida;
    			TypedQuery<BonoDetalle> queryFindByIdPersona = em.createNamedQuery(
    					"BonoDetalle.findByIdPersona",BonoDetalle.class);
    			TypedQuery<SalidaDetalle>  queryFindByAnyoAndPersona = em.createNamedQuery(
    					"SalidaDetalle.findByAnyoAndPersona",SalidaDetalle.class);
            	map.put("id_persona", persona.getIdPersona());
            	map.put("usuario", persona.getUsuario());
            	map.put("dni", persona.getDni());
            	map.put("nombre", persona.getNombre());
            	map.put("apellido1", persona.getApellido1());
            	map.put("apellido2", persona.getApellido2());
            	map.put("codigoPostal", persona.getCodigoPostal());
            	map.put("domicilio", persona.getDomicilio());
            	map.put("poblacion", persona.getPoblacion());
            	map.put("provincia", persona.getProvincia());
            	map.put("correo", persona.getCorreo());
            	map.put("sexo", persona.getSexo());
            	map.put("nacimiento", persona.getNacimiento()==null? "": persona.getNacimiento().toString());
            	map.put("pzCastilla", persona.getPzCastilla());
            	map.put("pasaporte", persona.getPasaporte());
            	map.put("cadPasaporte", persona.getCadPasaporte()==null? "": persona.getCadPasaporte().toString());
            	map.put("nota", persona.getNota());
            	map.put("vetado", persona.getVetado());
            	map.put("veto", persona.getVeto());
            	map.put("FICHA_YEAR", fichaYear=Static.getFichaYear());
            	map.put("FPs", Static.getMfp());
            	map.put("telefonos", list = new ArrayList<Map<String, Object>>());
            	for (SocioTelefono bean: persona.getSocioTelefonos()) {
                	list.add(mapBean1 = new HashMap<String, Object>());
            		mapBean1.put("nota", bean.getNota());
            		mapBean1.put("orden", bean.getOrden());
            		mapBean1.put("telefono", bean.getId().getTelefono());
            	}
            	map.put("emails", list = new ArrayList<Map<String, Object>>());
            	for (SocioEmail bean: persona.getSocioEmails()) {
            		list.add(mapBean1 = new HashMap<String, Object>());
            		mapBean1.put("nota", bean.getNota());
            		mapBean1.put("orden", bean.getOrden());
            		mapBean1.put("email", bean.getId().getEmail());
            	}
            	map.put("fichas", mapBean2 = new HashMap<Integer,Map<String, Object>>());
            	int anyo = -1;
            	for (Ficha ficha: persona.getFichas()) {
            		if (anyo==-1) {
            			if (persona.getIngreso()==null) {
                        	map.put("ingreso", ficha.getFecha().toString());
            			} else {
                        	map.put("ingreso", persona.getIngreso().toString());
            			}
            		}
            		mapBean2.put(anyo = ficha.getId().getAnyo(),mapBean1 = new HashMap<String, Object>());
            		mapBean1.put("importecuota", ficha.getImportecuota());
            		mapBean1.put("orden", ficha.getTipoLicencia());
            		mapBean1.put("importelicencia", ficha.getImportelicencia());
            		mapBean1.put("club", ficha.getClub());
            		mapBean1.put("fp", ficha.getFp());
            		mapBean1.put("tipoLicencia", ficha.getTipoLicencia());
            		mapBean1.put("regalo", ficha.getRegalo());
            		mapBean1.put("licencias", Static.getLicencia(ficha.getId().getAnyo()));
            		mapBean1.put("opciones", ficha.getOpciones());
            		mapBean1.put("fecha", Constante.dF12.format(ficha.getFecha()));
            		mapBean1.put("fechavto", ficha.getFechavto()==null? "":Constante.dF12.format(ficha.getFechavto()));
        			ficha.getOpciones().iterator();
        			mapBean1.put("salidas",list = new ArrayList<Map<String, Object>>());
            		for (SalidaDetalle bean: queryFindByAnyoAndPersona.setParameter("anyo", anyo).
            				setParameter("idPersona", idPersona).getResultList()) {
                		list.add(mapBean3 = new HashMap<String, Object>());
                		mapBean3.put("salida", salida=bean.getSalidaBean().getSalida());
                		mapBean3.put("fechIni", Constante.dF12.format(bean.getSalidaBean().getFechaInicio()));
                		mapBean3.put("descripcion", bean.getSalidaBean().getDescripcion());
            			mapBean3.put("vino",bean.isParticipo()? "Si": "No");
            			mapBean3.put("tipo",bean.getSalidaBean().getTipo());
            			mapBean3.put("fp",bean.getRecibo().getFormapago().getCodigo());
            			if (!(lBonoDetalle = queryFindByIdPersona.setParameter("salida",salida).
            					setParameter("idPersona", idPersona).getResultList()).isEmpty()) {
            				mapBean3.put("bono",String.valueOf((bonoDetalle = lBonoDetalle.get(0)).getId().getBono())
            						+ "-" + String.valueOf(bonoDetalle.getId().getUso()));
            			}
            		}
            	}
            	if (!mapBean2.containsKey(fichaYear)) {
            		mapBean2.put(fichaYear,mapBean1 = new HashMap<String, Object>());
            		mapBean1.put("importecuota", 0);
            		mapBean1.put("orden", 0);
            		mapBean1.put("importelicencia", 0);
            		mapBean1.put("club", "");
            		mapBean1.put("fp", "");
            		mapBean1.put("tipoLicencia", "");
            		mapBean1.put("regalo", 0);
            		mapBean1.put("licencias", Static.getLicencia(fichaYear));
            		mapBean1.put("fechavto", "");
            	}
            }
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Persona.find", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
        return map;
	}

	public static List<Persona> findByEmail(String filtro,EntityManager em,int maxResults) {
    	List<Persona> list = new ArrayList<Persona>();
        if (Constante.PATTERN_INI_EMAIL.matcher(filtro).matches()) {
            list = em.createNamedQuery("Persona.findByEmail", Persona.class).setMaxResults(maxResults)
        			.setParameter("filtro", filtro).getResultList();
        }
		return list;
	}
    public static List<Persona> findExact(String filtro) {
    	List<Persona> list = new ArrayList<Persona>();
    	filtro = filtro.trim().toUpperCase();
		EntityManager em = null;
		int maxResults = 2;
        try {
    		em = EntityManagerFactories.getEM();
            if (Constante.PATTERN_NOMBRE.matcher(filtro).matches()) {
            	if ((list = em.createNamedQuery("Persona.findByNombre", Persona.class).setMaxResults(maxResults)
            			.setParameter("filtro", filtro).getResultList()).size()>=maxResults) {
            		return list;
            	}
            }
            Map<String,Object> map = Static.normalizaDni(filtro);
            if ((int) map.get("tipoIdentificacion")!=3) {
                list.addAll(em.createNamedQuery("Persona.findByDni", Persona.class).setMaxResults(maxResults)
            			.setParameter("filtro", map.get("dni")).getResultList());
            	if (list.size()>=maxResults) {
            		return list;
            	}
            }
            if (Constante.PATTERN_TELEFONO.matcher(filtro).matches()) {
                list.addAll(em.createNamedQuery("Persona.findByTelefono", Persona.class).setMaxResults(maxResults)
            			.setParameter("filtro", filtro).getResultList());
            	if (list.size()>=maxResults) {
            		return list;
            	}
            }
            list.addAll(findByEmail(filtro,em,maxResults));
        	if (list.size()>=maxResults) {
        		return list;
        	}
            if (Constante.PATTERN_ID_PERSONA.matcher(filtro).matches()) {
                list.addAll(em.createNamedQuery("Persona.findByIdPersona", Persona.class).setMaxResults(maxResults)
            			.setParameter("idPersona", filtro.substring(1).replaceFirst("^0+(?!$)", "")).getResultList());
            	if (list.size()>=maxResults) {
            		return list;
            	}
            }
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Persona.filter", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
        return list;
    }

    public static Collection<Persona> filter(String filtro) {
    	Map<Integer, Persona> map = new HashMap<Integer, Persona>();
    	filtro = filtro.trim().toUpperCase();
		EntityManager em = null;
		int maxResults = 10;
        try {
    		em = EntityManagerFactories.getEM();
            if (Constante.PATTERN_NOMBRE.matcher(filtro).matches()) {
            	for (Persona persona: em.createNamedQuery("Persona.filterByNombre", Persona.class).setMaxResults(maxResults)
            			.setParameter("filtro", filtro).getResultList()) {
            		map.put(persona.idPersona, persona);
            	}
            	if (map.size()>=maxResults) {
            		return map.values();
            	}
            }
            if (Constante.PATTERN_NIF1.matcher(filtro).matches()) {
            	for (String dni = filtro.replaceFirst("^0+(?!$)", ""); dni.length()<9; dni = "0" + dni) {
                	for (Persona persona: em.createNamedQuery("Persona.filterByDni", Persona.class).setMaxResults(maxResults)
                			.setParameter("filtro", dni).getResultList()) {
                		map.put(persona.idPersona, persona);
                	}
                	if (map.size()>=maxResults) {
                		return map.values();
                	}
            	}
            }
            if (Constante.PATTERN_NIE1.matcher(filtro).matches()) {
            	for (Persona persona: em.createNamedQuery("Persona.filterByDni", Persona.class).setMaxResults(maxResults)
            			.setParameter("filtro", filtro).getResultList()) {
            		map.put(persona.idPersona, persona);
            	}
            	if (map.size()>=maxResults) {
            		return map.values();
            	}
            }
            if (Constante.PATTERN_TELEFONO.matcher(filtro).matches()) {
            	for (Persona persona: em.createNamedQuery("Persona.filterByTelefono", Persona.class).setMaxResults(maxResults)
            			.setParameter("filtro", filtro).getResultList()) {
            		map.put(persona.idPersona, persona);
            	}
            	if (map.size()>=maxResults) {
            		return map.values();
            	}
            }
            if (Constante.PATTERN_INI_EMAIL.matcher(filtro).matches()) {
            	for (Persona persona: em.createNamedQuery("Persona.filterByEmail", Persona.class).setMaxResults(maxResults)
            			.setParameter("filtro", filtro).getResultList()) {
            		map.put(persona.idPersona, persona);
            	}
            	if (map.size()>=maxResults) {
            		return map.values();
            	}
            }
            if (Constante.PATTERN_ID_PERSONA.matcher(filtro).matches()) {
            	for (Persona persona: em.createNamedQuery("Persona.filterByIdPersona", Persona.class).setMaxResults(maxResults)
            			.setParameter("filtro", filtro).getResultList()) {
            		map.put(persona.idPersona, persona);
            	}
            	if (map.size()>=maxResults) {
            		return map.values();
            	}
            }
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Persona.filter", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
		return map.values();
    }

    public static void main(String[] args) {
    	String filtro = "009351";
    	for (String dni = filtro.replaceFirst("^0+(?!$)", ""); dni.length()<9; dni = "0" + dni) {
    	   	System.out.println(dni);
    	}
    }

    public static synchronized Map<String, Object> update(Map<String, Object> map) {
		String nombre = (String) map.get("nombre");
		if (nombre==null || (nombre=nombre.trim()).isEmpty()) {
			return null;
		}
		EntityManager em = null;
		String string;
		int idPersona = 0;
        try {
    		Persona persona = null;
    		em = EntityManagerFactories.getEM();
			em.getTransaction().begin();
        	boolean exists = !"".equals(map.get("id_persona"));
        	if (exists) {
        		idPersona = Integer.parseInt((String) map.get("id_persona"));
        		persona = em.createNamedQuery("Persona.findByIdPersona", Persona.class)
        				.setParameter("idPersona", idPersona).getSingleResult();
        	}
    		if (exists = persona!=null) {
        		persona.setIdPersona(idPersona);
    		} else {
    			persona = new Persona();
    			persona.setIdPersona(idPersona = em.createNamedQuery("Persona.getIdPersona", Integer.class).setMaxResults(1).getSingleResult() + 1);
    			em.persist(persona);
            	log.log(Level.SEVERE, "Insertando persona con idPersona: " + persona.getIdPersona() + ", map: " + new Gson().toJson(map));
    		}
    		String usuario = (String) map.get("usuario");
    		if (!"".equals(usuario)) {
    			persona.setUsuario(usuario);
    		}
            Map<String,Object> mBean = Static.normalizaDni((String) map.get("dni"));
    		persona.setDni((String) mBean.get("dni"));
    		persona.setTipoIdentificacion((Integer) mBean.get("tipoIdentificacion"));
    		persona.setNombre(nombre);
    		persona.setApellido1((String) map.get("apellido1"));
    		persona.setApellido2((String) map.get("apellido2"));
    		persona.setCodigoPostal((String) map.get("codigo_postal"));
    		persona.setDomicilio((String) map.get("domicilio"));
    		persona.setPoblacion((String) map.get("poblacion"));
    		persona.setProvincia((String) map.get("provincia"));
    		persona.setCorreo((byte) ((Boolean) map.get("correo")? 1: 0));
    		persona.setSexo((String) map.get("sexo"));
    		persona.setNacimiento(Static.getDate((String) map.get("nacimiento")));
    		persona.setIngreso((string = (String) map.get("ingreso"))==null? new Date(): Static.getDate(string));
    		persona.setPzCastilla((Boolean) map.get("pz_castilla")? "S": "");
    		persona.setPasaporte((String) map.get("pasaporte"));
    		persona.setCadPasaporte((Date) map.get("cadPasaporte"));
    		persona.setNota((String) map.get("nota"));
    		persona.setVetado((byte) ((Boolean) map.get("vetado")? 1: 0));
    		persona.setVeto((String) map.get("veto"));
    		List<SocioTelefono> lTelefonos = new ArrayList<SocioTelefono>();
    		persona.setSocioTelefonos(lTelefonos);
    		SocioTelefono telefono;
    		SocioTelefonoPK telefonoPK;
    		int orden = 0;
			em.createNamedQuery("SocioTelefono.del").setParameter("idPersona", idPersona).executeUpdate();
    		for (String bean: (List<String>) map.get("telefonos")) {
    			lTelefonos.add(telefono = new SocioTelefono());
    			telefono.setPersona(persona);
    			telefono.setOrden(orden++);
    			telefono.setId(telefonoPK = new SocioTelefonoPK());
    			telefonoPK.setIdPersona(idPersona);
    			telefonoPK.setTelefono(bean);
    			em.persist(telefono);
    		}
    		List<SocioEmail> lEmails = new ArrayList<SocioEmail>();
    		persona.setSocioEmails(lEmails);
    		SocioEmail email;
    		SocioEmailPK emailPK;
    		orden = 0;
			em.createNamedQuery("SocioEmail.del").setParameter("idPersona", idPersona).executeUpdate();
    		for (String bean: (List<String>) map.get("emails")) {
    			lEmails.add(email = new SocioEmail());
    			email.setPersona(persona);
    			email.setOrden(orden++);
    			email.setId(emailPK = new SocioEmailPK());
    			emailPK.setIdPersona(idPersona);
    			emailPK.setEmail(bean);
    			em.persist(email);
    		}
			em.getTransaction().commit();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No updata", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
        return findByIdPersona(idPersona);
    }
}