package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity

@Table(name = "reinbursements_db")
public class ReinbursementTable {
	
	@Id
	@Column(name="reinbursement_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int reinbursementId;
	
	@Column(name="travel_r")
	double travel_r;
	@Column(name="mileage_r")
	double mileage_r;
	@Column(name="food_r")
	double food_r;
	@Column(name="supplies_r")
	double supplies_r;
	@Column(name="status")
	String status;
	
	@ManyToOne
	@JoinColumn(name="employee_id", nullable=false)
	private EmployeeTable employee_table;
	
	public ReinbursementTable() {
		super();
	}
	
	
	
	public ReinbursementTable(int reinbursementId, double travel_r, double mileage_r, double food_r, double supplies_r,
			String status, EmployeeTable t) {
		super();
		this.reinbursementId = reinbursementId;
		this.travel_r = travel_r;
		this.mileage_r = mileage_r;
		this.food_r = food_r;
		this.supplies_r = supplies_r;
		this.status = status;
		this.employee_table = t;
	}



	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getReinbursementId() {
		return reinbursementId;
	}
	public void setReinbursementId(int reinbursementId) {
		this.reinbursementId = reinbursementId;
	}
	public double getTravel_r() {
		return travel_r;
	}
	public void setTravel_r(double travel_r) {
		this.travel_r = travel_r;
	}
	public double getMileage_r() {
		return mileage_r;
	}
	public void setMileage_r(double mileage_r) {
		this.mileage_r = mileage_r;
	}
	public double getFood_r() {
		return food_r;
	}
	public void setFood_r(double food_r) {
		this.food_r = food_r;
	}
	public double getSupplies_r() {
		return supplies_r;
	}
	public void setSupplies_r(double supplies_r) {
		this.supplies_r = supplies_r;
	}
	public void setEmployeeTable(EmployeeTable t) {
		this.employee_table = t;
	}
	public EmployeeTable getEmployeeTable(EmployeeTable t) {
		return employee_table;
	}
	
	
}
