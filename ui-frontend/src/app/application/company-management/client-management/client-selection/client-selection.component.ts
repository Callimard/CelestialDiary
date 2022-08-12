import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ClientDTO} from "../../../../../data/model/person/client/client-dto";
import {ClientManagementService} from "../../../../../service/company-management/client/client-management.service";
import {PrivilegeService} from "../../../../../service/authentication/privilege.service";
import {PaneInfoTransformer, PaneInfoWithId} from "../../../../library/informative/info-pane/info-pane.component";

export class ClientPaneInfoTransformer implements PaneInfoTransformer<ClientDTO> {

  transform(client: ClientDTO): PaneInfoWithId {
    return {
      id: client.id,
      title: client.firstName + ' ' + client.lastName,
      subTitle: client.email,
      img: client.photo
    };
  }

}

@Component({
  selector: '[app-client-selection]',
  templateUrl: './client-selection.component.html',
  styleUrls: ['./client-selection.component.css']
})
export class ClientSelectionComponent implements OnInit {

  allClients: ClientDTO[] = [];

  clientPaneInfoTransformer: PaneInfoTransformer<ClientDTO> = new ClientPaneInfoTransformer();

  @Output() clientSelected = new EventEmitter<string>();
  @Output() wantCreateClient = new EventEmitter<boolean>();

  constructor(private clientManagementService: ClientManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeClients();
  }

  public reload() {
    this.chargeClients();
  }

  private chargeClients() {
    this.clientManagementService.allClients().then((allClients) => {
      this.allClients = allClients;
    })
  }

  filterClients(filter: string) {
    this.clientManagementService.searchClient(filter).then((allClients) => {
      this.allClients = allClients;
    })
  }

  selectClient(client: any) {
    const selectedClient: PaneInfoWithId = client as PaneInfoWithId;
    this.clientSelected.emit(selectedClient.id);
  }

  createClient() {
    this.wantCreateClient.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
