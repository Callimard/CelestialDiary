import {Component, OnInit} from '@angular/core';
import {ClientDTO} from "../../../../data/company-management/person/client/client-dto";
import {ClientManagementService} from "../../../../service/company-management/client/client-management.service";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-client-selection',
  templateUrl: './client-selection.component.html',
  styleUrls: ['./client-selection.component.css']
})
export class ClientSelectionComponent implements OnInit {

  allClients: ClientDTO[] = [];

  constructor(private clientManagementService: ClientManagementService, private router: Router, private activatedRoute: ActivatedRoute,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
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

  navigateToClientInformation(product: any) {
    const selectedClient: ClientDTO = product as ClientDTO;
    this.router.navigate([{outlets: {right: ['information', selectedClient.id]}}], {
      relativeTo: this.activatedRoute
    });
  }

  navigateToCreateClient() {
    this.router.navigate([{outlets: {right: ['create']}}], {
      relativeTo: this.activatedRoute
    });
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
