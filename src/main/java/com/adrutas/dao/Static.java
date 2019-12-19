package com.adrutas.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;

import com.adrutas.model.Formapago;
import com.adrutas.model.Licencia;
import com.adrutas.model.LicenciaOpcione;
import com.adrutas.model.Property;
import com.adrutas.model.PropiedadesAnuale;
import com.adrutas.model.Salida;
import com.google.gson.Gson;

import adrutas.com.Constante;

public class Static extends HttpServlet {
    private static final long serialVersionUID = 5151565340468051014L;
    private static final java.util.logging.Logger log = Logger.getLogger(Static.class.getName());
    private static final Comparator<Salida> myComparator1 = new Comparator<Salida>() {
		public int compare(Salida s1, Salida s2) {
			return s2.getFechaInicio().compareTo(s1.getFechaInicio());
		}
    };
    private static final Comparator<Date> myComparator2 = new Comparator<Date>() {
        @Override public int compare(Date d1, Date d2) {
			return d2.compareTo(d1);
        }           
    };
    private static SortedMap<Date, Salida> mSalida;
    private static Map<Integer, Map<String, Map<String, Object>>> mLicencia;
    private static Map<String, Formapago> mFormaPago;
    private static Map<String, String> mFP;
    public static Map<String,Formapago> mFormaPagoAll;
    private static List<String> lFP;
    private static Map<Integer,Map<String,String>> mPropiedadesAnuales;
    private static int fichaYear = 2019;

//  Anyo, Salida
    private static SortedSet<Salida> sAlbum;
	public static boolean PRODUCTION = false;

