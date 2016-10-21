package br.com.servicedesk.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.servicedesk.factory.ConnectionFactory;
import br.com.servicedesk.model.Status;

public class StatusDao {
	
	@PersistenceContext
	private EntityManager manager;

	public StatusDao() {
		manager = ConnectionFactory.getConnection();
	}

	public Long getNameById(String nomeStatus) {
		try {
			manager.getTransaction().begin();
			return (long) manager.createQuery("select p.status_id from status_chamado p where p.status_nome=:status")
					.setParameter("status", nomeStatus).getFirstResult();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e ;
		}finally{
			manager.close();
		}
	}
	
	public String getNameById(long idFila) {
		try {
			manager.getTransaction().begin();
			return String.valueOf(manager.createQuery("select p.status_nome from fila p where p.status_id=:status")
					.setParameter("status", idFila).getSingleResult());
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e ;
		}finally{
			manager.close();
		}
	}
	
	public List<Status> listAll(){
		try {
			manager.getTransaction().begin();
			return manager.createQuery("select p from status_chamado p order by status_id",Status.class).getResultList(); 
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e ;
		}finally{
			manager.close();
		}
	}

}
