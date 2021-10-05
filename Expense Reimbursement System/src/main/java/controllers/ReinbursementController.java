package controllers;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.Authentication.User;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import models.EmployeeTable;
import models.ReinbursementTable;
import service.ReinbursementService;

public class ReinbursementController {
private ReinbursementService reinbursementService;
	
	public ReinbursementController(Javalin app) {


		this.reinbursementService = new ReinbursementService();
		
		app.routes(() -> {
			path("employee", () -> {
				path("new-reimbursement", () -> {
					post(newReimbursement);
				});
				path("view-reimbursements", () -> {
					get(getAllReimbursements);
				});
			});
			path("manager", () -> {
//				path("all", () -> {
//					get(getAllReimbursements);
//				});
//				path("new-reinbursement", () -> {
//					post(saveReimbursement);
//				});
				path("delete-reinbursement", () -> {
					get(deleteReimbursement);
				});
				
			});
		});
		

	}
	
	private Handler getAllReimbursements = ctx -> {
		HttpSession httpsession = ctx.req.getSession(false);
		
		if(httpsession != null) {
			ctx.json(this.reinbursementService.getAllEmployeesInfo());
		}
		
		else {
			ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");
			System.out.println("No session to getAllReimbursements");
		}
	
		
	};
	
	private Handler employeeSaveReimbursement = ctx -> {
		HttpSession httpsession = ctx.req.getSession(false);
		
		if(httpsession != null) {
			EmployeeTable user = (EmployeeTable) httpsession.getAttribute("current_user");
			
			ReinbursementTable reimbursement = new ReinbursementTable(
					1,
					Double.valueOf(ctx.req.getParameter("travel_r")),
					Double.valueOf(ctx.req.getParameter("mileage_r")),
					Double.valueOf(ctx.req.getParameter("food_r")),
					Double.valueOf(ctx.req.getParameter("supplies_r")),
					ctx.req.getParameter("status"),
					user
					);
			
			this.reinbursementService.save(reimbursement);
			
			ctx.redirect("EmployeeLoginPage.html");
		}
		
		else {
			ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");
			System.out.println("No session to saveEmployee");
		}

	};
	
	
	private Handler newReimbursement = ctx -> {
		HttpSession httpsession = ctx.req.getSession(false);
		
		if(httpsession != null) {
			EmployeeTable user = (EmployeeTable) httpsession.getAttribute("current_user");
			
			ReinbursementTable reimbursement = new ReinbursementTable(
					1,
					Double.valueOf(ctx.req.getParameter("travel_r")),
					Double.valueOf(ctx.req.getParameter("mileage_r")),
					Double.valueOf(ctx.req.getParameter("food_r")),
					Double.valueOf(ctx.req.getParameter("supplies_r")),
					"pending",
					user
					);
			
			this.reinbursementService.save(reimbursement);
			
			ctx.redirect("EmployeeLoginPage.html");
		}
		
		else {
			ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");
			System.out.println("No session to saveEmployee");
		}

	};
	
	private Handler deleteReimbursement = ctx -> {
		HttpSession httpsession = ctx.req.getSession(false);
		
	
		
		if(httpsession != null) {
			EmployeeTable user = (EmployeeTable) httpsession.getAttribute("current_user");
			
			int id = Integer.parseInt(ctx.req.getParameter("id"));
			
			this.reinbursementService.delete(id);
			
			ctx.redirect("EmployeeLoginPage.html");
		}
		
		else {
			ctx.res.getWriter().write("{\"error\": \"You do not have a session.\"}");
			System.out.println("No session to saveEmployee");
		}
	};
}
