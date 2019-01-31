package com.adrutas.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;

import com.adrutas.model.Salida;

import adrutas.com.Constante;

public class Index implements Serializable {
    private static final long serialVersionUID = -1687066108273034896L;
	private static final Logger log = Logger.getLogger(Index.class.getName());

	public static String getUrl(String fecha, String pathname) {
		Date date = null;
		try {
			date = Constante.dF11.parse(pathname.substring(0, 11));
		} catch (Exception e) {}
		if (date==null){
			try {
				date = Constante.dF3.parse(fecha);
			} catch (ParseException e) {}
		}
		if (date==null) {
			date = new Date();
		}
		return Salida.findByDate(date).getUrl();
    }
}
