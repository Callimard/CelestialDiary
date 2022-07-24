import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";

@Component({
  selector: 'app-establishment-creation',
  templateUrl: './establishment-creation.component.html',
  styleUrls: ['./establishment-creation.component.css']
})
export class EstablishmentCreationComponent implements OnInit {

  constructor(private location: Location) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  goBack() {
    this.location.back();
  }
}
