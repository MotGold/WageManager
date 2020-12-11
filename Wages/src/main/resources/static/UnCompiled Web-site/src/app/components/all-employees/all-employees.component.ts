import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MainControllerService } from 'src/app/services/main-controller.service';

@Component({
  selector: 'app-all-employees',
  templateUrl: './all-employees.component.html',
  styleUrls: ['./all-employees.component.css']
})
export class AllEmployeesComponent implements OnInit {

  constructor(private router: Router, private service: MainControllerService) { }

  id: number;
  first: string;
  last: string;
  type: number = null;
  check: boolean = false;
  check2: boolean = false;
  check3: boolean = false;

  ngOnInit(): void {
    this.router.navigateByUrl('employees/list');
  }

  reloadCurrentRoute() {
    let currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
  }

  public admins() {
    this.router.navigateByUrl('/admins');
  }

  public findById() {
    if (this.check) {
      this.check = false;
      this.router.navigateByUrl('employees/byId2', { state: { id: this.id } });
    } else if (this.check == false) {
      this.check = true;
      this.router.navigateByUrl('employees/byId', { state: { id: this.id } });
    }
    this.id = null;
  }

  public findByDepartment() {
    if (this.check3 == true) {
      this.check3 = false;
      this.router.navigateByUrl('employees/bydepartment2', { state: { type: this.type } });
    } else if (this.check3 == false) {
      this.check3 = true;
      this.router.navigateByUrl('employees/bydepartment', { state: { type: this.type } });
    }
    this.type = null;
  }

  public findByname() {
    if (this.check2) {
      this.check2 = false;
      this.router.navigateByUrl('employees/byName2', { state: { first: this.first, last: this.last } });
    } else if (this.check == false) {
      this.check2 = true;
      this.router.navigateByUrl('employees/byName', { state: { first: this.first, last: this.last } });
    }
    this.first = null;
    this.last = null;
  }

  public old() {
    this.router.navigateByUrl('/old');
  }


  public payAll() {
    this.service.payAllEmpolyees(sessionStorage.key(0)).subscribe(
      msg => {
        alert(msg);
        this.reloadCurrentRoute();
      },
      err => alert(err.error)
    );
  }

  public logOut() {
    sessionStorage.clear();
    this.router.navigateByUrl('/login');
  }

}

