import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AutherizedAdmin } from 'src/app/models/autherized-admin';
import { MainControllerService } from 'src/app/services/main-controller.service';

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.css']
})
export class AdminsComponent implements OnInit {

  user: string = null;
  pass: string = null;
  admins: AutherizedAdmin[];

  constructor(private router: Router, private service: MainControllerService) { }

  ngOnInit(): void {
    this.service.recieveAllAdmins(sessionStorage.key(0)).subscribe(
      allAdmins => this.admins = allAdmins,
      err => {
        alert("your session token has expired!");
        this.router.navigateByUrl("/login");
      }
    );
  }

  public addAdmin() {
    if (this.user == null)
      alert("please enter user name!")
    else if (this.pass == null)
      alert("please enter a password!")
    else {
      let newUser = new AutherizedAdmin(0, this.user, this.pass);
      this.service.postAdmin(sessionStorage.key(0), newUser).subscribe(
        admin1 => {
          alert("User " + admin1.user + " added!");
          this.admins.splice(this.admins.length, 0,admin1);
          this.user=null;
          this.pass=null;
        },
        err => alert(err.error)
      );
    }
  }

  public employees() {
    this.router.navigateByUrl('/employees');
  }

  public delete1Admin(id: number) {
    this.service.deleteAdmin(id, sessionStorage.key(0)).subscribe(
      () => {
        let index = -1;
        for (let i = 0; i < this.admins.length; i++) {
          if (this.admins[i].id == id) {
            index = i;
            break;
          }
        }
        if (index > -1)
          this.admins.splice(index, 1); // deletes one company from index position in companies array
      },
      err => {
        alert("your session token has expired!");
      }
    );
  }

}
