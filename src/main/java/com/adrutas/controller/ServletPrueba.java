package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name = "Prueba", urlPatterns = {"/prueba"})
public class ServletPrueba extends HttpServlet {
	private static final long serialVersionUID = 1307305602748456356L;
	private static final Logger log = Logger.getLogger(ServletPrueba.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String, Object> map = new HashMap<String, Object>();
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
//        out.println(new Gson().toJson(map));
//        out.println("{\"dato\":\"ÁÉÍÓÚáéíóúÑñºª\"}");
    	map.put("dato","ÁÉÍÓÚáéíóúÑñºª 3");
        out.println(new Gson().toJson(map));
        out.close();
	}
}