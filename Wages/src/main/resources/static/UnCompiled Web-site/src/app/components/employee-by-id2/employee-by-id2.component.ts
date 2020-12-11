import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/models/employee';
import { History } from 'src/app/models/history';
import { MainControllerService } from 'src/app/services/main-controller.service';

@Component({
  selector: 'app-employee-by-id2',
  templateUrl: './employee-by-id2.component.html',
  styleUrls: ['./employee-by-id2.component.css']
})
export class EmployeeById2Component implements OnInit {

  emp: Employee;
  vis2: boolean = true;
  oldId: number;
  tempId: number;
  historyId: number;
  history: History[];

  type: number = null;
  id: number;
  firstName: string;
  lastName: string;
  bankAccount: number;
  salary: number;
  position: string;

  hiringDate: Date;
  latestDateOfTransfer: Date;

  constructor(private router: Router, private service: MainControllerService) { }

  ngOnInit(): void {
    this.id = history.state.id;
    if (this.id != null) {
      this.service.findEmployee(this.id, sessionStorage.key(0)).subscribe(
        emp => this.emp = emp,
        err => {
          alert(err.error);
          this.router.navigateByUrl('employees/list');
        }
      );
    } else
      this.router.navigateByUrl('employees/list');
  }

  public back() {
    this.router.navigateByUrl('employees/list');
  }

  public fire1employee(id: number) {
    this.service.fireEmployee(id, sessionStorage.key(0)).subscribe(
      empId => {
        alert("Employee " + empId + " have been fired!");
        this.router.navigateByUrl('employees/list');
      },
      err => alert(err.error)
    );
  }

  public sendPay(id: number) {
    this.historyId = null;
    this.service.payEmpolyee(id, sessionStorage.key(0)).subscribe(
      idd => {
        alert("Employee " + idd + " have been payed!");
        this.service.findEmployee(this.id, sessionStorage.key(0)).subscribe(
          emp => this.emp = emp,
          err => alert(err.error)
        );
      },
      err => alert(err.error)
    );
  }

  public empty() {
    this.oldId = null;
    this.vis2 = true;
  }

  public openEdit() {
    this.historyId = null;
    this.vis2 = false;
    this.oldId = this.id;
    this.tempId = this.id;
    this.hiringDate = this.emp.hiringDate;
    this.firstName = this.emp.firstName;
    this.lastName = this.emp.lastName;
    this.bankAccount = this.emp.bankAccount;
    this.salary = this.emp.salary;
    this.latestDateOfTransfer = this.emp.latestDateOfTransfer;
    this.position = this.emp.position;
  }

  public editEmployee() {
    if (this.type == null) {
      alert("please assign Department to the employee!");
    } else {
      let editedEmployee = new Employee(this.tempId, this.hiringDate, this.firstName, this.lastName, this.bankAccount, this.salary, this.latestDateOfTransfer, null, this.position);
      this.service.editEmployee(this.oldId, this.type, sessionStorage.key(0), editedEmployee).subscribe(
        emp => {
          alert("Employee " + emp.id + " have been edited!");
          this.id = emp.id;
          this.service.findEmployee(this.id, sessionStorage.key(0)).subscribe(
            emp =>this.emp = emp,
            err => alert(err.error)
          );
        },
        err => alert(err.error)
      );
    }
    this.vis2 = true;
  }

  public showHistory() {
    this.vis2 = true;
    this.historyId = this.id;
    this.service.historyOfEmployee(this.id, sessionStorage.key(0)).subscribe(
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
    this.historyId = null;
  }

}
