import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/models/employee';
import { History } from 'src/app/models/history';
import { MainControllerService } from 'src/app/services/main-controller.service';


@Component({
  selector: 'app-employee-by-department',
  templateUrl: './employee-by-department.component.html',
  styleUrls: ['./employee-by-department.component.css']
})
export class EmployeeByDepartmentComponent implements OnInit {

  constructor(private router: Router, private service: MainControllerService) { }

  employeeType: number;
  employees: Employee[];
  history: History[];

  vis: boolean = false;
  oldId: number;
  tempId: number;
  type: number = null;
  vis2: boolean = false;
  historyId: number;

  firstName: string;
  lastName: string;
  id: number;
  bankAccount: number;
  salary: number;
  position: string;

  hiringDate: Date;
  latestDateOfTransfer: Date;

  ngOnInit(): void {
    this.employeeType = history.state.type;
    if (this.employeeType != null) {
      this.service.findEmployeesByDepartment(this.employeeType, sessionStorage.key(0)).subscribe(
        allEmployees => this.employees = allEmployees,
        err => {
          alert("your session token has expired!");
          this.router.navigateByUrl("/login");
        }
      );
    } else
      this.router.navigateByUrl('employees/list');
  }

  public back() {
    this.router.navigateByUrl('employees/list');
  }

  public showHistory(id: number) {
    this.vis2 = true;
    this.historyId = id;
    this.service.historyOfEmployee(id, sessionStorage.key(0)).subscribe(
      employeeHistory => {
        this.history = employeeHistory;
      },
      err => {
        alert("your session token has expired!");
        this.router.navigateByUrl("/login");
      }
    );
  }

  public hideHistory() {
    this.vis2 = false;
    this.historyId = null;
  }

  public sendPay(id: number) {
    this.hideHistory();
    this.service.payEmpolyee(id, sessionStorage.key(0)).subscribe(
      idd => {
        alert("Employee " + idd + " have been payed!");
        this.service.findEmployeesByDepartment(this.employeeType, sessionStorage.key(0)).subscribe(
          allEmployees => this.employees = allEmployees,
          err => {
            alert("your session token has expired!");
            this.router.navigateByUrl("/login");
          }
        );
      },
      err => alert(err.error)
    );
  }

  public openEdit(emp: Employee) {
    this.historyId = null;
    this.vis = false;
    this.vis2 = false;
    this.id = emp.id;
    this.oldId = this.id;
    this.tempId = this.id;
    this.hiringDate = emp.hiringDate;
    this.firstName = emp.firstName;
    this.lastName = emp.lastName;
    this.bankAccount = emp.bankAccount;
    this.salary = emp.salary;
    this.latestDateOfTransfer = emp.latestDateOfTransfer;
    this.position = emp.position;
  }

  public editEmployee() {
    if (this.type == null) {
      alert("please assign Department to the employee!");
    } else {
      let editedEmployee = new Employee(this.tempId, this.hiringDate, this.firstName, this.lastName, this.bankAccount, this.salary, this.latestDateOfTransfer, null, this.position);
      this.service.editEmployee(this.oldId, this.type, sessionStorage.key(0), editedEmployee).subscribe(
        emp => {
          alert("Employee " + emp.id + " have been edited!");
          this.service.findEmployeesByDepartment(this.employeeType, sessionStorage.key(0)).subscribe(
            allEmployees => this.employees = allEmployees,
            err => {
              alert("your session token has expired!");
              this.router.navigateByUrl("/login");
            }
          );
          this.empty();
        },
        err => alert(err.error)
      );
      this.vis = false;
    }
  }

  public empty() {
    this.historyId = null;
    this.id = null;
    this.hiringDate = null;
    this.firstName = null;
    this.lastName = null;
    this.bankAccount = null;
    this.salary = null;
    this.latestDateOfTransfer = null;
    this.position = null;
    this.type = null;
    this.oldId = null;
    this.tempId = null;
    this.vis2 = false;
  }

  public Cancel() {
    this.empty();
    this.vis = false;
  }

  public fire1employee(id: number) {
    this.service.fireEmployee(id, sessionStorage.key(0)).subscribe(
      empId => {
        alert("Employee " + empId + " have been fired!");
        let index = -1;
        for (let i = 0; i < this.employees.length; i++) {
          if (this.employees[i].id == id) {
            index = i;
            break;
          }
        }
        if (index > -1)
          this.employees.splice(index, 1); // deletes one employee from index position in employeess array
      },
      err => {
        alert(err.error);
      }
    );
  }


}
