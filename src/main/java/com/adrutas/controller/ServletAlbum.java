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

@WebServlet(name = "Album", urlPatterns = {"/album"})
public class ServletAlbum extends HttpServlet {
	private static final long serialVersionUID = 7584229251352875276L;
	private static final Logger log = Logger.getLogger(ServletAlbum.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String, Object> mResult = new HashMap<String, Object>();
    	Map<Integer, List<Map<Date,List<Map<String, Object>>>>> map =
    			new TreeMap<Integer, List<Map<Date,List<Map<String, Object>>>>>();
    	List<Map<Date,List<Map<String, Object>>>> lAnyo;
    	Map<Date,List<Map<String, Object>>> mDia;
    	List<Map<String, Object>> lDia;
    	Map<String, Object> mAlbum;
    	Date fecha;
    	String sFecha = null;
    	Persona persona;
    	mResult.put("anyos", map);
		for (Salida salida: Static.getSalbum()) {
			if ((lAnyo = map.get(salida.getAnyo()))==null) {
				map.put(salida.getAnyo(), lAnyo = new ArrayList<Map<Date,List<Map<String, Object>>>>());
			}
			lAnyo.add(mDia = new TreeMap<Date,List<Map<String, Object>>>());
			if ((lDia = mDia.get(salida.getFechaInicio()))==null) {
				mDia.put(fecha = salida.getFechaInicio(), lDia = new ArrayList<Map<String, Object>>());
				sFecha = Constante.dF13.format(fecha);
			}
	    	mResult.put("salida",salida.getSalida());
			for (Album album: salida.getAlbums()) {
				lDia.add(mAlbum = new HashMap<String, Object>());
				mAlbum.put("fecha",sFecha);
				mAlbum.put("descripcion",salida.getDescripcion());
				mAlbum.put("nombre",((persona = album.getPersona()).getNombre().trim() + " "
						+ persona.getApellido1().trim() + " " + persona.getApellido2().trim()).trim());
				mAlbum.put("url",album.getUrl());
			}
		}
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(mResult));
        out.close();
	}
}