package com.adrutas.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.SalidaDetalle;

@WebServlet(name = "ServletDelAlta", urlPatterns = {"/delAlta"})
public class ServletDelAlta extends HttpServlet {
	private static final long serialVersionUID = -4539599305533439093L;
	private static final Logger log = Logger.getLogger(ServletDelAlta.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SalidaDetalle.del(request.getParameter("salida"),Integer.parseInt(request.getParameter("id_persona")));
	}
}