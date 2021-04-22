package com.adrutas.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.model.Ficha;
import com.adrutas.model.Persona;

//No funciona en app engine la anotación @WebFilter, lo pongo en web.xml
//@WebFilter(filterName = "FilterSocio", urlPatterns = {"/socios/*"})
public final class FilterSocio implements Filter {
	private ServletContext context;
	private static final Logger log = Logger.getLogger(FilterSocio.class.getName());

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
		context.log("Inicializado FilterSocio");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
    	HttpSession session = ((HttpServletRequest) req).getSession(true);
    	log.log(Level.SEVERE, "session.id: " + session.getId());
    	Persona persona = (Persona) session.getAttribute("yo");
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
    	    		chain.doFilter(req, resp);
    	    		log.log(Level.SEVERE, "Se continua el filtro");
    	    		return;
    	    	}
    		} finally {
    			if (em!=null) {
    				em.close();
    			}
    		}
    	}
		log.log(Level.SEVERE, "Se envía zonaSocio.html");
		((HttpServletResponse) resp).sendRedirect("/zonaSocio.html");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
