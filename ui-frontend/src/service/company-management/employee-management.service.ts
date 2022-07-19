import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {WrappedEmployeeDTO} from "../../data/company-management/employee/wrapped-employee-dto";
import {environment} from "../../environments/environment";
import {JwtAccount} from "../authentication/jwt-account";
import {AuthenticationService} from "../authentication/authentication.service";
import {EmployeeCreationInformation} from "../../data/company-management/employee/employee-creation-information";
import {EmployeeDTO} from "../../data/company-management/employee/employee-dto";
import {EmployeeUpdatedInformation} from "../../data/company-management/employee/employee-updated-information";

@Injectable({
  providedIn: 'root'
})
export class EmployeeManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  public allEmployees(): Promise<WrappedEmployeeDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEmployeeDTO[]>(((resolve, reject) => {
      this.http.get<WrappedEmployeeDTO[]>(environment.backendUrl
        + environment.apiV1Url + environment.companiesUrl + '/' + jwtAccount.companyId + environment.employeesUrl)
        .subscribe({
          next: (allEmployees) => {
            resolve(allEmployees);
          },
          error: (err: HttpErrorResponse) => {
            console.error("Fail to get all employees", err.error);
            reject(err.error);
          }
        });
    }))
  }

  public searchEmployee(filter: string): Promise<WrappedEmployeeDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEmployeeDTO[]>(((resolve, reject) => {
      this.http.get<WrappedEmployeeDTO[]>(environment.backendUrl
        + environment.apiV1Url + environment.companiesUrl + '/' + jwtAccount.companyId + environment.employeesUrl + "?filter=" + filter)
        .subscribe({
          next: (allEmployees) => {
            resolve(allEmployees);
          },
          error: (err: HttpErrorResponse) => {
            console.error(err.error);
            reject(err.error);
          }
        });
    }))
  }

  public getSpecificEmployee(employeeId: string): Promise<EmployeeDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<EmployeeDTO>(((resolve, reject) => {
      this.http.get<EmployeeDTO>(environment.backendUrl
        + environment.apiV1Url + environment.companiesUrl + '/' + jwtAccount.companyId + environment.employeesUrl + '/' + employeeId)
        .subscribe({
          next: (employee) => {
            resolve(employee);
          },
          error: (err: HttpErrorResponse) => {
            console.error(err.error);
            reject(err.error);
          }
        });
    }))
  }

  public createEmployee(employeeInfo: EmployeeCreationInformation): Promise<WrappedEmployeeDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEmployeeDTO>(((resolve, reject) => {
      this.http.post<WrappedEmployeeDTO>(environment.backendUrl + environment.apiV1Url + environment.companiesUrl
        + '/' + jwtAccount.companyId + environment.employeesUrl, employeeInfo).subscribe({
        next: (employeeCreated) => {
          resolve(employeeCreated)
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public updateEmployee(employeeId: string, employeeUpdateInfo: EmployeeUpdatedInformation): Promise<WrappedEmployeeDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEmployeeDTO>(((resolve, reject) => {
      this.http.put<WrappedEmployeeDTO>(environment.backendUrl + environment.apiV1Url + environment.companiesUrl
        + '/' + jwtAccount.companyId + environment.employeesUrl + '/' + employeeId, employeeUpdateInfo).subscribe({
        next: (employeeUpdated) => {
          resolve(employeeUpdated)
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err.error);
        }
      })
    }))
  }

}
