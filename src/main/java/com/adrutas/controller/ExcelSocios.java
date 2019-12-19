package com.adrutas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.model.Ficha;
import com.adrutas.model.Persona;
import com.adrutas.model.Recibo;
import com.adrutas.model.SocioEmail;
import com.adrutas.model.SocioTelefono;

import adrutas.com.Constante;

@WebServlet(name = "ExcelSocio", urlPatterns = {"/jd/excelSocios"})
public class ExcelSocios extends HttpServlet {
	private static final long serialVersionUID = -4143733404836244863L;
	private static final Logger log = Logger.getLogger(ExcelSocios.class.getName());
    private static final String[] names = {"id_persona","usuario","nombre","apellido1",
        "apellido2","domicilio","codigo_postal","poblacion","provincia","dni","nota",
        "nacimiento","sexo","vetado","veto","pasaporte","cad_pasaporte","E-mail1","E-mail2","E-mail3","Phone1","Phone3",
        "Phone2","importelicencia","importecuota","tipo_licencia","id_recibo","fp","fecha",
        "club","anyo"};

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		EntityManager em = null;
		Ficha ficha = null;
		Recibo recibo = null;
        Date date;
        int size;
        List<SocioEmail> lEmails;
        List<SocioTelefono> lTelefonos;
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Socios");
        ServletOutputStream out = res.getOutputStream();
		try {
	        Row row = sheet.createRow(0);
	        int col = 0;
	        for (String name: names) {
	            row.createCell(col++).setCellValue(name);
	        }
			em = EntityManagerFactories.getEM();
	        int fil = 1;
	        Map<Integer,Persona> mPersona = new TreeMap<Integer,Persona>();
	    	Calendar cal = new GregorianCalendar();
	    	Integer year = cal.get(Calendar.YEAR);
	        if (cal.get(Calendar.MONTH)<2) {
	        	year = year-1;
	        }
//        	list.add(year-1);
	        for (Ficha bean: em.createNamedQuery("Ficha.findSociosByAnyos", Ficha.class)
					.setParameter("anyo", year).getResultList()) {
	        	mPersona.put(bean.getId().getIdPersona(),bean.getPersona());
	        }
	        for (Persona persona: mPersona.values()) {
	            row = sheet.createRow(fil++);
	            row.createCell(0).setCellValue(persona.getIdPersona());
                row.createCell(1).setCellValue(persona.getUsuario());
                row.createCell(2).setCellValue(persona.getNombre());
                row.createCell(3).setCellValue(persona.getApellido1());
                row.createCell(4).setCellValue(persona.getApellido2());
                row.createCell(5).setCellValue(persona.getDomicilio());
                row.createCell(6).setCellValue(persona.getCodigoPostal());
                row.createCell(7).setCellValue(persona.getPoblacion());
                row.createCell(8).setCellValue(persona.getProvincia());
                row.createCell(9).setCellValue(persona.getDni());
                row.createCell(10).setCellValue(persona.getNota());
                if ((date = persona.getNacimiento())!=null) {
                	row.createCell(11).setCellValue(date);
                }
                row.createCell(12).setCellValue(persona.getSexo());
                row.createCell(13).setCellValue(persona.getVetado());
                row.createCell(14).setCellValue(persona.getVeto());
                row.createCell(15).setCellValue(persona.getPasaporte());
                if ((date = persona.getCadPasaporte())!=null) {
                	row.createCell(16).setCellValue(date);
                }
                if (!(lEmails = persona.getSocioEmails()).isEmpty()) {
                    if ((size = lEmails.size())>0) {
                    	row.createCell(17).setCellValue(lEmails.get(0).getId().getEmail());
                        if (size>1) {
                        	row.createCell(18).setCellValue(lEmails.get(1).getId().getEmail());
                            if (size>2) {
                            	row.createCell(19).setCellValue(lEmails.get(2).getId().getEmail());
                            }
                        }
                    }
                }
                if (!(lTelefonos = persona.getSocioTelefonos()).isEmpty()) {
                    if ((size = lTelefonos.size())>0) {
                    	row.createCell(20).setCellValue(lTelefonos.get(0).getId().getTelefono());
                        if (size>1) {
                        	row.createCell(21).setCellValue(lTelefonos.get(1).getId().getTelefono());
                            if (size>2) {
                            	row.createCell(21).setCellValue(lTelefonos.get(2).getId().getTelefono());
                            }
                        }
                    }
                }
                row.createCell(23).setCellValue((ficha = persona.getFichas().get(0)).getImportelicencia().doubleValue());
                row.createCell(24).setCellValue(ficha.getImportecuota().doubleValue());
                row.createCell(25).setCellValue(ficha.getTipoLicencia());
                row.createCell(26).setCellValue((recibo = ficha.getRecibo()).getIdRecibo());
                row.createCell(27).setCellValue(recibo.getFormapago().getCodigo());
                row.createCell(28).setCellValue(recibo.getFecha());
                row.createCell(29).setCellValue(ficha.getClub());
                row.createCell(30).setCellValue(ficha.getId().getAnyo());
	        }
	        res.setContentType("application/vnd.ms-excel");
	        res.setHeader("Content-Disposition", "attachment;filename=datos_socios_" + Constante.dF1.format(cal.getTime()) + ".xls");
            wb.write(out);
		} catch (Exception e) {
			log.log(Level.SEVERE, "ExcelSocios", e);
		} finally {
			if (em!=null) {
				em.close();
			}
			wb.close();
			out.close();
		}
	}
}
