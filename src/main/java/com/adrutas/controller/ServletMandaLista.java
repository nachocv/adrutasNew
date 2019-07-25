package com.adrutas.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.SalidaDetalle;
import com.google.gson.Gson;

@WebServlet(name = "ServletMandaLista", urlPatterns = {"/jd/mandaLista"})
public class ServletMandaLista extends HttpServlet {
	private static final long serialVersionUID = 906159967871146790L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"))) {	
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		}
		SalidaDetalle.update(new Gson().fromJson(stringBuilder.toString(), Map.class));
	}
}