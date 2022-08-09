import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {ClientDTO} from "../../../../data/company-management/person/client/client-dto";
import {ClientManagementService} from "../../../../service/company-management/client/client-management.service";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";

@Component({
  selector: '[app-client-information]',
  templateUrl: './client-information.component.html',
  styleUrls: ['./client-information.component.css']
})

export class ClientInformationComponent implements OnInit, OnChanges {

  @Input() clientId?: string;

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() clientHasBeenUpdated = new EventEmitter<boolean>();

  client?: ClientDTO;

  constructor(private clientManagementService: ClientManagementService,
              private privilegeService: PrivilegeService) {
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    if (this.clientId != null) {
      this.chargeClient(this.clientId);
    }
  }

  private chargeClient(clientId: string) {
    this.clientManagementService.getSpecificClient(clientId).then((client) => {
      this.client = client;
    });
  }

  goBack() {
    this.wantGoBack.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
