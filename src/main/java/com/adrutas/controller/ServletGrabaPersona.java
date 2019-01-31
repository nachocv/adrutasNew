package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.Persona;
import com.google.gson.Gson;

@WebServlet(name = "GrabaPersona", urlPatterns = {"/grabaPersona"})
public class ServletGrabaPersona extends HttpServlet {
	private static final long serialVersionUID = 7007175350357635662L;
	private static final Logger log = Logger.getLogger(ServletGrabaPersona.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(Persona.update(new Gson().fromJson(request.getParameter("persona"), Map.class))));
        out.close();
	}
}