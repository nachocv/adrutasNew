package com.adrutas.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "closeSession", urlPatterns = {"/close_session"})
public class ServletCloseSession extends HttpServlet {
	private static final long serialVersionUID = -6586571123923910651L;
	private static final Logger log = Logger.getLogger(ServletCloseSession.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = req.getSession(true);
    	log.log(Level.SEVERE, "session.id: " + session.getId());
    	session.removeAttribute("yo");
    	session.invalidate();
		log.log(Level.SEVERE, "Eliminado el atributo yo");
	}
}