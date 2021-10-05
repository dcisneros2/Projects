package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;

import daos.EmployeeDaoImplementation;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import models.EmployeeTable;
import models.ReinbursementTable;
import net.bytebuddy.asm.Advice.This;
import service.EmployeeService;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class EmployeeController {

	private EmployeeService employeeService;
	
	public EmployeeController(Javalin app) {


		this.employeeService = new EmployeeService();
		
		app.routes(() -> {
			path("loginSubmission", () -> {
				post(verifyLoginInfo);
			});
			path("logout", () -> {
				get(logoutUser);
			});
			path("manager", () -> {
				path("all", () -> {
					get(getAllEmployees);
				});
//				path("new-employee", () -> {
//					//get(newEmployee);
//				});
//				path("delete-employee", () -> {
//					get(deleteEmployee);
//				});
				path("update-reimbursement", () -> {
					post(updateEmployee);
				});
			});
			path("employee", () -> {
				path("reimbursements", () -> {
					get(getEmployeeInfo);
				});
		
			});
		});
		

	}
	
	private Handler logoutUser = ctx -> {
		HttpSession httpsession = ctx.req.getSession(false);

		if(httpsession != null) {
			httpsession.invalidate();
			ctx.redirect("EmployeeLoginPage.html");
		}
	};
	
	
	private Handler getEmployeeInfo = ctx -> {
		HttpSession httpsession = ctx.req.getSession(false);
		
		if(httpsession != null) {
			EmployeeTable employee = new EmployeeTable();
			employee = (EmployeeTable) httpsession.getAttribute("current_user");
			
			
			
			if(employee.getAccountType().equals("employee")) {			
				ctx.json(this.employeeService.getUserById((int)httpsession.getAttribute("id")));
				
			}
			

		}
		else {
			//ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");
			System.out.println("No session to getAllEmployees");
		}
	};

	
	private Handler getAllEmployees = ctx -> {
		HttpSession httpsession = ctx.req.getSession(false);
		

		
		if(httpsession != null) {
			ctx.json(this.employeeService.getAllEmployeesInfo());
		}
		
		else {
			ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");
			System.out.println("No session to getAllEmployees");
		}
	};
	
	private Handler verifyLoginInfo = ctx -> {

		EmployeeTable employee = new EmployeeTable();
		
		employee = this.employeeService.getUser(ctx.req.getParameter("username"), ctx.req.getParameter("password"));
		
		
		if(employee == null) {
			// Try again
			System.out.println("Wrong info. Try again");
			//ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");

			//TODO: Redirect here or in js script
			ctx.redirect("EmployeeLoginPage.html");
		}
		else {
			// User matched information, give session
			System.out.println("User found, logging in");
			HttpSession	httpsession = ctx.req.getSession();
			httpsession.setAttribute("current_user", employee);
			httpsession.setAttribute("employee_type", employee.getAccountType());
			httpsession.setAttribute("id", employee.getEmployeeId());
			
			if(employee.getAccountType().equals("employee")) {
				ctx.redirect("employeehome.html");
			}
			else if (employee.getAccountType().equals("manager")){
				ctx.redirect("managerhome.html");
			}
			
		}
	};
	
	private Handler updateEmployee = ctx -> {
		HttpSession httpsession = ctx.req.getSession(false);
		
		
		if(httpsession != null) {
			EmployeeTable employee = new EmployeeTable();
			
			employee = (EmployeeTable) httpsession.getAttribute("current_user");
			
			
			if(employee.getAccountType().equals("employee")) {
				Set<ReinbursementTable> reimbursements = employee.getReinbursements();
	
				ReinbursementTable reimbursement = new ReinbursementTable(
						1,
						Double.valueOf(ctx.req.getParameter("travel_r")),
						Double.valueOf(ctx.req.getParameter("mileage_r")),
						Double.valueOf(ctx.req.getParameter("food_r")),
						Double.valueOf(ctx.req.getParameter("supplies_r")),
						"submitted",
						employee
						);
	
				reimbursements.add(reimbursement);
				employee.setReinbursements(reimbursements);
				
				this.employeeService.save(employee);
				
				ctx.redirect("EmployeeLoginPage.html");
			}
			
			
			
			
			
			else if(employee.getAccountType().equals("manager")) {
				
					EmployeeTable t = new EmployeeTable();
					Set <ReinbursementTable> r = new HashSet<>();
					
					t = this.employeeService.getUserById(Integer.valueOf(ctx.req.getParameter("id")));
					
					if(t != null) {
						r = t.getReinbursements();
						if(r != null) {
							for(ReinbursementTable i : r) {
								if(i.getReinbursementId() == Integer.valueOf(ctx.req.getParameter("reinbursement_id"))) {
									i.setStatus(ctx.req.getParameter("status"));
								}
								else {
									System.out.println("No Reinbursement by that Id");
								}
								
							}
							t.setReinbursements(r);
							this.employeeService.save(t);
						}
						else {
							System.out.println("No Reimbursements for that account");
						}
					}
			}
			
			else {
				ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");
				System.out.println("No session to updateEmployee. Not employee of company");
			}
			
		}
		
		else {
			ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");
			System.out.println("No session to updateEmployee");
		}
	};
	
	private Handler newEmployee = ctx -> {
		HttpSession httpsession = ctx.req.getSession(false);
		
		if(httpsession != null) {
			
			
			Set<ReinbursementTable> reimbursements = new HashSet<>();
			
			EmployeeTable employee = new EmployeeTable(
					1,
					ctx.req.getParameter("firstName"),
					ctx.req.getParameter("lastName"),
					ctx.req.getParameter("username"),
					ctx.req.getParameter("password"),
					ctx.req.getParameter("account_type"),
					reimbursements
					);
			
			this.employeeService.save(employee);
			
			ctx.redirect("EmployeeLoginPage.html");
		}
		
		else {
			ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");
			System.out.println("No session to newEmployee");
		}
	};
	
	private Handler deleteEmployee = ctx -> {
		HttpSession httpsession = ctx.req.getSession(false);
		
		if(httpsession != null) {
			int id = Integer.parseInt(ctx.req.getParameter("id"));
			
			this.employeeService.delete(id);
			
			ctx.redirect("EmployeeLoginPage.html");
		}
		
		else {
			ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");
			System.out.println("No session to deleteEmployee");
		}
	};
	
}
