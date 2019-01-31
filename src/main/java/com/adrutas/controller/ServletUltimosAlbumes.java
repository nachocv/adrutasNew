package com.adrutas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.dao.Static;
import com.adrutas.model.Album;
import com.adrutas.model.Persona;
import com.adrutas.model.Salida;
import com.google.gson.Gson;

import adrutas.com.Constante;

@WebServlet(name = "UltimosAlbumes", urlPatterns = {"/ultimosAlbumes"})
public class ServletUltimosAlbumes extends HttpServlet {
	private static final long serialVersionUID = 1688610399016115478L;
	private static final Logger log = Logger.getLogger(ServletUltimosAlbumes.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<Date,List<Map<String, Object>>> mDia = new TreeMap<Date,List<Map<String, Object>>>();
    	List<Map<String, Object>> lDia = new ArrayList<Map<String, Object>>();
    	Map<String, Object> mAlbum;
    	Date fecha;
    	String sFecha = null;
    	Persona persona;
    	String portada;
    	String urlPortada;
    	int cont = 3;
		for (Salida salida: Static.getSalbum()) {
			mDia.put(fecha = salida.getFechaInicio(), lDia = new ArrayList<Map<String, Object>>());
			sFecha = Constante.dF13.format(fecha);
			portada = salida.getPortada();
			urlPortada = salida.getUrlPortada();
			for (Album album: salida.getAlbums()) {
				lDia.add(mAlbum = new HashMap<String, Object>());
				mAlbum.put("fecha",sFecha);
				mAlbum.put("portada",portada);
				mAlbum.put("urlPortada",urlPortada);
				mAlbum.put("descripcion",salida.getDescripcion());
				mAlbum.put("nombre",((persona = album.getPersona()).getNombre().trim() + " "
						+ persona.getApellido1().trim() + " " + persona.getApellido2().trim()).trim());
				mAlbum.put("url",album.getUrl());
			}
			if (--cont<=0) {
				break;
			}
		}
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(mDia));
        out.close();
	}
}