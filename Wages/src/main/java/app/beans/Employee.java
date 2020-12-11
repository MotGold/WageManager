package app.beans;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "empolyees")
public class Employee {

	@Id
	private int id;
	@Column
	private Date hiringDate;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private long bankAccount;
	@Column
	private double salary;
	@Column
	private Date latestDateOfTransfer;
	@Column
	private DepartmentType department;
	@Column
	private String position;
	@OneToMany(mappedBy = "empId", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<History> history;

	public Employee(int id, String firstName, String lastName, Date hiringDate, long bankAccount,
			double salary, Date latestDateOfTransfer, DepartmentType department, String position) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hiringDate = hiringDate;
		this.bankAccount = bankAccount;
		this.salary = salary;
		this.latestDateOfTransfer = latestDateOfTransfer;
		this.department = department;
		this.position = position;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public long getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(long bankAccount) {
		this.bankAccount = bankAccount;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getLatestDateOfTransfer() {
		return latestDateOfTransfer;
	}

	public void setLatestDateOfTransfer(Date latestDateOfTransfer) {
		this.latestDateOfTransfer = latestDateOfTransfer;
	}

	public DepartmentType getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentType department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}

	public Date getHiringDate() {
		return hiringDate;
	}

	public void setHiringDate(Date hiringDate) {
		this.hiringDate = hiringDate;
	}

	@Override
	public String toString() {
		return "Empolyee " + id + ", name=" + firstName + ", family name=" + lastName + ", department=" + department
				+ ", position=" + position + ", bank Account=" + bankAccount + ", salary=" + salary
				+ ", date of transaction=" + latestDateOfTransfer;
	}

}
