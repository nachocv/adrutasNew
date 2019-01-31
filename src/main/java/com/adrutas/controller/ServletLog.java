package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.Persona;
import com.adrutas.model.Salida;
import com.google.gson.Gson;

@WebServlet(name = "Log", urlPatterns = {"/log"})
public class ServletLog extends HttpServlet {
	private static final long serialVersionUID = -3462096228274971485L;
	private static final Logger log = Logger.getLogger(ServletLog.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	PrintWriter out = resp.getWriter();
        try {
            String filtro = req.getParameter("filtro").trim();
    		if (filtro!=null) {
                List<Persona> list = Persona.findExact(filtro);
                if (list.size()==1) {
                    Persona persona = list.get(0);
                    if (req.getParameter("password").equals(persona.getPassword())) {
                    	req.getSession().setAttribute("yo", persona);
            			out.println(new Gson().toJson(Salida.find(req.getParameter("salida"),
            					null,(Persona) req.getSession().getAttribute("yo"))));
                    }
                }
            }
		} catch (Exception e) {
        	log.log(Level.SEVERE, "ServletUser", e);
        	throw e;
		} finally {
	        out.close();
		}

	}
}