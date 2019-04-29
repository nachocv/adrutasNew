package com.adrutas.dao;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

public class SalidaDao {
	private static final Logger log = Logger.getLogger(SalidaDao.class.getName());

	public static void doLogSalida() {
		EntityManager em = EntityManagerFactories.getEM();
//		Query q = em.createNamedQuery("Salida.findAll");
//		for (Salida salida: (List<Salida>) q.getResultList()) {
//			log.finest("salida: " + salida.getSalida() + " descripcion: " + salida.getDescripcion());
//			System.out.println("salida: " + salida.getSalida() + " descripcion: " + salida.getDescripcion());
//		}
//		em.close();
	}
}
