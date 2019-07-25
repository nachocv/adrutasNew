package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.Persona;
import com.google.gson.Gson;

@WebServlet(name = "FiltroPersona", urlPatterns = {"/jd/filtroPersona"})
public class ServletFiltroPersona extends HttpServlet {
	private static final long serialVersionUID = -7663814435460409407L;
	private static final Logger log = Logger.getLogger(ServletFiltroPersona.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String, Object> map = null;
		String filtro = request.getParameter("filtro").trim();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (filtro!=null) {
            for (Persona persona: Persona.filter(filtro)) {
            	list.add(map = new HashMap<String, Object>());
            	map.put("id_persona", persona.getIdPersona());
            	map.put("nomb", (persona.getNombre()+" "+persona.getApellido1()+" "+persona.getApellido2()).trim());
            }
        }
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(list));
        out.close();
	}
}