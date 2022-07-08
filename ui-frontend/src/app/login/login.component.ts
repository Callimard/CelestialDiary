import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../service/authentication/authentication.service";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authenticationFail = false;

  loginForm = new FormGroup({
    company: new FormControl(AuthenticationService.lastCompanyNameUsed(), Validators.required),
    email: new FormControl(AuthenticationService.lastEmployeeEmailUsed(), Validators.required),
    password: new FormControl('', Validators.required)
  });

  constructor(private authService: AuthenticationService, private router: Router) {
    // Nothing
  }

  ngOnInit(): void {
    if (AuthenticationService.isAuthenticated()) {
      this.navigateToApplication();
    }
  }

  onLogin() {
    const form = this.loginForm.value;
    this.authService.employeeLogin(form.company, form.email, form.password).then(() => {
      this.authenticationFail = false;
      this.navigateToApplication();
    }).catch(() => {
      this.authenticationFail = true;
    });
  }

  private navigateToApplication() {
    this.router.navigate([environment.frontApplicationPage]).catch((reason => {
      console.error("Fail to navigate to Application page", reason);
    }));
  }
}
