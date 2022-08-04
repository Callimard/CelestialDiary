import {Component, OnInit} from '@angular/core';
import {ClientDTO} from "../../../../data/company-management/person/client/client-dto";
import {ClientManagementService} from "../../../../service/company-management/client/client-management.service";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";

@Component({
  selector: 'app-client-information',
  templateUrl: './client-information.component.html',
  styleUrls: ['./client-information.component.css']
})
export class ClientInformationComponent implements OnInit {

  client?: ClientDTO;

  constructor(private clientManagementService: ClientManagementService, private location: Location, private activatedRoute: ActivatedRoute,
              private privilegeService: PrivilegeService) {
    this.activatedRoute.params.subscribe({
      next: (param) => {
        const clientId: string | undefined = param["clientId"];
        if (clientId != null) {
          this.chargeClient(clientId);
        }
      }
    })
  }

  ngOnInit(): void {
    // Nothing
  }

  private chargeClient(clientId: string) {
    this.clientManagementService.getSpecificClient(clientId).then((client) => {
      this.client = client;
    });
  }

  goBack() {
    this.location.back()
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
