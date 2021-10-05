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
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.EmployeeTable;
import models.ReinbursementTable;
import utils.ConnectionFactory;
import utils.HibernateSessionFactory;

public class EmployeeDaoImplementation implements EmployeeDao<EmployeeTable> {

	Connection connection;

	@Override
	public EmployeeTable getById(int id) {
		EmployeeTable employee = new EmployeeTable();

		Session session = null;
		Transaction tx = null;
		
		
		try {
			session = HibernateSessionFactory.getSessionFactory();
			tx = session.beginTransaction();
			
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<EmployeeTable> cq = cb.createQuery(EmployeeTable.class);
		
			//Select *
			Root<EmployeeTable> root = cq.from(EmployeeTable.class);
			
			
			cq.select(root).where(cb.equal(root.get("employeeId"), id));
			
			Query<EmployeeTable> query = session.createQuery(cq);
			
			employee = query.uniqueResult();
			
			tx.commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}
	
	
	public EmployeeTable getByUser(String username) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		EmployeeTable t = new EmployeeTable();
		
		
		try {
			session = HibernateSessionFactory.getSessionFactory();
			tx = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<EmployeeTable> cq = cb.createQuery(EmployeeTable.class);
		
			//Select *
			Root<EmployeeTable> root = cq.from(EmployeeTable.class);
			
			//where
			cq.select(root).where(cb.equal(root.get("username"), username));
			
			Query<EmployeeTable> query = session.createQuery(cq);
			
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

	public List<EmployeeTable> getAll() throws SQLException {

		List<EmployeeTable> employeeList = new ArrayList<>();

		Session session = null;
		Transaction tx = null;
		
		
		try {
			session = HibernateSessionFactory.getSessionFactory();
			tx = session.beginTransaction();
			
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<EmployeeTable> cq = cb.createQuery(EmployeeTable.class);
		
			//Select *
			Root<EmployeeTable> root = cq.from(EmployeeTable.class);
			
			//all
			cq.select(root);
			
			Query<EmployeeTable> query = session.createQuery(cq);
			
			employeeList = query.getResultList();
	
			tx.commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
		return employeeList;
	}

	
	// Written for Hibernate
	@Override
	public void save(EmployeeTable t) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateSessionFactory.getSessionFactory();
			tx = session.beginTransaction();
			//session.save(t);
			session.saveOrUpdate(t);
			tx.commit();
		}
		
		catch(HibernateException e){
			tx.rollback();
			e.printStackTrace();
		}
		finally {
			try {
			session.close();
		
			}catch (HibernateException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(EmployeeTable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		EmployeeTable t = new EmployeeTable();
		
		
		try {
			session = HibernateSessionFactory.getSessionFactory();
			tx = session.beginTransaction();
			
			t = session.get(EmployeeTable.class, id);
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
	public List<EmployeeTable> getAll(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
