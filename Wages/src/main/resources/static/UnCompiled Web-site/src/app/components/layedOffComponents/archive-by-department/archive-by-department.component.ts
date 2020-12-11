import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArchivedEmployee } from 'src/app/models/archived-employee';
import { ArchivedHistory } from 'src/app/models/archived-history';
import { ArchiveControllerService } from 'src/app/services/archive-controller.service';

@Component({
  selector: 'app-archive-by-department',
  templateUrl: './archive-by-department.component.html',
  styleUrls: ['./archive-by-department.component.css']
})
export class ArchiveByDepartmentComponent implements OnInit {

  constructor(private router: Router, private service: ArchiveControllerService) { }

  employeeType: number;
  employees: ArchivedEmployee[];
  history: ArchivedHistory[];

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
  terminationDate: Date;

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
      this.router.navigateByUrl('old/archive');
  }

  public back() {
    this.router.navigateByUrl('old/archive');
  }

  public showHistory(id: number) {
    this.vis2 = true;
    this.historyId = id;
    this.service.historyOfArchivedEmployee(id, sessionStorage.key(0)).subscribe(
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

  public openEdit(emp: ArchivedEmployee) {
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
    this.terminationDate = emp.terminationDate;
  }

  public editEmployee() {
    if (this.type == null) {
      alert("please assign Department to the employee!");
    } else {
      let editedEmployee = new ArchivedEmployee(this.tempId, this.firstName, this.lastName, this.hiringDate, this.terminationDate, this.bankAccount, this.salary, this.latestDateOfTransfer, null, this.position);
      this.service.editArchivedEmployee(this.oldId, this.type, sessionStorage.key(0), editedEmployee).subscribe(
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
    this.terminationDate = null;
  }

  public Cancel() {
    this.empty();
    this.vis = false;
  }

  public fire1employee(id: number) {
    this.service.deleteEmployee(id, sessionStorage.key(0)).subscribe(
      empId => {
        alert("Employee " + empId + " have been deleted!");
        this.router.navigateByUrl('old/archive');
      },
      err => alert(err.error)
    );
  }


}
