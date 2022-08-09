import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../service/authentication/authentication.service";
import {JwtAccount} from "../../service/authentication/jwt-account";
import {environment} from "../../environments/environment";

@Component({
  selector: '[app-application]',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css']
})
export class ApplicationComponent implements OnInit {

  navigationBarClosed = window.innerWidth <= environment.bigScreenWidth;
  selectedNavItem: string = "";

  jwtAccount: JwtAccount;

  constructor() {
    this.jwtAccount = AuthenticationService.getJwtAccount();
  }

  ngOnInit(): void {
    // Nothing
  }

  closeNavigationBar() {
    this.navigationBarClosed = true;
  }

  openNavigationBar() {
    this.navigationBarClosed = false;
  }

  onNavClick(navItemTitle: string) {
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
}
