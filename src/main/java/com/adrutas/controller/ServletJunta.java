package com.adrutas.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.model.Directiva;
import com.adrutas.model.Persona;

//@WebServlet(name = "ServletJunta", urlPatterns = {"/persona.html","/apunte.html"})
public final class ServletJunta extends HttpServlet {
	private static final long serialVersionUID = -5634101072538352882L;
	private static final int BUFSIZE = 4096;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Persona persona = (Persona) ((HttpServletRequest) req).getSession().getAttribute("yo");
    	if (persona==null || !Directiva.isDirectivo(persona.getIdPersona())) {
    		resp.sendError(403);
    	} else {
			ServletOutputStream out = null;
			DataInputStream in = null;
    		try {
                File file = new File(getServletConfig().getServletContext().getRealPath(req.getRequestURI()));
                int length   = 0;
                out = resp.getOutputStream();
                byte[] byteBuffer = new byte[BUFSIZE];
                in = new DataInputStream(new FileInputStream(file));
                while (in!=null && (length=in.read(byteBuffer))!=-1) {
                    out.write(byteBuffer,0,length);
                }
			} finally {
				if (in!=null) {
					in.close();
				}
				if (out!=null) {
					out.flush();
					out.close();
				}
			}
    	}
	}
}
