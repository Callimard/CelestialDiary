import {Component, Input, OnInit} from '@angular/core';
import {ClientForm} from "../client-form";

@Component({
  selector: '[app-client-form-content]',
  templateUrl: './client-form-content.component.html',
  styleUrls: ['./client-form-content.component.css']
})
export class ClientFormContentComponent implements OnInit {

  @Input() clientForm!: ClientForm;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
