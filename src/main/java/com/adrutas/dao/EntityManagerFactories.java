package com.adrutas.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.appengine.api.utils.SystemProperty;

public class EntityManagerFactories {
    private static EntityManagerFactory eMF;
    private static final java.util.logging.Logger log = Logger.getLogger(EntityManagerFactories.class.getName());

    static {
    	Map<String, String> properties = new HashMap<String, String>();
        if (SystemProperty.environment.value()==SystemProperty.Environment.Value.Production) {
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
//          properties.put("javax.persistence.jdbc.url", "jdbc:google:mysql://adrutas1:rutas/rutas?useServerPrepStmts=false");
            properties.put("javax.persistence.jdbc.url", "jdbc:google:mysql://adrutas-new:europe-west1:rutas-new/rutas");
            //properties.put("javax.persistence.jdbc.url", "jdbc:google:mysql://173.194.109.193:3306/rutas");
            properties.put("javax.persistence.jdbc.user", "root");
            properties.put("javax.persistence.jdbc.password", "1234");
            properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			log.log(Level.SEVERE, "BBDD, entra por Production");
        } else {
            properties.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
//          properties.put("javax.persistence.jdbc.url", "jdbc:mysql://127.0.0.1:3306/rutas?useServerPrepStmts=false");
            properties.put("javax.persistence.jdbc.url", "jdbc:mysql://127.0.0.1:3306/rutas");
            properties.put("javax.persistence.jdbc.user", "root");
            properties.put("javax.persistence.jdbc.password", "1234");
            properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			log.log(Level.SEVERE, "BBDD, no entra por Production");
        }
        try {
        	eMF = Persistence.createEntityManagerFactory("rutas", properties);
			log.log(Level.SEVERE, "Realizada la conexión a la BBDD rutas");
		} catch (Exception e) {
			log.log(Level.SEVERE, "No se conecta a la BBDD rutas", e);
		}
    }

    public static EntityManagerFactory getEMF() {
		return eMF;
	}

    public static EntityManager getEM() {
		return eMF.createEntityManager();
	}
}
