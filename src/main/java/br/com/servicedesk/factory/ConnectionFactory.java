package br.com.servicedesk.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
	public static EntityManager getConnection(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("servicedesk");
		return entityManagerFactory.createEntityManager();
	}
}
