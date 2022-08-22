import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {backend} from "../../../environments/environment";
import {Observable} from "rxjs";
import {EstablishmentEquipmentDTO} from "../../../data/model/establishment/establishment-equipment-dto";
import {JwtAccount} from "../../authentication/jwt-account";
import {AuthenticationService} from "../../authentication/authentication.service";
import {EstablishmentEquipmentUpdatedInformation} from "../../../data/model/establishment/establishment-equipment-updated-information";
import {EstablishmentEquipmentAddingInformation} from "../../../data/model/establishment/establishment-equipment-adding-information";
import {EstablishmentDTO} from "../../../data/model/establishment/establishment-dto";

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

  private static specificEstablishmentEquipmentUrl(companyId: string, establishmentId: string, establishmentEquipmentId: string): string {
    return EstablishmentEquipmentManagementService.establishmentEquipmentUrl(companyId, establishmentId) + '/' + establishmentEquipmentId;
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

  public getSpecificEstablishmentEquipment(establishmentId: string, establishmentEquipmentId: string): Observable<EstablishmentEquipmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.get<EstablishmentEquipmentDTO>(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, establishmentEquipmentId));
  }

  public addEstablishmentEquipment(establishmentId: string, equipmentId: string, addingInformation: EstablishmentEquipmentAddingInformation): Observable<EstablishmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.post<EstablishmentDTO>(EstablishmentEquipmentManagementService.establishmentEquipmentUrl(jwtAccount.companyId, establishmentId), addingInformation, {
      params: {
        idEquipment: equipmentId
      }
    });
  }

  public updateEstablishmentEquipment(establishmentId: string, establishmentEquipmentId: string, updates: EstablishmentEquipmentUpdatedInformation): Observable<EstablishmentEquipmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.put<EstablishmentEquipmentDTO>(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, establishmentEquipmentId), updates);
  }

  public deleteEstablishmentEquipment(establishmentId: string, establishmentEquipmentId: string): Observable<any> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.delete(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, establishmentEquipmentId));
  }
}
