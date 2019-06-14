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
//@WebFilter(filterName = "FilterJunta", urlPatterns = {"/persona.html","/apunte.html"})
public final class FilterJunta implements Filter {
	private ServletContext context;
	private static final Logger log = Logger.getLogger(FilterJunta.class.getName());

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
		context.log("Inicializado FilterJunta");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.log(Level.SEVERE, "Entra en FilterJunta.doFilter");
    	Persona persona = (Persona) ((HttpServletRequest) request).getSession().getAttribute("yo");
    	if (persona==null || !Directiva.isDirectivo(persona.getIdPersona())) {
    		((HttpServletResponse) response).sendError(403);
    	} else {
    		chain.doFilter(request, response);
    	}
	}

	public void destroy() {
		//we can close resources here
	}

}
