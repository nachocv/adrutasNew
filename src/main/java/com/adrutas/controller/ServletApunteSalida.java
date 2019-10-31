package com.adrutas.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.SalidaDetalle;

@WebServlet(name = "ServletApunteSalida", urlPatterns = {"/apunteSalida"})
public class ServletApunteSalida extends HttpServlet {
	private static final long serialVersionUID = 6622980861074142711L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = SalidaDetalle.insert(request.getParameter("salida"),Integer.parseInt(request.getParameter("id_persona")));
		if (error!=null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().print(error);
		}
	}
}