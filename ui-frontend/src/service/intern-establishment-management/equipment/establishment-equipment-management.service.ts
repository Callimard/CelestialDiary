import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {backend} from "../../../environments/environment";
import {Observable} from "rxjs";
import {EstablishmentEquipmentDTO} from "../../../data/model/establishment/establishment-equipment-dto";
import {JwtAccount} from "../../authentication/jwt-account";
import {AuthenticationService} from "../../authentication/authentication.service";
import {EstablishmentEquipmentUpdatedInformation} from "../../../data/model/establishment/establishment-equipment-updated-information";
import {EstablishmentEquipmentAddingInformation} from "../../../data/model/establishment/establishment-equipment-adding-information";
import {AdvancedEstablishmentEquipmentContainerDTO} from "../../../data/model/establishment/advanced-establishment-equipment-container-dto";
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

  private static specificEstablishmentEquipmentUrl(companyId: string, establishmentId: string, equipmentId: string): string {
    return EstablishmentEquipmentManagementService.establishmentEquipmentUrl(companyId, establishmentId) + '/' + equipmentId;
  }

  public allEstablishmentEquipments(establishmentId: string): Observable<AdvancedEstablishmentEquipmentContainerDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.get<AdvancedEstablishmentEquipmentContainerDTO[]>(EstablishmentEquipmentManagementService.establishmentEquipmentUrl(jwtAccount.companyId, establishmentId));
  }

  public searchEstablishmentEquipments(establishmentId: string, filter: string): Observable<AdvancedEstablishmentEquipmentContainerDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.get<AdvancedEstablishmentEquipmentContainerDTO[]>(EstablishmentEquipmentManagementService.establishmentEquipmentUrl(jwtAccount.companyId, establishmentId), {
      params: {
        filter: filter
      }
    });
  }

  public getSpecificEstablishmentEquipment(establishmentId: string, equipmentId: string, establishmentEquipmentId: string): Observable<EstablishmentEquipmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.get<EstablishmentEquipmentDTO>(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, equipmentId),
      {
        params: {
          idEstablishmentEquipment: establishmentEquipmentId
        }
      });
  }

  public addEstablishmentEquipment(establishmentId: string, equipmentId: string, addingInformation: EstablishmentEquipmentAddingInformation): Observable<EstablishmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.post<EstablishmentDTO>(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, equipmentId), addingInformation);
  }

  public updateEstablishmentEquipment(establishmentId: string, equipmentId: string, establishmentEquipmentId: string, updates: EstablishmentEquipmentUpdatedInformation): Observable<EstablishmentEquipmentDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.put<EstablishmentEquipmentDTO>(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, equipmentId), updates,
      {
        params: {
          idEstablishmentEquipment: establishmentEquipmentId
        }
      });
  }

  public deleteEstablishmentEquipment(establishmentId: string, equipmentId: string, establishmentEquipmentId: string): Observable<any> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.delete(EstablishmentEquipmentManagementService.specificEstablishmentEquipmentUrl(jwtAccount.companyId, establishmentId, equipmentId),
      {
        params: {
          idEstablishmentEquipment: establishmentEquipmentId
        }
      });
  }
}
