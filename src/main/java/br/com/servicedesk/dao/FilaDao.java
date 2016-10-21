package br.com.servicedesk.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.servicedesk.factory.ConnectionFactory;

public class FilaDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public FilaDao() {
		manager = ConnectionFactory.getConnection();
	}
	public Long getIdByName(String nomeFila) {
		try {
			manager.getTransaction().begin();
			return (long) manager.createQuery("select p.fila_id from fila p where p.fila_nome=:fila")
					.setParameter("fila", nomeFila).getFirstResult();
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
			return String.valueOf(manager.createQuery("select p.fila_nome from fila p where p.fila_id=:fila")
					.setParameter("fila", idFila).getSingleResult());
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e ;
		}finally{
			manager.close();
		}
	}
	
	public ArrayList<String> listAll(){
		try {
			manager.getTransaction().begin();
			return (ArrayList<String>)manager.createQuery("select p.nomeFila from Fila p order by idFila",String.class).getResultList(); 
		} catch (Exception e) {
			throw e ;
		}finally{
			manager.close();
		}
	}
}
