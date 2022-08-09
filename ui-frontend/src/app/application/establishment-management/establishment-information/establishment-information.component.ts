import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {EstablishmentDTO} from "../../../../data/company-management/establishment/establishment-dto";
import {EstablishmentManagementService} from "../../../../service/company-management/establishment/establishment-management.service";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";

@Component({
  selector: '[app-establishment-information]',
  templateUrl: './establishment-information.component.html',
  styleUrls: ['./establishment-information.component.css']
})
export class EstablishmentInformationComponent implements OnInit, OnChanges {

  @Input() establishmentId?: string;

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() establishmentHasBeenUpdated = new EventEmitter<boolean>();

  establishment?: EstablishmentDTO;

  constructor(private establishmentManagementService: EstablishmentManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    if (this.establishmentId != null) {
      this.chargeEstablishment(this.establishmentId);
    }

  }

  private chargeEstablishment(establishmentId: string) {
    this.establishmentManagementService.getSpecificEstablishment(establishmentId).then((establishment) => {
      this.establishment = establishment;
    })
  }

  goBack() {
    this.wantGoBack.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
