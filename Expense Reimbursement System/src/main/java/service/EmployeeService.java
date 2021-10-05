package service;
import java.sql.SQLException;
import java.util.List;

import daos.EmployeeDaoImplementation;
import models.EmployeeTable;


public class EmployeeService {
	private EmployeeDaoImplementation employeeDaoImplementation;
	
	
	public EmployeeService() {
		this.employeeDaoImplementation = new EmployeeDaoImplementation();
	}
	
	public EmployeeTable getUser(String username, String password) {
		EmployeeTable employee = new EmployeeTable();
		
		employee = this.employeeDaoImplementation.getByUser(username);
		
		if(employee == null) {
			System.out.println("Username not found in DB");
			return null;
		}
		else {
			//TODO Strip employee table password. Make method private?

			if(employee.getPassword().equals(password)) {
				return employee;
			}
			else {
				return null;
			}
	
		}
	}
	
	public EmployeeTable getUserById(int id) {
		EmployeeTable employee = new EmployeeTable();
		
		employee = this.employeeDaoImplementation.getById(id);
		
		if(employee != null) {
			return employee;
		}
		
		else {
			System.out.println("Username not found in DB");
			return null;
		}
		
	}
	
	
	public List<EmployeeTable> getAllEmployeesInfo() throws SQLException{
		//TODO remove password line, maybe remove all but employee info
		//if not a manager
		return this.employeeDaoImplementation.getAll();
	}
	
	public void save(EmployeeTable e) {
		this.employeeDaoImplementation.save(e);
	}
	
	
	public void delete(int id) {
		this.employeeDaoImplementation.delete(id);
	}
}
