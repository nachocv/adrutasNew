package com.adrutas.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
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

import adrutas.com.Constante;

@WebServlet(name = "Prueba", urlPatterns = {"/prueba"})
public class ServletPrueba extends HttpServlet {
	private static final long serialVersionUID = -3462096228274971485L;
	private static final Logger log = Logger.getLogger(ServletInit.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURL().toString();
		String uri = req.getRequestURI().toString();
		log.fine("URL: " + url + " URI: " + uri);
    	ServletOutputStream out = response.getOutputStream();
    	String fecha = request.getParameter("fecha");
    	String pathname = request.getParameter("pathname");
		Date date = null;
		try {
			date = Constante.dF11.parse(pathname.substring(0, 11));
		} catch (Exception e) {}
		if (date==null){
			try {
				date = Constante.dF3.parse(fecha);
			} catch (ParseException e) {}
		}
		if (date==null) {
			date = new Date();
		}
		EntityManager em = null;
		List<Salida> list = null;
        try {
    		em = EntityManagerFactories.getEM_old();
    		list = em.createNamedQuery("Salida.findByDate", Salida.class)
    				.setParameter("date", date).setMaxResults(1).getResultList();
        } catch (Exception e) {
        	log.log(Level.SEVERE, "No lee Salida.findBySalida", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
    	if (list.isEmpty()) {
			log.log(Level.SEVERE, "Se hardcodea la url");
            out.println("/2018/1028/index.html");
    	} else {
			log.log(Level.SEVERE, "Se obtiene la url de la BBDD");
    		out.println(list.get(0).getUrl());
    	}
    	out.close();
	}
}