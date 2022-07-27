import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {WrappedEstablishmentDTO} from "../../../data/company-management/establishment/wrapped-establishment-dto";
import {JwtAccount} from "../../authentication/jwt-account";
import {AuthenticationService} from "../../authentication/authentication.service";
import {backend} from "../../../environments/environment";
import {EstablishmentDTO} from "../../../data/company-management/establishment/establishment-dto";
import {EstablishmentCreationInformation} from "../../../data/company-management/establishment/establishment-creation-information";
import {EstablishmentUpdatedInformation} from "../../../data/company-management/establishment/establishment-updated-information";

@Injectable({
  providedIn: 'root'
})
export class EstablishmentManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  private static companyEstablishmentUrl(companyId: string): string {
    return backend.backendUrl + backend.apiV1Url + backend.companiesUrl + '/' + companyId + backend.establishmentsUrl
  }

  private static companySpecificEstablishmentUrl(companyId: string, establishmentId: string): string {
    return EstablishmentManagementService.companyEstablishmentUrl(companyId) + '/' + establishmentId;
  }

  public allEstablishments(): Promise<WrappedEstablishmentDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEstablishmentDTO[]>(((resolve, reject) => {
      this.http.get<WrappedEstablishmentDTO[]>(EstablishmentManagementService.companyEstablishmentUrl(jwtAccount.companyId)).subscribe({
        next: (allEstablishments) => {
          resolve(allEstablishments);
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err);
        }
      })
    }));
  }

  public searchEstablishment(filter: string): Promise<WrappedEstablishmentDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEstablishmentDTO[]>(((resolve, reject) => {
      this.http.get<WrappedEstablishmentDTO[]>(EstablishmentManagementService.companyEstablishmentUrl(jwtAccount.companyId) + '?filter=' + filter).subscribe({
        next: (allEstablishments) => {
          resolve(allEstablishments);
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err);
        }
      })
    }))
  }

  public getSpecificEstablishment(establishmentId: string): Promise<EstablishmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<EstablishmentDTO>(((resolve, reject) => {
      this.http.get<EstablishmentDTO>(EstablishmentManagementService.companySpecificEstablishmentUrl(jwtAccount.companyId, establishmentId))
        .subscribe({
          next: (establishment) => {
            resolve(establishment);
          },
          error: (err: HttpErrorResponse) => {
            console.error(err.error);
            reject(err.error);
          }
        })
    }))
  }

  public createEstablishment(establishmentInfo: EstablishmentCreationInformation): Promise<WrappedEstablishmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEstablishmentDTO>(((resolve, reject) => {
      this.http.post<WrappedEstablishmentDTO>(EstablishmentManagementService.companyEstablishmentUrl(jwtAccount.companyId), establishmentInfo)
        .subscribe({
          next: (establishment) => {
            resolve(establishment);
          },
          error: (err: HttpErrorResponse) => {
            console.error(err.error);
            reject(err.error)
          }
        })
    }));
  }

  public updateEstablishment(establishmentId: string, establishmentUpdates: EstablishmentUpdatedInformation): Promise<WrappedEstablishmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedEstablishmentDTO>(((resolve, reject) => {
      this.http.put<WrappedEstablishmentDTO>(EstablishmentManagementService.companySpecificEstablishmentUrl(jwtAccount.companyId, establishmentId), establishmentUpdates)
        .subscribe({
          next: (establishment) => {
            resolve(establishment);
          },
          error: (err: HttpErrorResponse) => {
            console.error(err.error);
            reject(err.error)
          }
        })
    }));
  }
}
