package app.web;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.beans.AutherizedAdmin;
import app.beans.DepartmentType;
import app.beans.Employee;
import app.beans.History;
import app.beans.LayedOff;
import app.beans.LayedOffHistory;
import app.exceptions.DoesNotExistException;
import app.exceptions.DuplicateException;
import app.services.MainService;

@RestController
@RequestMapping("controller")
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

	public MainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private Map<String, Session> sessionsMap;

	@Autowired
	private MainService mainService;

	@GetMapping("admins/{token}")
	public List<AutherizedAdmin> getAllAdmins(@PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			return mainService.getallAdmins();
		} else
			throw new DoesNotExistException(); // "Token expired"
	}

	@GetMapping("history/{id}/{token}")
	public List<History> getHistory(@PathVariable int id, @PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			return mainService.GetEmployeeHistory(id);
		} else
			throw new DoesNotExistException(); // "Token expired"
	}
	
	@GetMapping("archivedHistory/{id}/{token}")
	public List<LayedOffHistory> getArchivedHistory(@PathVariable int id, @PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			return mainService.GetArchivedHistory(id);
		} else
			throw new DoesNotExistException(); // "Token expired"
	}

	@PostMapping("addAdmin/{token}")
	public ResponseEntity<?> newAdmin(@PathVariable String token, @RequestBody AutherizedAdmin admin) {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			try {
				mainService.addAdmin(admin);
				return ResponseEntity.ok(admin);
			} catch (DuplicateException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("session expired");
	}

	@DeleteMapping("admins/delete/{id}/{token}")
	public void deleteAdmin(@PathVariable int id, @PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			mainService.deleteAdmin(id);
		} else
			throw new DoesNotExistException();
	}

	@GetMapping("employees/{token}")
	public List<Employee> getAllEmployees(@PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			return mainService.getAllEmployees();
		} else
			throw new DoesNotExistException(); // "Token expired"
	}
	
	@GetMapping("archivedEmployees/{token}")
	public List<LayedOff> getAllArchivedEmployees(@PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			return mainService.getAllArchivedEmployees();
		} else
			throw new DoesNotExistException(); // "Token expired"
	}
	
	@GetMapping("employees/{first}/{last}/{token}")
	public List<Employee> getEmployeesByName(@PathVariable String first, @PathVariable String last, @PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			return mainService.findEmployeeByName(first, last);
		} else
			throw new DoesNotExistException(); // "Token expired"
	}
	
	@GetMapping("employeesDep/{type}/{token}")
	public List<Employee> getEmployeesByDepartment( @PathVariable int type, @PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			return mainService.getDepartmentEmployees(DepartmentType.valueOf(type));
		} else
			throw new DoesNotExistException(); // "Token expired"
	}
	
	@GetMapping("archivedDepartment/{type}/{token}")
	public List<LayedOff> getArchivedEmployeesByDepartment( @PathVariable int type, @PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			return mainService.getDepartmentArchivedEmployees(DepartmentType.valueOf(type));
		} else
			throw new DoesNotExistException(); // "Token expired"
	}
	
	@GetMapping("employees/{id}/{token}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int id, @PathVariable String token)  {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			try {
				return ResponseEntity.ok(mainService.findEmployeeById(id));
			} catch (DoesNotExistException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		} 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("session expired");
	}
	
	@GetMapping("archivedEmployees/{id}/{token}")
	public ResponseEntity<?> getArchivedEmployeesById(@PathVariable int id, @PathVariable String token)  {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			try {
				return ResponseEntity.ok(mainService.findArchivedEmployeeById(id));
			} catch (DoesNotExistException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		} 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("session expired");
	}
	
	@GetMapping("archivedEmployees/{first}/{last}/{token}")
	public List<LayedOff> getArchivedEmployeesByName(@PathVariable String first, @PathVariable String last, @PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			return mainService.findArchivedEmployeeByName(first, last);
		} else
			throw new DoesNotExistException(); // "Token expired"
	}
	
	@PostMapping("editEmployee/{oldId}/{department}/{token}")
	public ResponseEntity<?> editEmployee(@PathVariable int oldId, @PathVariable int department,
			@PathVariable String token, @RequestBody Employee guy) {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			try {
				guy.setDepartment(DepartmentType.valueOf(department));
				mainService.editEmployee(oldId, guy);
				return ResponseEntity.ok(guy);
			} catch (DoesNotExistException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("session expired");
	}
	
	@PostMapping("editArchivedEmployee/{oldId}/{department}/{token}")
	public ResponseEntity<?> editArchivedEmployee(@PathVariable int oldId, @PathVariable int department,
			@PathVariable String token, @RequestBody LayedOff guy) {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			try {
				guy.setDepartment(DepartmentType.valueOf(department));
				mainService.editLayedoff(oldId, guy);
				return ResponseEntity.ok(guy);
			} catch (DoesNotExistException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("session expired");
	}

	@PostMapping("addEmployee/{department}/{token}")
	public ResponseEntity<?> addEmployee(@PathVariable int department, @PathVariable String token,
			@RequestBody Employee newGuy) {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			try {
				newGuy.setDepartment(DepartmentType.valueOf(department));
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String stringDate = sdf.format(new java.util.Date());
				Date sqlDate = new Date(sdf.parse(stringDate).getTime()); // <-- java.util.Date utilDate = sdf.parse(stringDate);
				newGuy.setHiringDate(sqlDate);                            //     Date sqlDate = new Date(utilDate.getTime());
				mainService.addEmployee(newGuy);
				return ResponseEntity.ok(newGuy);
			} catch (DuplicateException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			} catch (ParseException e) {
				return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("session expired");
	}

	@DeleteMapping("employees/fire/{id}/{token}")
	public ResponseEntity<?> FireEmployee(@PathVariable int id, @PathVariable String token) {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			try {
				mainService.endEmploymnent(id);
				return ResponseEntity.ok(id);
			} catch (DoesNotExistException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} catch (ParseException e) {
				return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("session expired");
	}
	
	@DeleteMapping("archivedEmployees/delete/{id}/{token}")
	public void deleteEmployee(@PathVariable int id, @PathVariable String token) throws DoesNotExistException {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			mainService.deleteArchivedEmployee(id);
		}else
			throw new DoesNotExistException(); // "Token expired
	}

	@GetMapping("pay/{id}/{token}")
	public ResponseEntity<?> sendPayment(@PathVariable int id, @PathVariable String token) {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			try {
				mainService.payTheMan(id);
				return ResponseEntity.ok(id);
			} catch (DoesNotExistException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} catch (ParseException e) {
				return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
			} catch (DuplicateException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("session expired");
	}

	@GetMapping("payAll/{token}")
	public ResponseEntity<?> payAll(@PathVariable String token) {
		Session ses = sessionsMap.get(token);
		if (ses != null) {
			ses.setLastAccessed(System.currentTimeMillis());
			sessionsMap.put(token, ses);
			List<Employee> dudes = mainService.getAllEmployees();
			for (int i = 0; i < dudes.size(); i++) {
				try {
					mainService.payTheMan(dudes.get(i).getId());
				} catch (DoesNotExistException e) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
				} catch (ParseException e) {
					return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
				} catch (DuplicateException e) {
					return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
				}
			}
			return ResponseEntity.ok("all employees have been payed!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("session expired");
	}

}
