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
  @Input() ticketHeight: string = "75px";
  @Input() roundImage: boolean = true;
  @Input() withImage: boolean = true;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
