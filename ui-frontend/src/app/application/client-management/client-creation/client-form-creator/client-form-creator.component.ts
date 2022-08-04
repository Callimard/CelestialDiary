import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ClientForm} from "../../utils/client-form";
import {ClientManagementService} from "../../../../../service/company-management/client/client-management.service";
import {ClientInformationCreation} from "../../../../../data/company-management/person/client/client-information-creation";

@Component({
  selector: '[app-client-form-creator]',
  templateUrl: './client-form-creator.component.html',
  styleUrls: ['./client-form-creator.component.css']
})
export class ClientFormCreatorComponent implements OnInit {

  creationFailed = false;

  @Output() clientCreated = new EventEmitter<boolean>();

  clientCreationForm!: ClientForm;

  constructor(private clientManagementService: ClientManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.clientCreationForm = new ClientForm(true);
  }

  onClientCreation() {
    this.clientManagementService.createClient(this.clientCreationForm.extractClientInformation() as ClientInformationCreation).then(() => {
      this.creationFailed = false;
      this.clientCreated.emit(true);
    }).catch(() => {
      this.creationFailed = true;
    })
  }
}
