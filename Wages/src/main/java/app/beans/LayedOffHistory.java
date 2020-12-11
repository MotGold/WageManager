package app.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "layedOffHistory")
public class LayedOffHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="employee_id")
	private LayedOff empoleeeId;
	@Column
	private Date date;
	@Column
	private double payCheck;

	public LayedOffHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LayedOffHistory(LayedOff empId, Date date, double payCheck) {
		super();
		this.empoleeeId = empId;
		this.date = date;
		this.payCheck = payCheck;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPayCheck() {
		return payCheck;
	}

	public void setPayCheck(double payCheck) {
		this.payCheck = payCheck;
	}

	public int getId() {
		return id;
	}

	public LayedOff getEmpId() {
		return empoleeeId;
	}

	public void setEmpId(LayedOff empId) {
		this.empoleeeId = empId;
	}
	
	@Override
	public String toString() {
		return "id: " + id + ", transfer date: " + date + ", salary recived: " + payCheck + ", employee: " + empoleeeId.getId()
				+ " " + empoleeeId.getFirstName() + " " + empoleeeId.getLastName();
	}

}
