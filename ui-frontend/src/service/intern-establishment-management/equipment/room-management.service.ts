import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {backend} from "../../../environments/environment";
import {Observable} from "rxjs";
import {RoomDTO} from "../../../data/model/establishment/room-dto";
import {JwtAccount} from "../../authentication/jwt-account";
import {AuthenticationService} from "../../authentication/authentication.service";
import {RoomCreationInformation} from "../../../data/model/establishment/room-creation-information";
import {RoomUpdatedInformation} from "../../../data/model/establishment/room-updated-information";

@Injectable({
  providedIn: 'root'
})
export class RoomManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  private static roomUrl(companyId: string, establishmentId: string): string {
    return backend.backendUrl + backend.apiV1Url + backend.companiesUrl + '/' + companyId + backend.establishmentsUrl + '/' + establishmentId + backend.roomsUrl;
  }

  private static specificRoomUrl(companyId: string, establishmentId: string, roomName: string): string {
    return RoomManagementService.roomUrl(companyId, establishmentId) + '/' + roomName;
  }

  public allRooms(establishmentId: string): Observable<RoomDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.get<RoomDTO[]>(RoomManagementService.roomUrl(jwtAccount.companyId, establishmentId));
  }

  public searchRoom(establishmentId: string, filter: string): Observable<RoomDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.get<RoomDTO[]>(RoomManagementService.roomUrl(jwtAccount.companyId, establishmentId), {
      params: {
        filter: filter
      }
    });
  }

  public getSpecificRoom(establishmentId: string, roomName: string): Observable<RoomDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.get<RoomDTO>(RoomManagementService.specificRoomUrl(jwtAccount.companyId, establishmentId, roomName));
  }

  public createRoom(establishmentId: string, information: RoomCreationInformation): Observable<RoomDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.post<RoomDTO>(RoomManagementService.roomUrl(jwtAccount.companyId, establishmentId), information);
  }

  public updateRoom(establishmentId: string, roomName: string, information: RoomUpdatedInformation): Observable<RoomDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.put<RoomDTO>(RoomManagementService.specificRoomUrl(jwtAccount.companyId, establishmentId, roomName), information);
  }

  public deleteRoom(establishmentId: string, roomName: string): Observable<any> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return this.http.delete(RoomManagementService.specificRoomUrl(jwtAccount.companyId, establishmentId, roomName));
  }
}
