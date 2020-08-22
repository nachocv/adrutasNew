package com.adrutas.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.model.PersonaMensaje;
import com.adrutas.model.Salida;
import com.adrutas.model.SalidaDetalle;

import adrutas.com.Constante;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

@WebServlet(name = "ListaAsientos", urlPatterns = {"/jd/listaAsientos"})
public class ListaAsientos extends HttpServlet {
	private static final long serialVersionUID = -1721119637702558047L;
	private static final Logger log = Logger.getLogger(ListaAsientos.class.getName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = null;
        ServletOutputStream out = resp.getOutputStream();
        InputStream in = null;
		String salida = req.getParameter("salida");
        StringBuffer cobro;
        String estado;
        Boolean seguro_dia;
    	List<PersonaMensaje> lMensajes;
    	String mensaje;
    	String observacion;
        resp.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        resp.setHeader("Content-Disposition", "attachment;filename=lista_asientos_" + salida + ".docx");
		try {
            in = ListaAsientos.class.getResourceAsStream("lista_asientos.docx" );
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            IContext context = report.createContext();
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();
            Map<String,String> map;
            Short asiento;
			em = EntityManagerFactories.getEM();
			Salida salidaBean = em.createNamedQuery("Salida.findBySalida", Salida.class)
					.setParameter("salida", salida).getSingleResult();
			TypedQuery<PersonaMensaje> queryMensaje = em.createNamedQuery(
					"PersonaMensaje.find",PersonaMensaje.class).setParameter("salida", salida);
			context.put("bus", 1);
            context.put("salida", salida);
            context.put("descripcion", salidaBean.getDescripcion());
            context.put("fecha", Constante.dF4.format(salidaBean.getFechaInicio()));
            for (SalidaDetalle detalle: em.createNamedQuery("SalidaDetalle.listAsientos", SalidaDetalle.class)
					.setParameter("salida", salida).getResultList()) {
            	list.add(map = new HashMap<String,String>());
            	map.put("asiento",(asiento = detalle.getAsiento())==null? "": String.valueOf(asiento));
                cobro = new StringBuffer();
                seguro_dia = detalle.getSeguroDia()==1;
                if ("ME".equals(estado = detalle.getRecibo().getFormapago().getCodigo())) {
                    if (seguro_dia) {
                        cobro.append("SD ");
                    }
                    cobro.append(detalle.getImporte());
                } else {
                    cobro.append(estado);
                    if (seguro_dia) {
                        cobro.append(" ").append(detalle.getImporte());
                    }
                }
                map.put("cobro", cobro.toString());
                map.put("bono", detalle.getBonoDetalle()==null? "":
                	(String.valueOf(detalle.getBonoDetalle().getId().getBono()) + "-"
                	+ String.valueOf(detalle.getBonoDetalle().getId().getUso())));
            	map.put("socio",Constante.nF2.format(detalle.getId().getIdPersona()));
            	map.put("nombre",detalle.getPersona().getApellido1().trim().toUpperCase()
            			+ " " + detalle.getPersona().getApellido2().trim().toUpperCase() + ", "
            			+ detalle.getPersona().getNombre().trim().toUpperCase());
            	observacion = (observacion = detalle.getObservacion())==null? "": observacion.trim();
	        	lMensajes = queryMensaje.setParameter("idPersona",
	        			detalle.getId().getIdPersona()).setMaxResults(1).getResultList();
	        	mensaje = (mensaje = lMensajes.isEmpty()? "": lMensajes.get(0).getMensaje())==null? "": mensaje.trim();
            	map.put("observacion",mensaje.isEmpty()? observacion: observacion.isEmpty()?
            			mensaje: (observacion + ". " + mensaje));
            }
            context.put("list", list);
            report.process(context, out);
		} catch (Exception e) {
			log.log(Level.SEVERE, "ListaAsientos", e);
		} finally {
			if (em!=null) {
				em.close();
			}
			in.close();
			out.close();
		}
	}
}
