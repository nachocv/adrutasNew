package com.adrutas.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.dao.EntityManagerFactories;

@WebServlet(name = "SendLink", urlPatterns = {"/sendLink"})
public class SendLink extends HttpServlet {
	private static final long serialVersionUID = 6841849689784151883L;
	private static final Logger log = Logger.getLogger(SendLink.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = null;
        try {
        	String link = req.getParameter("link");
        	em = EntityManagerFactories.getEM();
    		req.setAttribute("link", link);
//    		resp.sendRedirect(em.createNamedQuery("Link.count",Number.class).setParameter("link", link)
//    				.getSingleResult().intValue()==1? "/changePassword.html": "/linkErroneo.html");
    		String url = em.createNamedQuery("Link.count",Number.class).setParameter("link", link)
    				.getSingleResult().intValue()==1? "/changePassword.html": "/linkErroneo.html";
    		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(url);
    		dispatcher.forward(req, resp);
    	} catch (Exception e) {
            log.log(Level.SEVERE, "No recupera el link", e);
    		resp.sendRedirect("/linkErroneo.html");
		} finally {
			if (em!=null) {
				em.close();
			}
		}
	}
}
