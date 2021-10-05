package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.ReinbursementTable;
import utils.ConnectionFactory;
import utils.HibernateSessionFactory;

public class ReinbursementDaoImplementation implements ReinbursementDao<ReinbursementTable> {

	@Override
	public ReinbursementTable getById(int id) {

		return null;
	}
	
	
	public ReinbursementTable getByUser(String username) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		ReinbursementTable t = new ReinbursementTable();
		
		
		try {
			session = HibernateSessionFactory.getSessionFactory();
			tx = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<ReinbursementTable> cq = cb.createQuery(ReinbursementTable.class);
		
			//Select *
			Root<ReinbursementTable> root = cq.from(ReinbursementTable.class);
			
			//where
			cq.select(root).where(cb.equal(root.get("username"), username));
			
			Query<ReinbursementTable> query = session.createQuery(cq);
			
			t = query.uniqueResult();
			
			tx.commit();
		}
		
		catch(HibernateException e){
			tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	
		return t;
	}

	public List<ReinbursementTable> getAll() throws SQLException {

		Session session = null;
		Transaction tx = null;
		
		
		List<ReinbursementTable> reimbursementList = new ArrayList<>();
		

		try {
			session = HibernateSessionFactory.getSessionFactory();
			tx = session.beginTransaction();
			
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<ReinbursementTable> cq = cb.createQuery(ReinbursementTable.class);
		
			//Select *
			Root<ReinbursementTable> root = cq.from(ReinbursementTable.class);
			
			//all
			cq.select(root);
			
			Query<ReinbursementTable> query = session.createQuery(cq);
			
			reimbursementList = query.getResultList();
			
			tx.commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
		return reimbursementList;
	}

	
	// Written for Hibernate
	@Override
	public void save(ReinbursementTable t) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateSessionFactory.getSessionFactory();
			tx = session.beginTransaction();
			session.save(t);
			
			tx.commit();
		}
		
		catch(HibernateException e){
			tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
	}

	@Override
	public void update(ReinbursementTable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		ReinbursementTable t = new ReinbursementTable();
		
		
		try {
			session = HibernateSessionFactory.getSessionFactory();
			tx = session.beginTransaction();
			
			t = session.get(ReinbursementTable.class, id);
			session.delete(t);
			tx.commit();
		}
		
		catch(HibernateException e){
			tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	@Override
	public List<ReinbursementTable> getAll(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
