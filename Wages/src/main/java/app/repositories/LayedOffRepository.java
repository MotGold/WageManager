package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.beans.DepartmentType;
import app.beans.LayedOff;

public interface LayedOffRepository extends JpaRepository<LayedOff, Integer> {
	

	List<LayedOff>  findByFirstNameAndLastName(String firstName, String lastName);

	boolean existsByFirstNameAndLastName(String firstName, String lastName);

	List<LayedOff> findByDepartment(DepartmentType department);
	
	List<LayedOff> findByFirstName(String firstName);
	
	List<LayedOff> findByLastName(String lastName);

}
