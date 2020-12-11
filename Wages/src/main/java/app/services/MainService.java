package app.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.beans.AutherizedAdmin;
import app.beans.DepartmentType;
import app.beans.Employee;
import app.beans.History;
import app.beans.LayedOff;
import app.beans.LayedOffHistory;
import app.exceptions.DoesNotExistException;
import app.exceptions.DuplicateException;
import app.repositories.AdminRepository;
import app.repositories.EmployeeRepository;
import app.repositories.HistoryRepository;
import app.repositories.LayedOffHistoryRepository;
import app.repositories.LayedOffRepository;

@Service
public class MainService {

	@Autowired
	protected AdminRepository editAdmins;
	@Autowired
	protected EmployeeRepository editEmployees;
	@Autowired
	protected HistoryRepository editHistory;
	@Autowired
	protected LayedOffRepository pastEmployees;
	@Autowired
	protected LayedOffHistoryRepository pastHistory;

	public boolean login(String user, String password) {
		if (editAdmins.findAll().isEmpty())
			editAdmins.save(new AutherizedAdmin("user", "password"));
		if (editAdmins.existsByUserAndPassword(user, password))
			return true;
		// System.out.println("Wrong password or user entered");
		return false;
	}

	public void addAdmin(AutherizedAdmin newAdmin) throws DuplicateException {
		if (editAdmins.existsByUser(newAdmin.getUser()))
			throw new DuplicateException();
		else
			editAdmins.save(newAdmin);
	}

	public String deleteAdmin(int id) {
		if (editAdmins.existsById(id)) {
			editAdmins.deleteById(id);
			return "admin deleted.";
		} else
			return "warning: the admin you tried to delete does not exists in the database!";
	}
	
	public AutherizedAdmin findAdminByUser(String user) throws DoesNotExistException {
		if (editAdmins.existsByUser(user))
			return editAdmins.findByUser(user);
		else
			throw new DoesNotExistException();
	}

	public List<AutherizedAdmin> getallAdmins() {
		return editAdmins.findAll();
	}

	public void addEmployee(Employee newGuy) throws DuplicateException {
		if (editEmployees.existsById(newGuy.getId()))
			throw new DuplicateException();
		else
			editEmployees.save(newGuy);
	}

	public void editEmployee(int oldId, Employee emp) throws DoesNotExistException {
		if (editEmployees.existsById(oldId)) {
			List<History> employeeHistory = findEmployeeById(oldId).getHistory();
			deleteEmployee(oldId);
			editEmployees.save(emp);
			for (int i = 0; i < employeeHistory.size(); i++)
				addHistory(new History(emp, employeeHistory.get(i).getDate(), employeeHistory.get(i).getPayCheck()));
		} else
			throw new DoesNotExistException();
	}

	public void editEmployee(Employee emp) throws DoesNotExistException {
		if (editEmployees.existsById(emp.getId())) {
			editEmployees.save(emp);
		} else
			throw new DoesNotExistException();
	}

	public void editLayedoff(int oldId,LayedOff emp) throws DoesNotExistException {
		if (pastEmployees.existsById(oldId)) {
			List<LayedOffHistory> employeeHistory = findArchivedEmployeeById(oldId).getHistory();
			deleteArchivedEmployee(oldId);
			pastEmployees.save(emp);
			for (int i = 0; i < employeeHistory.size(); i++)
				addArchivedHistory(new LayedOffHistory(emp, employeeHistory.get(i).getDate(), employeeHistory.get(i).getPayCheck()));
		}else
			throw new DoesNotExistException();
	}

	public String deleteEmployee(int id) {
		if (editEmployees.existsById(id)) {
			editEmployees.deleteById(id);
			return "Employee deleted.";
		} else
			return "warning: the Employee you tried to delete does not exists in the database!";
	}

	public String deleteArchivedEmployee(int id) {
		if (pastEmployees.existsById(id)) {
			pastEmployees.deleteById(id);
			return "Employee deleted.";
		} else
			return "warning: the Employee you tried to delete does not exists in the database!";
	}

	public List<Employee> getAllEmployees() {
		return editEmployees.findAll();
	}

	public List<LayedOff> getAllArchivedEmployees() {
		return pastEmployees.findAll();
	}

	public List<Employee> getDepartmentEmployees(DepartmentType type) {
		return editEmployees.findByDepartment(type);
	}

	public List<LayedOff> getDepartmentArchivedEmployees(DepartmentType type) {
		return pastEmployees.findByDepartment(type);
	}

