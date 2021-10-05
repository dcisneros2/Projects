package driver;

//import java.sql.Connection;

import controllers.EmployeeController;
import controllers.ReinbursementController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

//import utils.ConnectionFactory;

public class Driver {

	public static void main(String[] args) {
		// Javalin Connection Start
		
		Javalin app = Javalin.create().start(7000);
		
		app.after(ctx -> {
			ctx.res.addHeader("Access-Control-Allow-Origin", "null");
		});
		
		
		
		//Connection conn = ConnectionFactory.getConnection();
		
		
		//TODO: Add controller init app
		//EmployeeController.init(app);
		EmployeeController employeeController = new EmployeeController(app);
		ReinbursementController reinbursementController = new ReinbursementController(app);

		
		
		//defines where html files reside
		app.config.addStaticFiles("/static", Location.CLASSPATH);

	}
}
