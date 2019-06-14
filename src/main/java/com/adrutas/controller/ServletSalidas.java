package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.SalidaDetalle;
import com.google.gson.Gson;

@WebServlet(name = "ServletSalidas", urlPatterns = {"/getSalidas"})
public class ServletSalidas extends HttpServlet {
	private static final long serialVersionUID = -5560470542792929649L;
	private static final Logger log = Logger.getLogger(ServletSalidas.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setCharacterEncoding("UTF-8");
    	PrintWriter out = resp.getWriter();
    	Gson gson = new Gson();
        try {
			out.println(gson.toJson(SalidaDetalle.find(gson.fromJson(req.getParameter("salidas"), Map.class))));
		} catch (Exception e) {
        	log.log(Level.SEVERE, "ServletSalidas", e);
		} finally {
	        out.close();
		}
	}
}