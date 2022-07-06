import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {EmployeeBasicCredential} from "./employee-basic-credential";
import {JwtTokenResponse} from "../../data/authentication/jwt/jwt-token-response";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public static readonly JWT_TOKEN_KEY = "jwt_token";

  private static readonly JWT_HELPER = new JwtHelperService();

  constructor(private httpClient: HttpClient, private router: Router) {
    // Nothing
  }

  public checkLogin() {
    if (!AuthenticationService.isAuthenticated()) {
      this.redirectToLoginPage();
    }
  }

  private redirectToLoginPage() {
    this.router.navigate([environment.frontLoginPage], {skipLocationChange: true}).catch((reason => {
      console.error("Fail to redirect to login page", reason);
    }));
  }

  public employeeLogin(companyName: string, employeeEmail: string, employeePassword: string): Promise<any> {
    const employeeBasicCredential = new EmployeeBasicCredential(companyName, employeeEmail, employeePassword);

    return new Promise<boolean>((resolve, reject) => {
      this.httpClient.post<JwtTokenResponse>(environment.backend + environment.apiV1 + environment.employeeLoginUrl, null, {
        headers: {'Authorization': employeeBasicCredential.toBasicCredentials()}
      }).subscribe({
        next: (token) => {
          AuthenticationService.saveJwtToken(token.jwt);
          resolve(true);
        },
        error: (err: HttpErrorResponse) => {
          console.error("Login Fail", err.error);
          reject(false);
        }
      })
    });
  }

  public employeeLogout() {
    localStorage.removeItem(AuthenticationService.JWT_TOKEN_KEY);
    this.redirectToLoginPage();
  }

  private static saveJwtToken(jwt: string) {
    localStorage.setItem(AuthenticationService.JWT_TOKEN_KEY, jwt);
  }

  public static isAuthenticated(): boolean {
    const jwtToken: string | null = localStorage.getItem(AuthenticationService.JWT_TOKEN_KEY);
    if (jwtToken == null) {
      return false;
    } else {
      try {
        return !AuthenticationService.JWT_HELPER.isTokenExpired(jwtToken)
      } catch (e) {
        return false;
      }
    }
  }
}
