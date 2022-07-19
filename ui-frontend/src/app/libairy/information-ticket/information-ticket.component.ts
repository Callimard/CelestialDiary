import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: '[app-information-ticket]',
  templateUrl: './information-ticket.component.html',
  styleUrls: ['./information-ticket.component.css']
})
export class InformationTicketComponent implements OnInit {

  @Input() title?: string = "";
  @Input() subTitle?: string = "";
  @Input() img?: string = "";

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
