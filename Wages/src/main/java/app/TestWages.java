package app;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import app.beans.AutherizedAdmin;
import app.beans.DepartmentType;
import app.beans.Employee;
import app.beans.History;
import app.beans.LayedOffHistory;
import app.exceptions.DoesNotExistException;
import app.exceptions.DuplicateException;
import app.services.MainService;

@Component
public class TestWages {

	public static void testAll() {

		//ConfigurableApplicationContext boot = SpringApplication.run(WagesApplication.class);

		//MainService mainService = boot.getBean(MainService.class);

		//mainService.login("ghsrt", "456");
		//mainService.login("user", "password");
		/**
		AutherizedAdmin admin = new AutherizedAdmin("josh", "423");
		try {
			mainService.addAdmin(admin);
		} catch (DuplicateException e) {
			e.printStackTrace();
		}
		*/
		//mainService.deleteAdmin(2);
		//List<AutherizedAdmin> admins = mainService.getallAdmins();
		//System.out.println(admins);
		/**
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String stringDate = sdf.format(new java.util.Date());
			Date sqlDate = new Date(sdf.parse(stringDate).getTime()); // <-- java.util.Date utilDate = sdf.parse(stringDate);
	                                                                  //     Date sqlDate = new Date(utilDate.getTime());
			Employee dude = new Employee(310277025, "mathew", "gold",sqlDate, 4500, 0, null,  DepartmentType.programming, "abcd");
			mainService.addEmployee(dude);
		} catch (DuplicateException | ParseException e) {
			System.out.println(e.getMessage());
		}
		*/
		//mainService.deleteEmployee(310277025);
		/**
		try {
			Employee dude = mainService.findEmployeeById(311266734);
			//dude.setId(311266734);
			//mainService.editEmployee(05, dude);
			dude.setFirstName("Bob");
			mainService.editEmployee(dude);
		} catch (DoesNotExistException e) {
			System.out.println(e.getMessage());
		}
		*/
		/**
		List<Employee> employees = mainService.getAllEmployees();
		for (int i = 0; i <employees.size(); i++) {
			System.out.println(employees.get(i));
		}
		*/
		/**
		List<Employee> employees = mainService.getDepartmentEmployees(DepartmentType.programming);
		for (int i = 0; i <employees.size(); i++) {
			System.out.println(employees.get(i));
		}
		*/
		/**
		try {
			System.out.println(mainService.findEmployeeByName("bob", "gold"));
		} catch (DoesNotExistException e) {
			System.out.println(e.getMessage());
		} 
		*/
		/*
		try {
			mainService.payTheMan(310277025, 540);
			mainService.payTheMan(310277025, 1400);
			mainService.payTheMan(310277025, 2250);
		} catch (DoesNotExistException | ParseException | DuplicateException e) {
			System.out.println(e.getMessage());
		}
		*/
		/**
		try {
			List<History> history = mainService.GetEmployeeHistory("Bob","gold");
			for (int i = 0; i <history.size(); i++) {
				System.out.println(history.get(i));
			}
		} catch (DoesNotExistException e) {
			e.printStackTrace();
		}
		*/
		/**
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//String stringDate = sdf.format(new java.util.Date());
			Date sqlDate = new Date(sdf.parse("08/11/2020").getTime());
			List<History> history = mainService.GetHistoryByDate(sqlDate);
			for (int i = 0; i < history.size(); i++) {
				System.out.println(history.get(i));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
		/**
		try {
			mainService.endEmploymnent(311266734);
		} catch (DoesNotExistException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		/**
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String stringDate = sdf.format(new java.util.Date());
		try {
			Date sqlDate = new Date(sdf.parse("08/11/2020").getTime());
			mainService.addArchivedHistory(new LayedOffHistory(mainService.findArchivedEmployeeById(311266734), sqlDate, 5890));
		} catch (DoesNotExistException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//mainService.RemoveArchivedPayment(4);
		//mainService.RemovePayment(9);
		
	}

}
