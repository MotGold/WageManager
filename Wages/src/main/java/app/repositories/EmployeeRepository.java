package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.beans.DepartmentType;
import app.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

	boolean existsByFirstNameAndLastName(String firstName, String lastName);

	List<Employee> findByDepartment(DepartmentType department);
	
	List<Employee> findByFirstName(String firstName);
	
	List<Employee> findByLastName(String lastName);

}
