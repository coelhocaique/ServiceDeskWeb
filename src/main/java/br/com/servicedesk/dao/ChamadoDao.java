package br.com.servicedesk.dao;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.servicedesk.factory.ConnectionFactory;
import br.com.servicedesk.model.Chamado;

public class ChamadoDao {

	@PersistenceContext
	private EntityManager manager;

	public ChamadoDao() {
		manager = ConnectionFactory.getConnection();
	}

	public Chamado create(Chamado chamado) {
		try {
			openTransaction();
			manager.persist(chamado);
			commit();
			chamado = manager.merge(chamado);
		} catch (Exception e) {
			rollback();
			throw e;
		} finally {
			closeTransaction();
		}
		return chamado;
	}
	
	public Chamado merge(Chamado chamado) {
		try {
			openTransaction();
			chamado = manager.merge(chamado);
		} catch (Exception e) {
			rollback();
			throw e;
		} finally {
			closeTransaction();
		}
		return chamado;
	}

	public int closeById(Chamado chamado) {
		int rowsAffected = 0;
		try {
			openTransaction();
			Query query = manager.createQuery("update Chamado set dataDeFechamento=:data,"
						+ "status = 2 where numero = :numero");
			query.setParameter("data",chamado.getDataDeFechamento());
			query.setParameter("numero", chamado.getNumero());
			rowsAffected = query.executeUpdate();
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		} finally {
			closeTransaction();
		}
		return rowsAffected;
	}

	public ArrayList<Chamado> listByFilaId(int filaId) {
		try{
		openTransaction();
		return (ArrayList<Chamado>)manager.createQuery("select p from Chamado p where p.fila = :fila", Chamado.class)
				.setParameter("fila", filaId).getResultList();
		}catch(Exception e){
			throw e;
		}finally{
			closeTransaction();
		}
	}

	public ArrayList<Chamado> listByStatusId(int statusId) {
		try {
			openTransaction();
			return (ArrayList<Chamado>)manager.createQuery("select p from Chamado p where p.status_id = :status", Chamado.class)
					.setParameter("status", statusId).getResultList();
		} catch (Exception e) {
			throw e;
		} finally {
			closeTransaction();
		}

	}
	
	public ArrayList<Chamado> listAll() {
		try {
			openTransaction();
			return (ArrayList<Chamado>)manager.createQuery("select p from Chamado p order by numero", Chamado.class)
					.getResultList();
		} catch (Exception e) {
			throw e;
		} finally {
			closeTransaction();
		}

	}
	

	/*
	 * Transaction Helper methods
	 */

	private void openTransaction() {
		manager.getTransaction().begin();
	}

	private void commit() {
		manager.getTransaction().commit();
	}

	private void rollback() {
		manager.getTransaction().rollback();
	}

	private void closeTransaction() {
		manager.close();
	}
}
