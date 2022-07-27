import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {backend} from "../../../environments/environment";
import {WrappedBundleDTO} from "../../../data/company-management/saleable/bundle/wrapped-bundle-dto";
import {JwtAccount} from "../../authentication/jwt-account";
import {AuthenticationService} from "../../authentication/authentication.service";
import {BundleDTO} from "../../../data/company-management/saleable/bundle/bundle-dto";
import {BundleCreationInformation} from "../../../data/company-management/saleable/bundle/bundle-creation-information";
import {BundleUpdatedInformation} from "../../../data/company-management/saleable/bundle/bundle-updated-information";

@Injectable({
  providedIn: 'root'
})
export class BundleManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  private static companyBundleUrl(companyId: string): string {
    return backend.backendUrl + backend.apiV1Url + backend.companiesUrl + '/' + companyId + backend.bundlesUrl;
  }

  private static companySpecificBundleUrl(companyId: string, bundleId: string): string {
    return BundleManagementService.companyBundleUrl(companyId) + '/' + bundleId;
  }

  public allBundles(): Promise<WrappedBundleDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedBundleDTO[]>(((resolve, reject) => {
      this.http.get<WrappedBundleDTO[]>(BundleManagementService.companyBundleUrl(jwtAccount.companyId)).subscribe({
        next: (allBundles) => {
          resolve(allBundles);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public searchBundle(filter: string): Promise<WrappedBundleDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedBundleDTO[]>(((resolve, reject) => {
      this.http.get<WrappedBundleDTO[]>(BundleManagementService.companyBundleUrl(jwtAccount.companyId) + '?filter=' + filter).subscribe({
        next: (allBundles) => {
          resolve(allBundles);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public getSpecificBundle(bundleId: string): Promise<BundleDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<BundleDTO>(((resolve, reject) => {
      this.http.get<BundleDTO>(BundleManagementService.companySpecificBundleUrl(jwtAccount.companyId, bundleId)).subscribe({
        next: (bundle) => {
          resolve(bundle);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public createBundle(bundleInformation: BundleCreationInformation): Promise<WrappedBundleDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<WrappedBundleDTO>(((resolve, reject) => {
      this.http.post<WrappedBundleDTO>(BundleManagementService.companyBundleUrl(jwtAccount.companyId), bundleInformation).subscribe({
        next: (bundle) => {
          resolve(bundle);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public updateBundle(bundleId: string, bundleUpdates: BundleUpdatedInformation): Promise<BundleDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<BundleDTO>(((resolve, reject) => {
      this.http.put<BundleDTO>(BundleManagementService.companySpecificBundleUrl(jwtAccount.companyId, bundleId), bundleUpdates).subscribe({
        next: (bundle) => {
          resolve(bundle);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }
}
