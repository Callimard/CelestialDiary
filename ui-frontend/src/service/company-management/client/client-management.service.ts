import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {backend} from "../../../environments/environment";
import {ClientDTO} from "../../../data/model/person/client/client-dto";
import {JwtAccount} from "../../authentication/jwt-account";
import {AuthenticationService} from "../../authentication/authentication.service";
import {ClientInformationCreation} from "../../../data/model/person/client/client-information-creation";
import {ClientUpdatedInformation} from "../../../data/model/person/client/client-updated-information";

@Injectable({
  providedIn: 'root'
})
export class ClientManagementService {

  constructor(private http: HttpClient) {
    // Nothing
  }

  public static companyClientUrl(companyId: string): string {
    return backend.backendUrl + backend.apiV1Url + backend.companiesUrl + '/' + companyId + backend.clientsUrl;
  }

  public static companySpecificClientUrl(companyId: string, clientId: string): string {
    return ClientManagementService.companyClientUrl(companyId) + "/" + clientId;
  }

  public allClients(): Promise<ClientDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<ClientDTO[]>(((resolve, reject) => {
      this.http.get<ClientDTO[]>(ClientManagementService.companyClientUrl(jwtAccount.companyId)).subscribe({
        next: (allClients) => {
          resolve(allClients);
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err.error);
        }
      });
    }));
  }

  public searchClient(filter: string): Promise<ClientDTO[]> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<ClientDTO[]>(((resolve, reject) => {
      this.http.get<ClientDTO[]>(ClientManagementService.companyClientUrl(jwtAccount.companyId) + '?filter=' + filter).subscribe({
        next: (allClients) => {
          resolve(allClients);
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public createClient(clientInfo: ClientInformationCreation): Promise<ClientDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<ClientDTO>(((resolve, reject) => {
      this.http.post<ClientDTO>(ClientManagementService.companyClientUrl(jwtAccount.companyId), clientInfo).subscribe({
        next: (client) => {
          resolve(client);
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public getSpecificClient(clientId: string): Promise<ClientDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<ClientDTO>(((resolve, reject) => {
      this.http.get<ClientDTO>(ClientManagementService.companySpecificClientUrl(jwtAccount.companyId, clientId)).subscribe({
        next: (client) => {
          resolve(client);
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err.error);
        }
      })
    }))
  }

  public updateClient(clientId: string, clientUpdates: ClientUpdatedInformation): Promise<ClientDTO> {
    const jwtAccount: JwtAccount = AuthenticationService.getJwtAccount();
    return new Promise<ClientDTO>(((resolve, reject) => {
      this.http.put<ClientDTO>(ClientManagementService.companySpecificClientUrl(jwtAccount.companyId, clientId), clientUpdates).subscribe({
        next: (client) => {
          resolve(client);
        },
        error: (err: HttpErrorResponse) => {
          console.error(err.error);
          reject(err.error);
        }
      })
    }))
  }
}
