import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArchivedEmployee } from 'src/app/models/archived-employee';
import { ArchivedHistory } from 'src/app/models/archived-history';
import { ArchiveControllerService } from 'src/app/services/archive-controller.service';

@Component({
  selector: 'app-employee-archive',
  templateUrl: './employee-archive.component.html',
  styleUrls: ['./employee-archive.component.css']
})
export class EmployeeArchiveComponent implements OnInit {

  employees: ArchivedEmployee[];
  history: ArchivedHistory[];

  oldId: number;
  tempId: number;
  type: number = null;
  vis2: boolean = false;
  historyId: number;

  id: number;
  firstName: string;
  lastName: string;
  bankAccount: number;
  position: string;
  salary: number;
  hiringDate: Date;
  terminationDate: Date;
  latestDateOfTransfer: Date;

  constructor(private router: Router, private service: ArchiveControllerService) { }

  ngOnInit(): void {
    this.service.recieveAllArchivedEmployees(sessionStorage.key(0)).subscribe(
      allEmployees => this.employees = allEmployees,
      err => {
        console.log(err.error);
        alert("your session token has expired!");
        this.router.navigateByUrl("/login");
      }
    );
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

  public deleteEmployee(id: number) {
    this.service.deleteEmployee(id, sessionStorage.key(0)).subscribe(
      empId => {
        alert("Employee have been deleted!");
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


  public openEdit(emp: ArchivedEmployee) {
    this.historyId = null;
    this.vis2 = false;
    this.id = emp.id;
    this.oldId = this.id;
    this.tempId = this.id;
    this.hiringDate = emp.hiringDate;
    this.firstName = emp.firstName;
    this.lastName = emp.lastName;
    this.bankAccount = emp.bankAccount;
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
          console.log(emp.firstName + " " + emp.lastName + " " + emp.department);
          this.reloadCurrentRoute();
        },
        err => alert(err.error)
      );
    }
  }

  reloadCurrentRoute() {
    let currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
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
    this.terminationDate = null;
    this.position = null;
    this.type = null;
    this.oldId = null;
    this.tempId = null;
    this.vis2 = false;
  }

  public Cancel() {
    this.empty();
  }

}
