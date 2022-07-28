import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../service/authentication/authentication.service";
import {JwtAccount} from "../../service/authentication/jwt-account";
import {environment} from "../../environments/environment";
import {PrivilegeService} from "../../service/authentication/privilege.service";

@Component({
  selector: '[app-application]',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css']
})
export class ApplicationComponent implements OnInit {

  navigationBarClosed = window.innerWidth <= environment.bigScreenWidth;

  jwtAccount: JwtAccount;

  selectedNavItem: string = "";

  constructor(private authenticationService: AuthenticationService, private privilegeService: PrivilegeService) {
    this.jwtAccount = AuthenticationService.getJwtAccount();
  }

  ngOnInit(): void {
    this.authenticationService.checkLogin();
  }

  closeNavigationBar() {
    this.navigationBarClosed = true;
  }

  openNavigationBar() {
    this.navigationBarClosed = false;
  }

  logoutClick() {
    this.authenticationService.employeeLogout();
  }

  navClick(navItemTitle: string) {
    if (ApplicationComponent.navigationBarMustClosed() && !this.navigationBarClosed) {
      this.navigationBarClosed = true;
    }
    this.selectedNavItem = navItemTitle;
  }

  private static navigationBarMustClosed(): boolean {
    return window.innerWidth <= environment.bigScreenWidth;
  }

  isLittleScreen(): boolean {
    return window.innerWidth <= environment.bigScreenWidth;
  }

  isBigScreen(): boolean {
    return !this.isLittleScreen();
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
