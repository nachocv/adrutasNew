package com.adrutas.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.model.Ficha;
import com.adrutas.model.Persona;

//@WebServlet(name = "ServletSocio", urlPatterns = {"/socios/*"})
public final class ServletSocio extends HttpServlet {
	private static final long serialVersionUID = 3598377159851252896L;
	private static final Logger log = Logger.getLogger(ServletSocio.class.getName());
	private static final int BUFSIZE = 4096;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Persona persona = (Persona) ((HttpServletRequest) req).getSession().getAttribute("yo");
    	if (persona!=null) {
			log.log(Level.SEVERE, "Recupera el atributo yo");
    		EntityManager em = null;
        	Calendar calendar1 = Calendar.getInstance();
        	Calendar calendar2 = (Calendar) calendar1.clone();
        	calendar2.set(Calendar.MONTH,2);
        	calendar2.set(Calendar.DATE,1);
        	calendar2.set(Calendar.HOUR,0);
        	calendar2.set(Calendar.MINUTE,0);
        	calendar2.set(Calendar.SECOND,0);
        	calendar2.set(Calendar.MILLISECOND,0);
        	int anyo = calendar1.get(Calendar.YEAR);
    		try {
    			em = EntityManagerFactories.getEM();
    			persona.setFichas(em.createNamedQuery("Ficha.findByPersona", Ficha.class).setParameter(
    					"idPersona", persona.getIdPersona()).getResultList());
    	    	if (persona.esSocio(anyo) || (calendar1.compareTo(calendar2)<0 && persona.esSocio(anyo-1))) {
    				ServletOutputStream out = null;
    				DataInputStream in = null;
    	    		try {
    	                File file = new File(getServletConfig().getServletContext().getRealPath(req.getRequestURI()));
    	                int length   = 0;
    	                out = resp.getOutputStream();
    	                byte[] byteBuffer = new byte[BUFSIZE];
    	                in = new DataInputStream(new FileInputStream(file));
    	                while (in!=null && (length=in.read(byteBuffer))!=-1) {
    	                    out.write(byteBuffer,0,length);
    	                }
    	                return;
    				} finally {
    					if (in!=null) {
    						in.close();
    					}
    					if (out!=null) {
    						out.flush();
    						out.close();
    					}
    				}
    	    	}
    		} finally {
    			if (em!=null) {
    				em.close();
    			}
    		}
    	}
		resp.sendRedirect("/zonaSocio.html");
	}
}
