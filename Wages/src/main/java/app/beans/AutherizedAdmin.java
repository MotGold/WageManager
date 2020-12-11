package app.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class AutherizedAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String user;
	@Column
	private String password;
	
	public AutherizedAdmin(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public AutherizedAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", user=" + user + ", password=" + password + "]";
	}
	
	

}
