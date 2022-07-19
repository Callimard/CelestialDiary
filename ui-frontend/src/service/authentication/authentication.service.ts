import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {EmployeeBasicCredential} from "./employee-basic-credential";
import {JwtTokenResponse} from "../../data/authentication/jwt/jwt-token-response";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";
import {JwtAccount} from "./jwt-account";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public static readonly JWT_TOKEN_KEY = "jwt_token";
  public static readonly LAST_COMPANY_NAME_USED = "last_company_name_used";
  public static readonly LAST_EMPLOYEE_EMAIL_USED = "last_employee_email_used";

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
    this.router.navigate([environment.frontLoginPage]).catch((reason => {
      console.error("Fail to redirect to login page", reason);
    }));
  }

  public employeeLogin(companyName: string, employeeEmail: string, employeePassword: string): Promise<any> {
    const employeeBasicCredential = new EmployeeBasicCredential(environment.employeeAuthenticationIdentifier + companyName, employeeEmail, employeePassword);

    return new Promise<boolean>((resolve, reject) => {
      this.httpClient.post<JwtTokenResponse>(environment.backendUrl + environment.apiV1Url + environment.employeeLogin, null, {
        headers: {'Authorization': employeeBasicCredential.toBasicCredentials()}
      }).subscribe({
        next: (token) => {
          localStorage.setItem(AuthenticationService.LAST_COMPANY_NAME_USED, companyName);
          localStorage.setItem(AuthenticationService.LAST_EMPLOYEE_EMAIL_USED, employeeEmail);
          localStorage.setItem(AuthenticationService.JWT_TOKEN_KEY, token.jwt);
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

  public static isAuthenticated(): boolean {
    const jwtToken: string | null = AuthenticationService.getCurrentJwtToken();
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

  public static getJwtAccount(): any {
    const jwtToken: string | null = AuthenticationService.getCurrentJwtToken();
    if (jwtToken == null) {
      return null;
    } else {
      return AuthenticationService.extractJwtTokenAccount(jwtToken);
    }
  }

  private static extractJwtTokenAccount(jwt: string): JwtAccount {
    let decodedJWT = AuthenticationService.JWT_HELPER.decodeToken(jwt);
    return JSON.parse(decodedJWT.account);
  }

  public static getCurrentJwtToken(): string | null {
    return localStorage.getItem(AuthenticationService.JWT_TOKEN_KEY)
  }

  public static lastCompanyNameUsed(): string {
    const companyName: string | null = localStorage.getItem(AuthenticationService.LAST_COMPANY_NAME_USED);
    return companyName != null ? companyName : '';
  }

  public static lastEmployeeEmailUsed(): string {
    const employeeEmail: string | null = localStorage.getItem(AuthenticationService.LAST_EMPLOYEE_EMAIL_USED);
    return employeeEmail != null ? employeeEmail : '';
  }
}
