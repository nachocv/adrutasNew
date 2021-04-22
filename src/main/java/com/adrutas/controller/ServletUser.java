package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adrutas.model.Persona;
import com.adrutas.model.Salida;
import com.google.gson.Gson;

import adrutas.com.Constante;

@WebServlet(name = "ServletUser", urlPatterns = {"/getUsuario"})
public class ServletUser extends HttpServlet {
	private static final long serialVersionUID = -5310736222804009407L;
	private static final Logger log = Logger.getLogger(ServletUser.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	PrintWriter out = resp.getWriter();
        try {
        	HttpSession session = req.getSession(true);
        	log.log(Level.CONFIG, "session.id: " + session.getId());
			out.println(new Gson().toJson(Salida.find(null,Constante.dF11.parse(req.getParameter("pathname").
					substring(0, 11)),(Persona) session.getAttribute("yo"))));
		} catch (ParseException e) {
        	log.log(Level.SEVERE, "ServletUser", e);
		} finally {
	        out.close();
		}
	}
}