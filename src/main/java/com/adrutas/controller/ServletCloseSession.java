package com.adrutas.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "closeSession", urlPatterns = {"/close_session"})
public class ServletCloseSession extends HttpServlet {
	private static final long serialVersionUID = -6586571123923910651L;
	private static final Logger log = Logger.getLogger(ServletCloseSession.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("yo");
		request.getSession().invalidate();
		log.log(Level.SEVERE, "Eliminado el atributo yo");
	}
}