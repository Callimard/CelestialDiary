import {Component, Input, OnInit} from '@angular/core';
import {WrappedEstablishmentDTO} from "../../../../data/company-management/establishment/wrapped-establishment-dto";

export interface EstablishmentSearchingListObserver {
  onTicketClick(establishment: WrappedEstablishmentDTO): void
}

@Component({
  selector: '[app-establishment-searching-list]',
  templateUrl: './establishment-searching-list.component.html',
  styleUrls: ['./establishment-searching-list.component.css']
})
export class EstablishmentSearchingListComponent implements OnInit {

  @Input() allEstablishments: WrappedEstablishmentDTO[] = [];
  @Input() paddingHeight = "0px";
  @Input() observer?: EstablishmentSearchingListObserver;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  onTicketClick(establishment: WrappedEstablishmentDTO) {
    this.observer?.onTicketClick(establishment);
  }
}

