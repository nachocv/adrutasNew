package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.Persona;
import com.google.gson.Gson;

@WebServlet(name = "GetParticipacion", urlPatterns = {"/getParticipacion"})
public class Participacion extends HttpServlet {
	private static final long serialVersionUID = -3650365287670454130L;
	private static final Logger log = Logger.getLogger(Participacion.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Persona persona = (Persona) req.getSession().getAttribute("yo");
    	resp.setCharacterEncoding("UTF-8");
    	PrintWriter out = resp.getWriter();
        out.println(new Gson().toJson(Persona.findByIdPersona2(persona.getIdPersona())));
        out.close();
	}
}