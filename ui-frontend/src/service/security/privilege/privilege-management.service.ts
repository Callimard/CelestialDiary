import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {backend} from "../../../environments/environment";
import {ScopeDTO} from "../../../data/security/privilege/scope-dto";

@Injectable({
  providedIn: 'root'
})
export class PrivilegeManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  private static companyPrivilegesUrl(): string {
    return backend.backendUrl + backend.apiV1Url + backend.privilegesUrl + backend.company;
  }

  private static internEstablishmentPrivilegesUrl(): string {
    return backend.backendUrl + backend.apiV1Url + backend.privilegesUrl + backend.establishment;
  }

  public companyManagementScope(): Promise<ScopeDTO> {
    return new Promise<ScopeDTO>(((resolve, reject) => {
      this.http.get<ScopeDTO>(PrivilegeManagementService.companyPrivilegesUrl()).subscribe({
        next: (companyManagementScope) => {
          resolve(companyManagementScope);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public internEstablishmentManagementScope(): Promise<ScopeDTO> {
    return new Promise<ScopeDTO>(((resolve, reject) => {
      this.http.get<ScopeDTO>(PrivilegeManagementService.internEstablishmentPrivilegesUrl()).subscribe({
        next: (internEstablishmentManagementScope) => {
          resolve(internEstablishmentManagementScope);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }
}
