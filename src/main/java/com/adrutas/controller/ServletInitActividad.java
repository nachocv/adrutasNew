package com.adrutas.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adrutas.com.Constante;

@WebServlet(name = "ServletInitActividad", urlPatterns = {"/initActividad"}, loadOnStartup = 1)
public class ServletInitActividad extends HttpServlet {
	private static final long serialVersionUID = 7086896540075413184L;
	private static final Logger log = Logger.getLogger(ServletInitActividad.class.getName());

	public void inicio() throws ServletException {
		Init.init();
		Constante.init();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		inicio();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicio();
	}
}