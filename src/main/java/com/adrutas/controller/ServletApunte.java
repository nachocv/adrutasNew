package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.Persona;
import com.google.gson.Gson;

@WebServlet(name = "ServletApunte", urlPatterns = {"/apunte"})
public class ServletApunte extends HttpServlet {
	private static final long serialVersionUID = 1406827050408366399L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	Map<String, Object> mSalida = new HashMap<String, Object>();
    	List<Map<String, Object>> lSalida = new ArrayList<Map<String, Object>>();
    	mSalida.put("data", lSalida);
    	Map<String, Object> mPersona;
//    	mSalida.put("salidas",Salida.findAll());
    	for (Persona persona: Persona.findAll()) {
    		lSalida.add(mPersona = new HashMap<String, Object>());
    		mPersona.put("nombre", persona.getNombre());
    		mPersona.put("apellido1", persona.getApellido1());
    		mPersona.put("apellido2", persona.getApellido2());
    		mPersona.put("dni", persona.getDni());
    		mPersona.put("poblacion", persona.getPoblacion());
    		mPersona.put("domicilio", persona.getDomicilio());
    	}
        out.println(new Gson().toJson(mSalida)
    	        .replace("Á", "&Aacute;")
    	        .replace("É", "&Eacute;")
    	        .replace("Í", "&Iacute;")
    	        .replace("Ó", "&Oacute;")
    	        .replace("Ú", "&Uacute;")
    	        .replace("á", "&aacute;")
    	        .replace("é", "&eacute;")
    	        .replace("í", "&iacute;")
    	        .replace("ó", "&oacute;")
    	        .replace("ú", "&uacute;")
    	        .replace("º", "&ordm;")
    	        .replace("ª", "&ordf;")
    	        .replace("Ñ", "&Ntilde;")
    	        .replace("ñ", "&ntilde;")
    	        );
        out.close();
	}
}