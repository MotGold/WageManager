package app.web;

public class Session {

	private int adminId;
	private Long lastAccessed;

	public Session(int adminId, Long lastAccessed) {
		super();
		this.adminId = adminId;
		this.lastAccessed = lastAccessed;
	}

	public Long getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(Long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public int getAdminId() {
		return adminId;
	}

}
