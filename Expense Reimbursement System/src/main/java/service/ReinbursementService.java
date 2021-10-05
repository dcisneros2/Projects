package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import daos.ReinbursementDaoImplementation;
import models.ReinbursementTable;

public class ReinbursementService {
	private ReinbursementDaoImplementation reinbursementDaoImplementation;
	
	public ReinbursementService() {
		this.reinbursementDaoImplementation = new ReinbursementDaoImplementation();
	}
	
	public ReinbursementTable getUser(String username, String password) {
		ReinbursementTable reinbursement = new ReinbursementTable();
		
		reinbursement = this.reinbursementDaoImplementation.getByUser(username);
		
		if(reinbursement == null) {
			System.out.println("Username not found in DB");
			return null;
		}
		else {
		}
		return null;
	}
	
	//keep incase need to format or do something to all reinbursements
	public List<ReinbursementTable> getAllEmployeeInfo2(){
		try {
			List<ReinbursementTable> reimbursements = this.reinbursementDaoImplementation.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<ReinbursementTable> getAllEmployeesInfo() throws SQLException{
		//TODO remove password line, maybe remove all but reinbursement info
		//if not a manager
		List <ReinbursementTable> r = this.reinbursementDaoImplementation.getAll();
		
		//String str = (String) httpsession.getAttribute("user_id");
		
		for(ReinbursementTable reimbursement : r) {
			System.out.println(reimbursement.getReinbursementId());
		}
		
		return this.reinbursementDaoImplementation.getAll();
	}
	
	public void save(ReinbursementTable e) {
		this.reinbursementDaoImplementation.save(e);
	}
	
	
	public void delete(int id) {
		this.reinbursementDaoImplementation.delete(id);
	}
}