    public static void inicio() {
        mSalida = new TreeMap<Date, Salida>(myComparator2);
        mLicencia = new HashMap<Integer, Map<String, Map<String, Object>>>();
        mFormaPago = new HashMap<String, Formapago>();
        mFP = new HashMap<String, String>();
        mFormaPagoAll = new HashMap<String,Formapago>();
        lFP = new ArrayList<String>();
        mPropiedadesAnuales = new HashMap<Integer,Map<String,String>>();
        sAlbum = new TreeSet<Salida>(myComparator1);
		EntityManager em = null;
        try {
    		em = EntityManagerFactories.getEM();
    		for (Salida salida: em.createNamedQuery("Salida.findAll", Salida.class).getResultList()) {
    			salida.putFechas();
    			if (!salida.getAlbums().isEmpty()) {
    				sAlbum.add(salida);
                }
    			if (salida.getFechaFin()!=null) {
    				mSalida.put(salida.getFechaInicio(), salida);
    			}
            }
    		Map<String, Map<String, Object>> mLicenciaAnyo;
    		Map<String, Object> mLicenciaTipo;
    		Map<String, Map<String, Object>> mOpcion;
    		Map<String, Object> mOpcionTipo;
    		for (Licencia licencia: em.createNamedQuery("Licencia.findAll", Licencia.class).getResultList()) {
    			if ((mLicenciaAnyo = mLicencia.get(licencia.getId().getAnyo()))==null) {
    				mLicencia.put(licencia.getId().getAnyo(), mLicenciaAnyo =
    						new HashMap<String, Map<String, Object>>());
    			}
    			if ((mLicenciaTipo = mLicenciaAnyo.get(licencia.getId().getTipoLicencia()))==null) {
    				mLicenciaAnyo.put(licencia.getId().getTipoLicencia(), mLicenciaTipo =
    						new HashMap<String, Object>());
    			}
    			mLicenciaTipo.put("nombre", licencia.getNombre());
    			mLicenciaTipo.put("importe", licencia.getImporte());
    			mLicenciaTipo.put("importeJoven", licencia.getImporteJoven());
    			mLicenciaTipo.put("importeMenor", licencia.getImporteMenor());
    			mLicenciaTipo.put("descripcion", licencia.getDescripcion());
    			if (licencia.getInicio()==null) {
        			mLicenciaTipo.put("inicio", "");
        			mLicenciaTipo.put("fin", "");
    			} else {
        			mLicenciaTipo.put("inicio", Constante.dF12.format(licencia.getInicio()));
        			mLicenciaTipo.put("fin", Constante.dF12.format(licencia.getFin()));
    			}
    			mLicenciaTipo.put("opciones", mOpcion = new HashMap<String, Map<String, Object>>());
    			for (LicenciaOpcione opcion: licencia.getLicenciaOpciones()) {
    				mOpcion.put(opcion.getId().getTipoOpcion(), mOpcionTipo = new HashMap<String, Object>());
    				mOpcionTipo.put("nombre", opcion.getNombre());
    				mOpcionTipo.put("importe", opcion.getImporte());
    			}
            }
    		for (Formapago formapago: em.createNamedQuery("Formapago.findFicha", Formapago.class).getResultList()) {
				mFormaPago.put(formapago.getCodigo(), formapago);
				mFP.put(formapago.getCodigo(), formapago.getDescripcion());
    		}
    		for (Formapago formapago: em.createNamedQuery("Formapago.findAll", Formapago.class).getResultList()) {
				mFormaPagoAll.put(formapago.getCodigo(), formapago);
    		}
    		fichaYear = Integer.parseInt(em.createNamedQuery("Property.find", Property.class)
    				.setParameter("key", "ficha.year").getSingleResult().getValue());
    		for (Formapago fp: em.createNamedQuery("Formapago.findAll", Formapago.class).getResultList()) {
    			lFP.add(fp.getCodigo());
    		}
    		Map<String,String> mBean;
    		for (PropiedadesAnuale bean: em.createNamedQuery("PropiedadesAnuale.findAll", PropiedadesAnuale.class).getResultList()) {
    			if((mBean = mPropiedadesAnuales.get(bean.getId().getAnyo()))==null) {
    				mPropiedadesAnuales.put(bean.getId().getAnyo(),mBean = new HashMap<String,String>());
    			}
    			mBean.put(bean.getId().getPropiedad(), bean.getValor());
    		}
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Static", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
    }

    public static SortedSet<Salida> getSalbum() {
		return sAlbum;
	}

	public static SortedMap<Date, Salida> getMsalida() {
		return mSalida;
	}

	public static Map<Integer, Map<String, Map<String, Object>>> getMlicencia() {
		return mLicencia;
	}

	public static Map<String, Map<String, Object>> getLicencia(int anyo) {
		return mLicencia.get(anyo);
	}

	public static Map<String, Formapago> getMformapago() {
		return mFormaPago;
	}

	public static Map<String, String> getMfp() {
		return mFP;
	}

	public static List<String> getLfp() {
		return lFP;
	}

	public static int getFichaYear() {
		return fichaYear;
	}

    public static Date getDate(String fecha) {
        fecha = fecha.trim();
        for (Entry<Pattern, List<DateFormat>> entry: Constante.FORMATOS_FECHAS.entrySet()) {
            if (entry.getKey().matcher(fecha).matches()) {
                for (DateFormat dF: entry.getValue()) {
                    try {
                        return dF.parse(fecha);
                    } catch (ParseException e) {}
                }
            }
        }
        return null;
    }

    public static Map<String, Object> normalizaDni(String dni) {
        char letra;
        String inicio;
        int number;
        dni = dni.trim().toUpperCase();
        Map<String, Object> map = new HashMap<String, Object>();
        if (Constante.PATTERN_NIF2.matcher(dni).matches()) {
            number = Integer.parseInt(dni.substring(0, dni.length()-1));
            if ((letra = dni.charAt(dni.length()-1))==Constante.LETRAS_NIE.charAt(number%23)) {
                map.put("tipoIdentificacion", 1);
            } else {
                map.put("tipoIdentificacion", 4);
            }
            map.put("dni", Constante.nF3.format(number) + letra);
        } else if (Constante.PATTERN_NIF_SIN.matcher(dni).matches()) {
            map.put("tipoIdentificacion", 5);
            number = Integer.parseInt(dni);
            map.put("dni", Constante.nF3.format(number));
        } else if (Constante.PATTERN_NIE3.matcher(dni).matches()) {
            inicio = dni.substring(0, 1);
            number = Integer.parseInt(Constante.INICIO_NIE.indexOf(inicio) + dni.substring(1, dni.length()-1));
            if ((letra = dni.charAt(dni.length()-1))==Constante.LETRAS_NIE.charAt(number%23)) {
                map.put("tipoIdentificacion", 2);
            } else {
                map.put("tipoIdentificacion", 6);
            }
            map.put("dni", dni);
        } else if (Constante.PATTERN_NIE_SIN.matcher(dni).matches()) {
            inicio = dni.substring(0, 1);
            map.put("tipoIdentificacion", 7);
            number = Integer.parseInt(dni.substring(1));
            map.put("dni", inicio + Constante.nF3.format(number).substring(1));
        } else {
            map.put("tipoIdentificacion", 3);
            map.put("dni", dni);
        }
        return map;
    }

	public static String getPropiedadesanuales(Integer anyo,String propiedad) {
		Map<String,String> map = mPropiedadesAnuales.get(anyo);
		if (map!=null) {
			return map.get(propiedad);
		}
		return null;
	}
}
