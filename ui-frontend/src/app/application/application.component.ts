import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../service/authentication/authentication.service";
import {JwtAccount} from "../../service/authentication/jwt-account";

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css']
})
export class ApplicationComponent implements OnInit {

  wrapped = false;

  jwtAccount: JwtAccount;

  constructor(private authenticationService: AuthenticationService) {
    this.jwtAccount = AuthenticationService.getJwtAccount();
  }

  ngOnInit(): void {
    this.authenticationService.checkLogin();
  }

  logoutClick() {
    this.authenticationService.employeeLogout();
  }

  wrapSlidBar() {
    this.wrapped = !this.wrapped;
  }
}
