package com.adrutas.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletInit", urlPatterns = {"/getInit"})
public class ServletInit extends HttpServlet {
	private static final long serialVersionUID = -3462096228274971485L;
	private static final Logger log = Logger.getLogger(ServletInit.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURL().toString();
		String uri = req.getRequestURI().toString();
		log.fine("URL: " + url + " URI: " + uri);
    	ServletOutputStream out = response.getOutputStream();
    	url = Index.getUrl(request.getParameter("fecha"), request.getParameter("pathname"));
    	if (url==null) {
			log.log(Level.SEVERE, "Se hardcodea la url");
            out.println("/2018/1028/index.html");
    	} else {
			log.log(Level.SEVERE, "Se obtiene la url de la BBDD");
    		out.println(url);
    	}
    	out.close();
//        request.getRequestDispatcher(index.getSalida().getUrl()).forward(request, response);
//        request.getRequestDispatcher("/2018/0916/index.html").forward(request, response);
//        response.sendRedirect(index.getSalida().getUrl());
//    	response.sendRedirect("http://localhost/2018/1028/index.html");
//    	RequestDispatcher dispatcher = request.getRequestDispatcher("http://localhost/2018/1028/index.html");
//    	dispatcher.forward(request, response);
	}
}