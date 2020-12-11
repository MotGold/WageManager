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
@Table(name = "LayedOff")
public class LayedOff {

	@Id
	private int id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private Date hiringDate;
	@Column
	private Date terminationDate;
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
	@OneToMany(mappedBy = "empoleeeId",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<LayedOffHistory> history;

	public LayedOff(int id, String firstName, String lastName, Date hiringDate, Date terminationDate, long bankAccount,
			double salary, Date latestDateOfTransfer, DepartmentType department, String position) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hiringDate = hiringDate;
		this.terminationDate = terminationDate;
		this.bankAccount = bankAccount;
		this.salary = salary;
		this.latestDateOfTransfer = latestDateOfTransfer;
		this.department = department;
		this.position = position;
	}

	public LayedOff() {
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

	public Date getHiringDate() {
		return hiringDate;
	}

	public void setHiringDate(Date hiringDate) {
		this.hiringDate = hiringDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
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

	public void setSalary(double latestSalaryRecived) {
		this.salary = latestSalaryRecived;
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

	public List<LayedOffHistory> getHistory() {
		return history;
	}

	public void setHistory(List<LayedOffHistory> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", hiringDate=" + hiringDate
				+ ", terminationDate=" + terminationDate + ", bankAccount=" + bankAccount + ", salary="
				+ salary + ", latestDateOfTransfer=" + latestDateOfTransfer + ", department=" + department
				+ ", position=" + position + "]";
	}

}
