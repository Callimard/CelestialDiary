import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {PrivilegeDTO} from "../../../../../data/company-management/employee/privilege-dto";
import {backend} from "../../../../../environments/environment";

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

  public allCompanyManagementPrivileges(): Promise<PrivilegeDTO[]> {
    return new Promise<PrivilegeDTO[]>(((resolve, reject) => {
      this.http.get<PrivilegeDTO[]>(PrivilegeManagementService.companyPrivilegesUrl()).subscribe({
        next: (allPrivileges) => {
          resolve(allPrivileges);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }
}
