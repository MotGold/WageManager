package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.beans.AutherizedAdmin;

public interface AdminRepository extends JpaRepository<AutherizedAdmin, Integer> {

	boolean existsByUser(String user);
	
	AutherizedAdmin findByUser(String user);
	
	boolean existsByUserAndPassword(String user, String password);

}
