package com.adrutas.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrutas.dao.EntityManagerFactories;
import com.adrutas.dao.Static;
import com.adrutas.model.Link;
import com.adrutas.model.Persona;

import adrutas.com.Constante;
import adrutas.com.Mail;

@WebServlet(name = "RenewPassword", urlPatterns = {"/renewPassword"})
public class RenewPassword extends HttpServlet {
	private static final long serialVersionUID = 3536913819734759467L;
    private static final Logger log = Logger.getLogger(RenewPassword.class.getName());

    private static void sendPassword(Map<String, Object> map) throws UnsupportedEncodingException, MessagingException {
        Map<String, Object> mArgs = new HashMap<String, Object>();
        mArgs.put("from", new InternetAddress("web@adrutas.com", "Administrador Web de ADRutas"));
        mArgs.put("to", new InternetAddress((String) map.get("email"), (String) map.get("nombre")));
        mArgs.put("subject", "Cuenta adrutas");
        String link = map.get("server") + "changePassword.html?link=" + map.get("link");
        mArgs.put("htmlBody", "Hola,<br/><br/>Este mensaje se ha generado desde la web \"adrutas.com\" porque alguien" +
        		" ha informado de que no dispones de la contraseña de tu cuenta.<br/><br/>Reactiva tu cuenta pulsando" +
        		" este enlace.<br/><br/><a href=\"" + link + "\">" + link +
        		"</a><br/><br/>Este es un mensaje automático, no contestes a esta dirección.");
        Mail.sendHtmlMail(mArgs);
    }

    private static int getChar(int i) {

        /*
         * 0 ,  9 -> 48,  57 -> 0, 9
         * 10, 35 -> 65,  90 -> A, Z
         * 36, 61 -> 97, 122 -> a, z
         */
        int k;
        i = i % 62;
        if (i<10) {
            k = i+48;
        } else if (i<36) {
            k = i+55;
        } else {
            k = i+61;
        }
        return k;
    }

    private static String getRndStr(Random r, int longitud){
        StringBuffer cadenaAleatoria = new StringBuffer();
        for (int i=0; i<longitud; i++) {
            cadenaAleatoria.append((char) getChar(r.nextInt(62)));
        }
        return cadenaAleatoria.toString();
    }

    private static void sendPassword(String email,String nombre,String server,String link) throws UnsupportedEncodingException, MessagingException {
        Map<String, Object> mArgs = new HashMap<String, Object>();
        mArgs.put("from", new InternetAddress("adrutas.web@gmail.com", "Administrador Web de ADRutas"));
        mArgs.put("to", new InternetAddress(email,nombre));
        mArgs.put("subject", "Cuenta adrutas");
        link = server + "changePassword.html?link=" + link;
        mArgs.put("htmlBody", "Hola,<br/><br/>Este mensaje se ha generado desde la web \"adrutas.com\" porque alguien" +
        		" ha informado de que no dispones de la contraseña de tu cuenta.<br/><br/>Reactiva tu cuenta pulsando" +
        		" este enlace.<br/><br/><a href=\"" + link + "\">" + link +
        		"</a><br/><br/>Este es un mensaje automático, no contestes a esta dirección.");
        log.log(Level.SEVERE, "Enviado el link " + link + " al email " + email + " de " + nombre);
        if (Static.PRODUCTION) {
        	Mail.sendHtmlMail(mArgs);
        }
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = null;
        String sLink;
        String error = null;
        try {
        	String email = req.getParameter("email");
    		em = EntityManagerFactories.getEM();
            List<Persona> list = Persona.findByEmail(email,em,2);
            if (list.size()==0) {
                error = "El EMail \"" + email + "\" no lo tiene nadie. " +
                		"Si quiere que le sea asignado, pongase en contacto con el Club";
            } else if (list.size()!=1) {
                error = "El EMail \"" + email + "\" lo tiene más de una persona";
            } else {
                Persona persona = list.get(0);
    			em.getTransaction().begin();
        		for (Link link1: em.createNamedQuery("Link.findOld",Link.class)
        				.setParameter("fecha",new Date()).getResultList()) {
            		em.remove(link1);
        		}
        		for (Link link1: em.createNamedQuery("Link.findByPersona", Link.class).setParameter(
        				"idPersona", persona.getIdPersona()).getResultList()) {
            		em.remove(link1);
        		}
                for (sLink = getRndStr(Constante.r, 25); em.createNamedQuery("Link.count", Number.class).setParameter(
                		"link", sLink).getSingleResult().intValue()>0;sLink = getRndStr(Constante.r, 25)) {}
                Link link = new Link();
                link.setFecha(new Date());
                link.setPersona(persona);
                link.setLink(sLink);
//                persona.setLink(link);
                em.persist(link);
//                em.persist(persona);
    			em.getTransaction().commit();
                StringBuffer url = req.getRequestURL();
                String server = url.substring(0,url.length()-req.getRequestURI().length()+1);
                sendPassword(email,persona.getNombre(),server,sLink);
            }
        } catch (Exception e) {
        	error = "No recupera la cuenta " + e.getMessage();
            log.log(Level.SEVERE, "No recupera la cuenta ", e);
		} finally {
			if (em!=null) {
				em.close();
			}
		}
		if (error==null) {
	        req.getRequestDispatcher("/").forward(req,resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().print(error);
		}
	}
}
