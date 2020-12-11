import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArchivedEmployee } from 'src/app/models/archived-employee';
import { ArchivedHistory } from 'src/app/models/archived-history';
import { ArchiveControllerService } from 'src/app/services/archive-controller.service';

@Component({
  selector: 'app-archive-by-id',
  templateUrl: './archive-by-id.component.html',
  styleUrls: ['./archive-by-id.component.css']
})
export class ArchiveByIdComponent implements OnInit {


  emp: ArchivedEmployee;
  vis2: boolean = true;
  oldId: number;
  tempId: number;
  historyId: number;
  history: ArchivedHistory[];

  type: number = null;
  id: number;
  firstName: string;
  lastName: string;
  bankAccount: number;
  salary: number;
  position: string;

  hiringDate: Date;
  terminationDate: Date;
  latestDateOfTransfer: Date;

  constructor(private router: Router, private service: ArchiveControllerService) { }

  ngOnInit(): void {
    this.id = history.state.id;
    if (this.id != null) {
      this.service.findEmployee(this.id, sessionStorage.key(0)).subscribe(
        emp => this.emp = emp,
        err => {
          alert(err.error);
          this.router.navigateByUrl('old/archive');
        }
      );
    } else
      this.router.navigateByUrl('old/archive');
  }

  public back() {
    this.router.navigateByUrl('old/archive');
  }

  public fire1employee(id: number) {
    this.service.deleteEmployee(id, sessionStorage.key(0)).subscribe(
      empId => {
        alert("Employee " + empId + " have been fired!");
        this.router.navigateByUrl('old/archive');
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
      let editedEmployee = new ArchivedEmployee(this.tempId, this.firstName, this.lastName, this.hiringDate, this.terminationDate, this.bankAccount, this.salary, this.latestDateOfTransfer, null, this.position);
      this.service.editArchivedEmployee(this.oldId, this.type, sessionStorage.key(0), editedEmployee).subscribe(
        emp => {
          alert("Employee " + emp.id + " have been edited!");
          this.id = emp.id;
          this.service.findEmployee(this.id, sessionStorage.key(0)).subscribe(
            emp => this.emp = emp,
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
    this.service.historyOfArchivedEmployee(this.id, sessionStorage.key(0)).subscribe(
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
