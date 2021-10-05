package models;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


// Entity makes class an entity so we will be mapping this class to a table in DB.
@Entity


@Table(name = "employees_db")
public class EmployeeTable {
	
	@Id // Denotes that this will be the primary key.
	@Column(name = "employee_id") // denotes that this is a column in db table.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // denotes that value for this is auto-generated. In my case, auto-inc
	int employeeId;
	
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String lastName;
	@Column(name = "username", unique = true)
	String username;
	@Column(name = "password")
	String password;
	@Column(name = "account_type")
	String accountType;
	
	@OneToMany(mappedBy = "employee_table", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // mapped by marks it as inverse. so ReibursementTable is owning side.
	private Set<ReinbursementTable> reimbursements;
	
	public EmployeeTable() {
		super();
	}
	
	public EmployeeTable(int employeeId, String firstName, String lastName, String username, String password, String accountType, Set<ReinbursementTable> reimbursements) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.reimbursements = reimbursements;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public void setReinbursements(Set<ReinbursementTable> reimbursements){
		this.reimbursements = reimbursements;
	}
	public Set<ReinbursementTable> getReinbursements(){
		return reimbursements;
	}
}
