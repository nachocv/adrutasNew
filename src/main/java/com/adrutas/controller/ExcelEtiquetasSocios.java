package com.adrutas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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

import adrutas.com.Constante;

@WebServlet(name = "ExcelEtiquetasSocio", urlPatterns = {"/jd/excelEtiquetasSocios"})
public class ExcelEtiquetasSocios extends HttpServlet {
	private static final long serialVersionUID = -868504714997529210L;
	private static final Logger log = Logger.getLogger(ExcelSocios.class.getName());

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		EntityManager em = null;
        String parameter1;
        String parameter2;
        Persona persona;
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("EtiquetasSocios");
        ServletOutputStream out = res.getOutputStream();
		try {
	        Row row = sheet.createRow(0);
	        row.createCell(0).setCellValue("linea1");
	        row.createCell(1).setCellValue("linea2");
	        row.createCell(2).setCellValue("linea3");
	        row.createCell(3).setCellValue("id_persona");
			em = EntityManagerFactories.getEM();
	        int fil = 1;
	        Set<Ficha> set = new TreeSet<>(new Comparator<Ficha>() {
				public int compare(Ficha o1, Ficha o2) {
			        String parameter1;
			        String parameter2;
			        int compareTo;
					Persona persona1 =o1.getPersona();
					Persona persona2 =o2.getPersona();
					parameter1 = (parameter1 = persona1.getProvincia())==null? "": parameter1;
					parameter2 = (parameter2 = persona2.getProvincia())==null? "": parameter2;
					if ((compareTo = parameter1.compareTo(parameter2))!=0) {
						return compareTo;
					}
					parameter1 = (parameter1 = persona1.getPoblacion())==null? "": parameter1;
					parameter2 = (parameter2 = persona2.getPoblacion())==null? "": parameter2;
					if ((compareTo = parameter1.compareTo(parameter2))!=0) {
						return compareTo;
					}
					parameter1 = (parameter1 = persona1.getCodigoPostal())==null? "": parameter1;
					parameter2 = (parameter2 = persona2.getCodigoPostal())==null? "": parameter2;
					if ((compareTo = parameter1.compareTo(parameter2))!=0) {
						return compareTo;
					}
					return persona1.getIdPersona()-persona2.getIdPersona();
				}
			});
	    	Calendar cal = new GregorianCalendar();
	    	Integer year = cal.get(Calendar.YEAR);
	        if (cal.get(Calendar.MONTH)<2) {
	        	year = year-1;
	        }
	        set.addAll(em.createNamedQuery("Ficha.findSociosByAnyos", Ficha.class)
					.setParameter("anyo", year).getResultList());
	        for (Ficha ficha: set) {
	        	persona = ficha.getPersona();
	        	(row = sheet.createRow(fil++)).createCell(0).setCellValue(persona.getNombre().trim() + ((parameter1 =
	        			persona.getApellido1())==null? "": (" " + parameter1.trim())) + ((parameter1 =
	        			persona.getApellido2())==null? "": (" " + parameter1.trim())));
	        	row.createCell(1).setCellValue(persona.getDomicilio());
	        	row.createCell(2).setCellValue(((parameter1 = persona.getCodigoPostal())==null? "":
	        		(parameter1.trim() + "-")) + ((parameter1 = (parameter1 = persona.getPoblacion())==null? "":
	        			parameter1.trim()).equalsIgnoreCase(parameter2 = (parameter2 = persona.getProvincia())==null? "":
	        				parameter2.trim())? parameter1: (parameter1 + " (" + parameter2) + ")"));
	        	row.createCell(3).setCellValue(persona.getIdPersona());
	        }
	        res.setContentType("application/vnd.ms-excel");
	        res.setHeader("Content-Disposition", "attachment;filename=etiquetas_socios_" + Constante.dF1.format(cal.getTime()) + ".xls");
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
