import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ClientDTO} from "../../../../../data/company-management/person/client/client-dto";
import {ClientForm} from "../../utils/client-form";
import {ClientManagementService} from "../../../../../service/company-management/client/client-management.service";
import {ClientUpdatedInformation} from "../../../../../data/company-management/person/client/client-updated-information";
import {ClientUtils} from "../../../../../service/company-management/client/client-utils";

@Component({
  selector: '[app-client-form-updater]',
  templateUrl: './client-form-updater.component.html',
  styleUrls: ['./client-form-updater.component.css']
})
export class ClientFormUpdaterComponent implements OnInit, OnChanges {

  @Input() client!: ClientDTO;
  @Input() allDisable = false;

  clientUpdateForm!: ClientForm;

  updateFail = false;

  constructor(private clientManagementService: ClientManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    this.clientUpdateForm = new ClientForm(false, this.client);

    if (this.allDisable) {
      this.clientUpdateForm.disable();
    }
  }

  onUpdate() {
    this.clientManagementService.updateClient(this.client.id, this.clientUpdateForm.extractClientInformation() as ClientUpdatedInformation)
      .then((client) => {
        this.updateFail = false;
        ClientUtils.copyClient(client, this.client);
      }).catch(() => {
      this.updateFail = true;
    })
  }

}
