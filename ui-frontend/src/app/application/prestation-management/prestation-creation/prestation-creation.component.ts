import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";

@Component({
  selector: 'app-prestation-creation',
  templateUrl: './prestation-creation.component.html',
  styleUrls: ['./prestation-creation.component.css']
})
export class PrestationCreationComponent implements OnInit {

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
