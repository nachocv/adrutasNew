package com.adrutas.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//@WebFilter(filterName = "Filter", urlPatterns = {"*"})
public final class Filter implements javax.servlet.Filter {
	private ServletContext context;
	private static final Logger log = Logger.getLogger(Filter.class.getName());

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
		context.log("Inicializado FilterJunta");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
//		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		String uri = ((HttpServletRequest) req).getRequestURI();
		StringBuffer url = ((HttpServletRequest) req).getRequestURL();
		log.log(Level.SEVERE, "Entra en Filter.doFilter.\nuri: " + uri + "\nurl: " + url);
//    	Persona persona = (Persona) ((HttpServletRequest) req).getSession().getAttribute("yo");
//		log.log(Level.SEVERE, "Filter.doFilter. Persona==null: " + (persona==null));
//		if (uri.startsWith("/persona") || uri.startsWith("/apunte")) {
//			log.log(Level.SEVERE, "Filter.doFilter: entra en /persona o /apunte.");
//	    	if (persona==null || !Directiva.isDirectivo(persona.getIdPersona())) {
//				log.log(Level.SEVERE, "Filter.doFilter: no es directivo.");
//	    		((HttpServletResponse) resp).sendError(403);
//	    		return;
//	    	}
//		} else if (uri.startsWith("/socios/")) {
//			log.log(Level.SEVERE, "Filter.doFilter: entra en /socios");
//	    	if (persona!=null) {
//				log.log(Level.SEVERE, "Recupera el atributo yo");
//	    		EntityManager em = null;
//	        	Calendar calendar1 = Calendar.getInstance();
//	        	Calendar calendar2 = (Calendar) calendar1.clone();
//	        	calendar2.set(Calendar.MONTH,2);
//	        	calendar2.set(Calendar.DATE,1);
//	        	calendar2.set(Calendar.HOUR,0);
//	        	calendar2.set(Calendar.MINUTE,0);
//	        	calendar2.set(Calendar.SECOND,0);
//	        	calendar2.set(Calendar.MILLISECOND,0);
//	        	int anyo = calendar1.get(Calendar.YEAR);
//	    		try {
//	    			em = EntityManagerFactories.getEM();
//	    			persona.setFichas(em.createNamedQuery("Ficha.findByPersona", Ficha.class).setParameter(
//	    					"idPersona", persona.getIdPersona()).getResultList());
//	    	    	if (persona.esSocio(anyo) || (calendar1.compareTo(calendar2)<0 && persona.esSocio(anyo-1))) {
//	    	    		chain.doFilter(req, resp);
//	    	    		return;
//	    	    	}
//	    		} finally {
//	    			if (em!=null) {
//	    				em.close();
//	    			}
//	    		}
//	    	}
//    		httpResponse.sendRedirect("/zonaSocio.html");
//    		return;
//		}
		chain.doFilter(req, resp);
	}
}
