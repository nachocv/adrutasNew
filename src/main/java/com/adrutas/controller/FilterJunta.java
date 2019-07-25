package com.adrutas.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.Directiva;
import com.adrutas.model.Persona;

//No funciona en app engine la anotación @WebFilter, lo pongo en web.xml
//@WebFilter(filterName = "FilterJunta", urlPatterns = {"/jd/*"})
public final class FilterJunta implements Filter {
	private ServletContext context;
	private static final Logger log = Logger.getLogger(FilterJunta.class.getName());

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
		context.log("Inicializado FilterJunta");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		String uri = ((HttpServletRequest) req).getRequestURI();
		StringBuffer url = ((HttpServletRequest) req).getRequestURL();
		log.log(Level.SEVERE, "uri: " + uri + ". url: " + url);
    	Persona persona = (Persona) ((HttpServletRequest) req).getSession().getAttribute("yo");
    	log.log(Level.SEVERE, "persona==null: " + (persona==null));
    	if (persona==null || !Directiva.isDirectivo(persona.getIdPersona())) {
    		log.log(Level.SEVERE, "Se envía error 403");
    		((HttpServletResponse) resp).sendError(403);
    	} else {
    		log.log(Level.SEVERE, "Se continua el filtro");
    		chain.doFilter(req, resp);
    	}
	}

	public void destroy() {
		//we can close resources here
	}

}
