import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {backend} from "../../../environments/environment";
import {EquipmentDTO} from "../../../data/company-management/equipment/equipment-dto";
import {JwtAccount} from "../../authentication/jwt-account";
import {AuthenticationService} from "../../authentication/authentication.service";
import {EquipmentCreationInformation} from "../../../data/company-management/equipment/equipment-creation-information";
import {EquipmentUpdatedInformation} from "../../../data/company-management/equipment/equipment-updated-information";

@Injectable({
  providedIn: 'root'
})
export class EquipmentManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  private static companyEquipmentUrl(companyId: string): string {
    return backend.backendUrl + backend.apiV1Url + backend.companiesUrl + '/' + companyId + backend.equipmentsUrl;
  }

  private static companySpecificEquipmentUrl(companyId: string, equipmentId: string): string {
    return EquipmentManagementService.companyEquipmentUrl(companyId) + '/' + equipmentId;
  }

  public allEquipments(): Promise<EquipmentDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<EquipmentDTO[]>(((resolve, reject) => {
      this.http.get<EquipmentDTO[]>(EquipmentManagementService.companyEquipmentUrl(jwtAccount.companyId)).subscribe({
        next: (allEquipments) => {
          resolve(allEquipments);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public searchEquipment(filter: string): Promise<EquipmentDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<EquipmentDTO[]>(((resolve, reject) => {
      this.http.get<EquipmentDTO[]>(EquipmentManagementService.companyEquipmentUrl(jwtAccount.companyId) + "?filter=" + filter)
        .subscribe({
          next: (allEquipments) => {
            resolve(allEquipments);
          },
          error: (err: HttpErrorResponse) => {
            console.log(err.error);
            reject(err.error);
          }
        })
    }))
  }

  public getSpecificEquipment(equipmentId: string): Promise<EquipmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<EquipmentDTO>(((resolve, reject) => {
      this.http.get<EquipmentDTO>(EquipmentManagementService.companySpecificEquipmentUrl(jwtAccount.companyId, equipmentId)).subscribe({
        next: (equipment) => {
          resolve(equipment);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public createEquipment(equipmentInformation: EquipmentCreationInformation): Promise<EquipmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<EquipmentDTO>(((resolve, reject) => {
      this.http.post<EquipmentDTO>(EquipmentManagementService.companyEquipmentUrl(jwtAccount.companyId), equipmentInformation).subscribe({
        next: (equipment) => {
          resolve(equipment);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public updateEquipment(equipmentId: string, equipmentUpdates: EquipmentUpdatedInformation): Promise<EquipmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<EquipmentDTO>(((resolve, reject) => {
      this.http.put<EquipmentDTO>(EquipmentManagementService.companySpecificEquipmentUrl(jwtAccount.companyId, equipmentId), equipmentUpdates).subscribe({
        next: (equipment) => {
          resolve(equipment);
        },
        error: (err: HttpErrorResponse) => {
          console.log(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public deleteEquipment(equipmentId: string): Promise<boolean> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<boolean>(((resolve, reject) => {
      this.http.delete(EquipmentManagementService.companySpecificEquipmentUrl(jwtAccount.companyId, equipmentId)).subscribe({
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
