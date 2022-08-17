import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {WrappedEmployeeDTO} from "../../../data/model/person/employee/wrapped-employee-dto";
import {backend} from "../../../environments/environment";
import {JwtAccount} from "../../authentication/jwt-account";
import {AuthenticationService} from "../../authentication/authentication.service";
import {EmployeeCreationInformation} from "../../../data/model/person/employee/employee-creation-information";
import {EmployeeDTO} from "../../../data/model/person/employee/employee-dto";
import {EmployeeUpdatedInformation} from "../../../data/model/person/employee/employee-updated-information";
import {EmployeeUpdatedRoles} from "../../../data/model/person/employee/employee-updated-roles";
import {EmployeeEstablishmentInformation} from "../../../data/model/person/employee/employee-establishment-information";
import {WrappedEmployeeWorkingHoursDTO} from "../../../data/model/person/employee/working-hours/wrapped-employee-working-hours-dto";
import {WorkingHoursInformation} from "../../../data/model/person/employee/working-hours/working-hours-information";

@Injectable({
  providedIn: 'root'
})
export class EmployeeManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  public static companyEmployeeUrl(companyId: string): string {
    return backend.backendUrl + backend.apiV1Url + backend.companiesUrl + '/' + companyId + backend.employeesUrl
  }

  private static companySpecificEmployeeUrl(companyId: string, employeeId: string): string {
    return EmployeeManagementService.companyEmployeeUrl(companyId) + '/' + employeeId;
  }

  private static companyUpdateEmployeeRoleUrl(companyId: string, employeeId: string): string {
    return EmployeeManagementService.companySpecificEmployeeUrl(companyId, employeeId) + '/roles';
  }

  private static companyAssignEmployeeToEstablishmentUrl(companyId: string, employeeId: string): string {
    return EmployeeManagementService.companySpecificEmployeeUrl(companyId, employeeId) + '/assignation';
  }

  private static companyEmployeeWorkingHoursUrl(companyId: string, employeeId: string, establishmentId: string): string {
    return EmployeeManagementService.companySpecificEmployeeUrl(companyId, employeeId) + '/working-hours/' + establishmentId;
  }

  public allEmployees(): Promise<WrappedEmployeeDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEmployeeDTO[]>(((resolve, reject) => {
      this.http.get<WrappedEmployeeDTO[]>(EmployeeManagementService.companyEmployeeUrl(jwtAccount.companyId))
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

  public searchEmployee(filter: string): Promise<WrappedEmployeeDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEmployeeDTO[]>(((resolve, reject) => {
      this.http.get<WrappedEmployeeDTO[]>(EmployeeManagementService.companyEmployeeUrl(jwtAccount.companyId) + "?filter=" + filter)
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
      this.http.get<EmployeeDTO>(EmployeeManagementService.companySpecificEmployeeUrl(jwtAccount.companyId, employeeId))
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
      this.http.post<WrappedEmployeeDTO>(EmployeeManagementService.companyEmployeeUrl(jwtAccount.companyId), employeeInfo).subscribe({
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

  public updateEmployee(employeeId: string, employeeUpdateInfo: EmployeeUpdatedInformation): Promise<EmployeeDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<EmployeeDTO>(((resolve, reject) => {
      this.http.put<EmployeeDTO>(EmployeeManagementService.companySpecificEmployeeUrl(jwtAccount.companyId, employeeId), employeeUpdateInfo).subscribe({
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

  public getEmployeeWorkingHours(employeeId: string, establishmentId: string, year: number, weekNumber: number): Promise<WrappedEmployeeWorkingHoursDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEmployeeWorkingHoursDTO>(((resolve, reject) => {
      this.http.get<WrappedEmployeeWorkingHoursDTO>(EmployeeManagementService.companyEmployeeWorkingHoursUrl(jwtAccount.companyId, employeeId, establishmentId)
        + '?year=' + year + '&weekNumber=' + weekNumber).subscribe({
        next: (employeeWorkingHours) => {
          resolve(employeeWorkingHours);
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public updateEmployeeWorkingHours(employeeId: string, establishmentId: string, updates: WorkingHoursInformation): Promise<WrappedEmployeeWorkingHoursDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEmployeeWorkingHoursDTO>(((resolve, reject) => {
      this.http.put<WrappedEmployeeWorkingHoursDTO>(EmployeeManagementService.companyEmployeeWorkingHoursUrl(jwtAccount.companyId, employeeId, establishmentId), updates).subscribe({
        next: (employeeWorkingHours) => {
          resolve(employeeWorkingHours);
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public updateEmployeeRoles(employeeId: string, employeeRoleUpdates: EmployeeUpdatedRoles): Promise<EmployeeDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<EmployeeDTO>(((resolve, reject) => {
      this.http.put<EmployeeDTO>(EmployeeManagementService.companyUpdateEmployeeRoleUrl(jwtAccount.companyId, employeeId), employeeRoleUpdates).subscribe({
        next: (employee) => {
          resolve(employee);
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public updateEmployeeEstablishmentAssignation(employeeId: string, employeeEstablishmentInformation: EmployeeEstablishmentInformation): Promise<EmployeeDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<EmployeeDTO>(((resolve, reject) => {
      this.http.put<EmployeeDTO>(EmployeeManagementService.companyAssignEmployeeToEstablishmentUrl(jwtAccount.companyId, employeeId), employeeEstablishmentInformation)
        .subscribe({
          next: (employee) => {
            resolve(employee);
          },
          error: (err: HttpErrorResponse) => {
            console.error(err.error);
            reject(err.error);
          }
        })
    }))
  }

}
