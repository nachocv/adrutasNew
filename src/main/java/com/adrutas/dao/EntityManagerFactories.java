package com.adrutas.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.getstarted.daos.BookDao;
import com.example.getstarted.daos.DatastoreDao;
import com.google.appengine.api.utils.SystemProperty;

public class EntityManagerFactories {
	private static EntityManagerFactory eMF;
	private static EntityManagerFactory eMF_old;
	private static final Logger log = Logger.getLogger(EntityManagerFactories.class.getName());
	private static BookDao dao = null;

	static {
		Map<String, String> properties = new HashMap<String, String>();
		Map<String, String> properties2 = new HashMap<String, String>();
		if (Static.PRODUCTION = SystemProperty.environment.value()==SystemProperty.Environment.Value.Production) {
			properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
//          properties.put("javax.persistence.jdbc.url", "jdbc:google:mysql://adrutas1:rutas/rutas?useServerPrepStmts=false");
			properties.put("javax.persistence.jdbc.url", "jdbc:google:mysql://adrutas-new:europe-west1:rutas-new/rutas");
//          properties.put("javax.persistence.jdbc.url", "jdbc:google:mysql://173.194.109.193:3306/rutas");
			properties.put("javax.persistence.jdbc.user", "root");
			properties.put("javax.persistence.jdbc.password", "1234");
			properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

			properties2.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
			properties2.put("javax.persistence.jdbc.url", "jdbc:google:mysql://adrutas1:us-central1:rutas");
			properties2.put("javax.persistence.jdbc.user", "root");
			properties2.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			log.log(Level.SEVERE, "BBDD, entra por Production");
			log.log(Level.ALL, "Prueba con ALL");
			log.log(Level.WARNING, "Prueba con WARNING");
			log.log(Level.SEVERE, "BBDD, entra por Production");
		} else {
			properties.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
//          properties.put("javax.persistence.jdbc.url", "jdbc:mysql://127.0.0.1:3306/rutas?useServerPrepStmts=false");
			properties.put("javax.persistence.jdbc.url", "jdbc:mysql://127.0.0.1:3306/rutas");
			properties.put("javax.persistence.jdbc.user", "root");
			properties.put("javax.persistence.jdbc.password", "1234");
			properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			properties2.putAll(properties);
			log.log(Level.SEVERE, "BBDD en local");
		}
		try {
			eMF = Persistence.createEntityManagerFactory("rutas", properties);
			eMF_old = Persistence.createEntityManagerFactory("rutas1", properties2);
			log.log(Level.SEVERE, "Realizada la conexión a la BBDD rutas");
		} catch (Exception e) {
			log.log(Level.SEVERE, "No se conecta a la BBDD rutas", e);
		}
		dao = new DatastoreDao();
	}

//	public static EntityManagerFactory getEMF() {
//		return eMF_old;
//	}

	public static EntityManager getEM() {
		return eMF.createEntityManager();
	}

	public static EntityManager getEM_old() {
		return eMF_old.createEntityManager();
	}

	public static BookDao getDao() {
		return dao;
	}
}
