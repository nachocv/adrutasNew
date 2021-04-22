package com.adrutas.controller;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.model.Link;
import com.adrutas.model.Persona;

@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 6841849689784151883L;
	private static final Logger log = Logger.getLogger(ChangePassword.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = null;
    	String password_1 = req.getParameter("password_1");
    	String password_2 = req.getParameter("password_2");
        if (password_1==null || !password_1.equals(password_2)) {
        	error = "No coinciden las contraseñas";
        } else {
    		EntityManager em = null;
        	String sLink = req.getParameter("link");
            try {
        		(em = EntityManagerFactories.getEM()).getTransaction().begin();
        		Link link = em.createNamedQuery("Link.find", Link.class).setParameter("link", sLink).getSingleResult();
        		Persona persona = link.getPersona();
                em.remove(link);
            	persona.setPassword(password_1);
            	em.persist(persona);
        		for (Link link1: em.createNamedQuery("Link.findOld",Link.class)
        				.setParameter("fecha",new Date()).getResultList()) {
            		em.remove(link1);
        		}
    			em.getTransaction().commit();
            	HttpSession session = req.getSession(true);
            	log.log(Level.SEVERE, "session.id: " + session.getId());
            	session.setAttribute("yo", persona);
            } catch (Exception e) {
            	error = "No recupera el link " + sLink + ": " + e.getMessage();
                log.log(Level.SEVERE, "No recupera la cuenta ", e);
    		} finally {
    			if (em!=null) {
    				em.close();
    			}
    		}
        }
		if (error==null) {
    		resp.sendRedirect("/");
		} else {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().print(error);
		}
	}
}
