package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.adrutas.model.Salida;
import com.google.gson.Gson;

@WebServlet(name = "Persona", urlPatterns = {"/persona"})
public class ServletPersona extends HttpServlet {
	private static final long serialVersionUID = -1572034853508489983L;
	private static final Logger log = Logger.getLogger(ServletPersona.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String, Object> map = new HashMap<String, Object>();
		String filtro = request.getParameter("filtro");
		if (filtro!=null) {
			filtro = filtro.trim();
			String password = request.getParameter("password");
			if (filtro!=null) {
	            List<Persona> list = Persona.findExact(filtro);
	            if (list.size()==1) {
	                Persona persona = list.get(0);
	                if (password.equals(persona.getPassword())) {
	                	request.getSession().setAttribute("yo", persona);
	                	String salida = request.getParameter("salida");
	                	map.put("salida", salida);
	                	map.put("id_persona", persona.getIdPersona());
	                	map.put("usuario", persona.getUsuario());
//	                	map.putAll(Salida.permitirApunte(salida,persona));
	                }
	            }
	        }
		}
    	PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(map));
        out.close();
	}
}