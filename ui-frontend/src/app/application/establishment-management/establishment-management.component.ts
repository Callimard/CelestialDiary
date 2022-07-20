import {Component, OnInit} from '@angular/core';
import {ScreenService} from "../../../service/screen/screen.service";
import {WrappedEstablishmentDTO} from "../../../data/company-management/establishment/wrapped-establishment-dto";
import {EstablishmentSearchingListObserver} from "./establishment-searching-list/establishment-searching-list.component";
import {EstablishmentManagementService} from "../../../service/company-management/establishment-management.service";

@Component({
  selector: 'app-establishment-management',
  templateUrl: './establishment-management.component.html',
  styleUrls: ['./establishment-management.component.css']
})
export class EstablishmentManagementComponent implements OnInit, EstablishmentSearchingListObserver {

  swipeDone = false;

  allEstablishments: WrappedEstablishmentDTO[] = [];

  selectedEstablishment?: WrappedEstablishmentDTO;

  createEstablishment = false;

  constructor(private establishmentManagementService: EstablishmentManagementService) {
    // Nothing
  }

  ngOnInit(): void {
    this.chargeAllEstablishment();
  }

  onTicketClick(establishment: WrappedEstablishmentDTO): void {
    this.selectedEstablishment = establishment;
    this.createEstablishment = false;
    this.swipeDone = true;
  }

  isBigScreen(): boolean {
    return ScreenService.isBigScreen();
  }

  searchText(text: string) {
    this.establishmentManagementService.searchEstablishment(text).then((allEstablishments) => {
      this.allEstablishments = allEstablishments;
    })
  }

  backToEstablishmentSelection() {
    this.swipeDone = false;
  }

  private chargeAllEstablishment() {
    this.establishmentManagementService.allEstablishments().then((allEstablishments) => {
      this.allEstablishments = allEstablishments;
    })
  }

  establishmentUpdateDone(success: boolean) {
    if (success) {
      this.chargeAllEstablishment();
    }
  }

  goCreateEstablishment() {
    this.swipeDone = true;
    this.selectedEstablishment = undefined;
    this.createEstablishment = true;
  }

  establishmentCreationDone(success: boolean) {
    if (success) {
      this.backToEstablishmentSelection();
      this.chargeAllEstablishment();
    }
  }
}
