package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.Salida;
import com.google.gson.Gson;

@WebServlet(name = "ServletSalidaDetalle", urlPatterns = {"/jd/salidaDetalle"})
public class ServletSalidaDetalle extends HttpServlet {
	private static final long serialVersionUID = 1668589115685844127L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(Salida.findApunte(request.getParameter("salida"))));
        out.close();
	}
}