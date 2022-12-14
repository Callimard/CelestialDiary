import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {backend} from "../../../environments/environment";
import {WrappedPrestationDTO} from "../../../data/model/saleable/prestation/wrapped-prestation-dto";
import {JwtAccount} from "../../authentication/jwt-account";
import {AuthenticationService} from "../../authentication/authentication.service";
import {PrestationDTO} from "../../../data/model/saleable/prestation/prestation-dto";
import {PrestationCreationInformation} from "../../../data/model/saleable/prestation/prestation-creation-information";
import {PrestationUpdatedInformation} from "../../../data/model/saleable/prestation/prestation-updated-information";

@Injectable({
  providedIn: 'root'
})
export class PrestationManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  private static companyPrestationUrl(companyId: string): string {
    return backend.backendUrl + backend.apiV1Url + backend.companiesUrl + '/' + companyId + backend.prestationsUrl;
  }

  private static companySpecificPrestationUrl(companyId: string, prestationId: string): string {
    return PrestationManagementService.companyPrestationUrl(companyId) + '/' + prestationId;
  }

  public allPrestations(): Promise<WrappedPrestationDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedPrestationDTO[]>(((resolve, reject) => {
      this.http.get<WrappedPrestationDTO[]>(PrestationManagementService.companyPrestationUrl(jwtAccount.companyId)).subscribe({
        next: (allPrestations) => {
          resolve(allPrestations);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public searchPrestation(filter: string): Promise<WrappedPrestationDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedPrestationDTO[]>(((resolve, reject) => {
      this.http.get<WrappedPrestationDTO[]>(PrestationManagementService.companyPrestationUrl(jwtAccount.companyId) + "?filter=" + filter).subscribe({
        next: (allPrestations) => {
          resolve(allPrestations);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public getSpecificPrestation(prestationId: string): Promise<PrestationDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<PrestationDTO>(((resolve, reject) => {
      this.http.get<PrestationDTO>(PrestationManagementService.companySpecificPrestationUrl(jwtAccount.companyId, prestationId)).subscribe({
        next: (prestation) => {
          resolve(prestation);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public createPrestation(prestationInformation: PrestationCreationInformation): Promise<WrappedPrestationDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedPrestationDTO>(((resolve, reject) => {
      this.http.post<WrappedPrestationDTO>(PrestationManagementService.companyPrestationUrl(jwtAccount.companyId), prestationInformation).subscribe({
        next: (prestation) => {
          resolve(prestation);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public updatePrestation(prestationId: string, prestationUpdates: PrestationUpdatedInformation): Promise<WrappedPrestationDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedPrestationDTO>(((resolve, reject) => {
      this.http.put<WrappedPrestationDTO>(PrestationManagementService.companySpecificPrestationUrl(jwtAccount.companyId, prestationId), prestationUpdates).subscribe({
        next: (prestation) => {
          resolve(prestation);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }
}
