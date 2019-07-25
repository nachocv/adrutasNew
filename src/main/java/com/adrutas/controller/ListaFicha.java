package com.adrutas.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.dao.Static;
import com.adrutas.model.Ficha;
import com.adrutas.model.FichaOpcion;
import com.adrutas.model.Persona;
import com.adrutas.model.SocioEmail;
import com.adrutas.model.SocioTelefono;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

@WebServlet(name = "ListaFicha", urlPatterns = {"/jd/lista_ficha"})
public class ListaFicha extends HttpServlet {
	private static final long serialVersionUID = 614337683527218214L;
	private static final Logger log = Logger.getLogger(ListaFicha.class.getName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        InputStream in = null;
        int idPersona = Integer.parseInt(req.getParameter("id_persona"));
        int anyo = Integer.parseInt(req.getParameter("anyo"));
        short idFicha = 0;
        resp.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        resp.setHeader("Content-Disposition", "attachment;filename=ficha_" + anyo + "_" + idPersona + ".docx");
        try {
            Ficha ficha = Ficha.find(idPersona,anyo,idFicha);
        	Persona persona = ficha.getPersona();
            in = ListaFicha.class.getResourceAsStream("lista_ficha_2017.docx" );
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            IContext context = report.createContext();
            context.put("id_persona",ficha.getId().getIdPersona());
            context.put("nombre",persona.getNombre());
            context.put("apellidos",(persona.getApellido1() + " " + persona.getApellido2()).trim());
            context.put("dni",persona.getDni());
            context.put("nacimiento",persona.getNacimiento());
            context.put("domicilio",persona.getDomicilio());
            context.put("sexo",persona.getSexo());
            context.put("poblacion",persona.getPoblacion());
            context.put("provincia",persona.getProvincia());
            context.put("codigo_postal",persona.getCodigoPostal());
            StringBuffer sB = new StringBuffer();
            for (SocioTelefono bean:persona.getSocioTelefonos()) {
                sB.append(bean.getId().getTelefono() + ", ");
            }
            context.put("telefonos",sB.length()==0? "": sB.substring(0, sB.length()-2));
            sB = new StringBuffer();
            for (SocioEmail bean:persona.getSocioEmails()) {
            	sB.append(bean.getId().getEmail() + ", ");
            }
            context.put("emails",sB.length()==0? "": sB.substring(0, sB.length()-2));
            context.put("correo",persona.getCorreo());
            context.put("pz_castilla",persona.getPzCastilla());
            context.put("BTTc", '\u2610');
            context.put("EAc", '\u2610');
            context.put("EFc", '\u2610');
            context.put("SNWc", '\u2610');
            if ("".equals(ficha.getTipoLicencia())) {
                context.put("licencia","");
            } else {
                context.put("licencia",Static.getLicencia(anyo).get(ficha.getTipoLicencia()).get("nombre"));
                for (FichaOpcion opcion: ficha.getOpciones()) {
                    context.put(opcion.getId().getTipoOpcion() + "c", '\u2611');
                }
            }
            context.put("ingreso",ficha.getFp());
            context.put("importecuota", ficha.getImportecuota());
            context.put("importeseguro", ficha.getImportelicencia());
            context.put("importe", ficha.getImportelicencia().add(ficha.getImportecuota()));
            report.process(context, out);
        } catch (Exception e) {
            log.log(Level.SEVERE, "No genera la ficha", e);
        } finally {
            in.close();
            out.close();
        }
	}
}