	public Employee findEmployeeById(int id) throws DoesNotExistException {
		if (editEmployees.existsById(id))
			return editEmployees.findById(id).get();
		else
			throw new DoesNotExistException();
	}

	public LayedOff findArchivedEmployeeById(int id) throws DoesNotExistException {
		if (pastEmployees.existsById(id))
			return pastEmployees.findById(id).get();
		else
			throw new DoesNotExistException();
	}

	public List<Employee> findEmployeeByName(String first, String last) throws DoesNotExistException {
		if (editEmployees.existsByFirstNameAndLastName(first, last))
			return editEmployees.findByFirstNameAndLastName(first, last);
		else
			throw new DoesNotExistException();
	}

	public List<LayedOff> findArchivedEmployeeByName(String first, String last) throws DoesNotExistException {
		if (pastEmployees.existsByFirstNameAndLastName(first, last))
			return pastEmployees.findByFirstNameAndLastName(first, last);
		else
			throw new DoesNotExistException();
	}

	public List<History> GetEmployeeHistory(int id) throws DoesNotExistException {
		return findEmployeeById(id).getHistory();
	}

	public List<LayedOffHistory> GetArchivedHistory(int id) throws DoesNotExistException {
		return findArchivedEmployeeById(id).getHistory();
	}

	public List<History> GetHistoryByDate(Date date) {
		return editHistory.findByDate(date);
	}

	public List<LayedOffHistory> GetArchivedHistoryByDate(Date date) {
		return pastHistory.findByDate(date);
	}

	public List<History> GetAllHistory() {
		return editHistory.findAll();
	}

	public List<LayedOffHistory> GetAllArchivedHistory() {
		return pastHistory.findAll();
	}

	public void addHistory(History salary) {
		editHistory.save(salary);
	}

	public void addArchivedHistory(LayedOffHistory salary) {
		pastHistory.save(salary);
	}

	public void RemovePayment(int id) {
		editHistory.deleteById(id);
	}

	public void RemoveArchivedPayment(int id) {
		pastHistory.deleteById(id);
	}

	public List<History> GetEmployeeHistoryByDate(int id, Date date) throws DoesNotExistException {
		List<History> employeeHistory = GetEmployeeHistory(id);
		List<History> dateHistory = new ArrayList<>();
		for (int i = 0; i < employeeHistory.size(); i++) {
			if (employeeHistory.get(i).getDate().equals(date))
				dateHistory.add(employeeHistory.get(i));
		}
		return dateHistory;
	}

	public List<LayedOffHistory> GetEmployeeArchivedHistoryByDate(int id, Date date) throws DoesNotExistException {
		List<LayedOffHistory> employeeHistory = GetArchivedHistory(id);
		List<LayedOffHistory> dateHistory = new ArrayList<>();
		for (int i = 0; i < employeeHistory.size(); i++) {
			if (employeeHistory.get(i).getDate().equals(date))
				dateHistory.add(employeeHistory.get(i));
		}
		return dateHistory;
	}

	public void payTheMan(int id) throws DoesNotExistException, ParseException, DuplicateException {
		Employee man = findEmployeeById(id);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String stringDate = sdf.format(new java.util.Date());
		Date sqlDate = new Date(sdf.parse(stringDate).getTime()); // <-- java.util.Date utilDate = sdf.parse(stringDate);
		man.setLatestDateOfTransfer(sqlDate);                     //     Date sqlDate = new Date(utilDate.getTime());
		History salary = new History(man, sqlDate, man.getSalary());
		// send the money
		// send message
		addHistory(salary);
		editEmployee(man);
	}

	public void endEmploymnent(int id) throws DoesNotExistException, ParseException {
		Employee man = findEmployeeById(id);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String stringDate = sdf.format(new java.util.Date());
		Date sqlDate = new Date(sdf.parse(stringDate).getTime());
		List<History> employeeHistory = man.getHistory();
		LayedOff undead = new LayedOff(man.getId(), man.getFirstName(), man.getLastName(), man.getHiringDate(), sqlDate,
				man.getBankAccount(), man.getSalary(), man.getLatestDateOfTransfer(), man.getDepartment(),
				man.getPosition());
		pastEmployees.save(undead);
		for (int i = 0; i < employeeHistory.size(); i++)
			addArchivedHistory(new LayedOffHistory(undead, employeeHistory.get(i).getDate(),
					employeeHistory.get(i).getPayCheck()));
		deleteEmployee(id);
	}

}
