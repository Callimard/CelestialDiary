import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {backend} from "../../../environments/environment";
import {Observable} from "rxjs";
import {EstablishmentEquipmentDTO} from "../../../data/model/establishment/establishment-equipment-dto";
import {JwtAccount} from "../../authentication/jwt-account";
import {AuthenticationService} from "../../authentication/authentication.service";
import {EstablishmentEquipmentUpdatedInformation} from "../../../data/model/establishment/establishment-equipment-updated-information";
import {EstablishmentEquipmentAddingInformation} from "../../../data/model/establishment/establishment-equipment-adding-information";

@Injectable({
  providedIn: 'root'
})
export class EstablishmentEquipmentManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  private static establishmentEquipmentUrl(companyId: string, establishmentId: string): string {
    return backend.backendUrl + backend.apiV1Url + backend.companiesUrl + '/' + companyId + backend.establishmentsUrl + '/' + establishmentId + backend.equipmentsUrl;
  }

  private static specificEstablishmentEquipmentUrl(companyId: string, establishmentId: string, equipmentId: string): string {
    return EstablishmentEquipmentManagementService.establishmentEquipmentUrl(companyId, establishmentId) + '/' + equipmentId;
  }

  public allEstablishmentEquipments(establishmentId: string): Observable<EstablishmentEquipmentDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.get<EstablishmentEquipmentDTO[]>(EstablishmentEquipmentManagementService.establishmentEquipmentUrl(jwtAccount.companyId, establishmentId));
  }

  public searchEstablishmentEquipments(establishmentId: string, filter: string): Observable<EstablishmentEquipmentDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.get<EstablishmentEquipmentDTO[]>(EstablishmentEquipmentManagementService.establishmentEquipmentUrl(jwtAccount.companyId, establishmentId), {
      params: {
        filter: filter
      }
    });
  }

  public getSpecificEstablishmentEquipment(establishmentId: string, equipmentId: string): Observable<EstablishmentEquipmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.get<EstablishmentEquipmentDTO>(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, equipmentId));
  }

  public addEstablishmentEquipment(establishmentId: string, equipmentId: string, addingInformation: EstablishmentEquipmentAddingInformation): Observable<EstablishmentEquipmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.post<EstablishmentEquipmentDTO>(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, equipmentId), addingInformation);
  }

  public updateEstablishmentEquipment(establishmentId: string, equipmentId: string, updates: EstablishmentEquipmentUpdatedInformation): Observable<EstablishmentEquipmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.put<EstablishmentEquipmentDTO>(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, equipmentId), updates);
  }

  public deleteEstablishmentEquipment(establishmentId: string, equipmentId: string): Observable<boolean> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.delete<boolean>(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, equipmentId));
  }
}
