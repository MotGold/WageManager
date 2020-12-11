import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginManagerService } from 'src/app/services/login-manager.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: string;
  pass: string;

  constructor(private router: Router, private service: LoginManagerService) { }

  ngOnInit(): void {
  }

  public login() {
    this.service.connectToServer(this.user, this.pass).subscribe(
      (serverOutput) => {
        sessionStorage.clear();
        sessionStorage.setItem(serverOutput.token, JSON.stringify(serverOutput.currentTime));
        this.router.navigateByUrl('/employees');
      },
      (err) => alert("Wrong input entered")//console.log(err)
    );
  }

}
