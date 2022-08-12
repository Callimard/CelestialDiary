import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {backend} from "../../../../environments/environment";
import {RoleDTO} from "../../../../data/model/person/employee/role/role-dto";
import {JwtAccount} from "../../../authentication/jwt-account";
import {AuthenticationService} from "../../../authentication/authentication.service";
import {RoleCreationInformation} from "../../../../data/model/person/employee/role/role-creation-information";
import {RoleUpdatedInformation} from "../../../../data/model/person/employee/role/role-updated-information";

@Injectable({
  providedIn: 'root'
})
export class RoleManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  private static companyRoleUrl(companyId: string): string {
    return backend.backendUrl + backend.apiV1Url + backend.companiesUrl + '/' + companyId + backend.rolesUrl;
  }

  private static companySpecificRoleUrl(companyId: string, roleId: string): string {
    return RoleManagementService.companyRoleUrl(companyId) + '/' + roleId;
  }

  public allRoles(): Promise<RoleDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<RoleDTO[]>(((resolve, reject) => {
      this.http.get<RoleDTO[]>(RoleManagementService.companyRoleUrl(jwtAccount.companyId)).subscribe({
        next: (allRoles) => {
          resolve(allRoles);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public searchRole(filter: string): Promise<RoleDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<RoleDTO[]>(((resolve, reject) => {
      this.http.get<RoleDTO[]>(RoleManagementService.companyRoleUrl(jwtAccount.companyId) + '?filter=' + filter).subscribe({
        next: (allRoles) => {
          resolve(allRoles);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public getSpecificRole(roleId: string): Promise<RoleDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<RoleDTO>(((resolve, reject) => {
      this.http.get<RoleDTO>(RoleManagementService.companySpecificRoleUrl(jwtAccount.companyId, roleId)).subscribe({
        next: (role) => {
          resolve(role);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public createRole(roleInformation: RoleCreationInformation): Promise<RoleDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<RoleDTO>(((resolve, reject) => {
      this.http.post<RoleDTO>(RoleManagementService.companyRoleUrl(jwtAccount.companyId), roleInformation).subscribe({
        next: (role) => {
          resolve(role);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public updateRole(roleId: string, roleUpdates: RoleUpdatedInformation): Promise<RoleDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<RoleDTO>(((resolve, reject) => {
      this.http.put<RoleDTO>(RoleManagementService.companySpecificRoleUrl(jwtAccount.companyId, roleId), roleUpdates).subscribe({
        next: (role) => {
          resolve(role);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public deleteRole(roleId: string): Promise<boolean> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise(((resolve, reject) => {
      this.http.delete(RoleManagementService.companySpecificRoleUrl(jwtAccount.companyId, roleId)).subscribe({
        next: () => {
          resolve(true);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }
}
