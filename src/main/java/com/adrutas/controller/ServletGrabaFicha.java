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

import com.adrutas.model.Ficha;
import com.adrutas.model.Persona;
import com.google.gson.Gson;

@WebServlet(name = "GrabaFicha", urlPatterns = {"/grabaFicha"})
public class ServletGrabaFicha extends HttpServlet {
	private static final long serialVersionUID = 7193464087103016170L;
	private static final Logger log = Logger.getLogger(ServletGrabaFicha.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Ficha ficha = Ficha.update(new Gson().fromJson(request.getParameter("ficha"), Map.class));
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(Persona.findByIdPersona(ficha.getId().getIdPersona())));
        out.close();
	}
}