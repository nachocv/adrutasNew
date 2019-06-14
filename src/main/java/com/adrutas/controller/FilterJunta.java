package com.adrutas.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.Directiva;
import com.adrutas.model.Persona;

@WebFilter(filterName = "MyFilter", urlPatterns = {"/persona.html","/apunte.html"})
public final class MyFilter implements Filter {
	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
		context.log("RequestLoggingFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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
