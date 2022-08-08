import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {PrestationDTO} from "../../../../data/company-management/saleable/prestation/prestation-dto";
import {PrestationManagementService} from "../../../../service/company-management/saleable/prestation-management.service";
import {PrivilegeService} from "../../../../service/authentication/privilege.service";

@Component({
  selector: '[app-prestation-information]',
  templateUrl: './prestation-information.component.html',
  styleUrls: ['./prestation-information.component.css']
})
export class PrestationInformationComponent implements OnInit, OnChanges {

  @Input() prestationId?: string;

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() prestationHasBeenUpdated = new EventEmitter<boolean>();

  prestation?: PrestationDTO;

  constructor(private prestationManagementService: PrestationManagementService,
              private privilegeService: PrivilegeService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  ngOnChanges(_changes: SimpleChanges): void {
    if (this.prestationId != null) {
      this.chargePrestation(this.prestationId);
    }
  }

  private chargePrestation(prestationId: string) {
    this.prestationManagementService.getSpecificPrestation(prestationId).then((prestation) => {
      this.prestation = prestation;
    })
  }

  goBack() {
    this.wantGoBack.emit(true);
  }

  get privilegeManagement(): PrivilegeService {
    return this.privilegeService;
  }
}
