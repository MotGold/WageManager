import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-layed-off-employees',
  templateUrl: './layed-off-employees.component.html',
  styleUrls: ['./layed-off-employees.component.css']
})
export class LayedOffEmployeesComponent implements OnInit {

  constructor(private router: Router) { }

  id: number;
  first: string;
  last: string;
  type: number = null;
  check: boolean = false;
  check2: boolean = false;
  check3: boolean = false;

  ngOnInit(): void {
    this.router.navigateByUrl('old/archive')
  }

  public employees() {
    this.router.navigateByUrl('employees/list');
  }

  reloadCurrentRoute() {
    let currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
  }

  public findById() {
    if (this.check) {
      this.check = false;
      this.router.navigateByUrl('old/byId2', { state: { id: this.id } });
    } else if (this.check == false) {
      this.check = true;
      this.router.navigateByUrl('old/byId', { state: { id: this.id } });
    }
    this.id = null;
  }

  public findByDepartment() {
    if (this.check3==true) {
      this.check3 = false;
      this.router.navigateByUrl('old/bydepartment2', { state: { type: this.type } });
    } else if (this.check3 == false) {
      this.check3 = true;
      this.router.navigateByUrl('old/bydepartment', { state: { type: this.type } });
    }
    this.type = null;
  }

  public findByname() {
    if (this.check2) {
      this.check2 = false;
      this.router.navigateByUrl('old/byName2', { state: { first: this.first, last: this.last } });
    } else if (this.check == false) {
      this.check2 = true;
      this.router.navigateByUrl('old/byName', { state: { first: this.first, last: this.last } });
    }
    this.first = null;
    this.last = null;
  }

  public logOut() {
    sessionStorage.clear();
    this.router.navigateByUrl('/login');
  }

}