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
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.model.Salida;
import com.adrutas.model.SalidaDetalle;

import adrutas.com.Constante;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

@WebServlet(name = "ListaAlfa", urlPatterns = {"/listaAlfa"})
public class ListaAlfa extends HttpServlet {
    private static final long serialVersionUID = -5895086119656860023L;
	private static final Logger log = Logger.getLogger(ListaAlfa.class.getName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = null;
        ServletOutputStream out = resp.getOutputStream();
        InputStream in = null;
		String salida = req.getParameter("salida");
        resp.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        resp.setHeader("Content-Disposition", "attachment;filename=lista_telefonos_" + salida + ".docx");
		try {
            in = ListaAlfa.class.getResourceAsStream("lista_alfabetica.docx" );
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            IContext context = report.createContext();
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();
            Map<String,String> map;
            Short bus;
            Short asiento;
			em = EntityManagerFactories.getEM();
			Salida salidaBean = em.createNamedQuery("Salida.findBySalida", Salida.class)
					.setParameter("salida", salida).getSingleResult();
			context.put("bus", 1);
            context.put("salida", salida);
            context.put("descripcion", salidaBean.getDescripcion());
            context.put("fecha", Constante.dF4.format(salidaBean.getFechaInicio()));
            for (SalidaDetalle detalle: em.createNamedQuery("SalidaDetalle.listAlfa", SalidaDetalle.class)
					.setParameter("salida", salida).getResultList()) {
            	list.add(map = new HashMap<String,String>());
            	map.put("asiento",((bus = detalle.getBus())==null? "":
            		(String.valueOf(bus))) + ((asiento = detalle.getAsiento())==null? "": Constante.nF1.format(asiento)));
            	map.put("id_persona",Constante.nF2.format(detalle.getId().getIdPersona()));
            	map.put("apellidos",detalle.getPersona().getApellido1().trim().toUpperCase()
            			+ " " + detalle.getPersona().getApellido2().trim().toUpperCase());
            	map.put("nombre",detalle.getPersona().getNombre().trim().toUpperCase());
            	map.put("telefono",detalle.getPersona().getSocioTelefonos().isEmpty()? "":
            		detalle.getPersona().getSocioTelefonos().get(0).getId().getTelefono());
            }
            context.put("list", list);
            report.process(context, out);
		} catch (Exception e) {
			log.log(Level.SEVERE, "No lee SalidaDetalle.findCount", e);
		} finally {
			if (em!=null) {
				em.close();
			}
			in.close();
			out.close();
		}
	}
}
