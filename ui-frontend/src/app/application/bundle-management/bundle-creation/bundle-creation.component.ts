import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";

@Component({
  selector: 'app-bundle-creation',
  templateUrl: './bundle-creation.component.html',
  styleUrls: ['./bundle-creation.component.css']
})
export class BundleCreationComponent implements OnInit {

  constructor(private location: Location) {
    // Nothing.
  }

  ngOnInit(): void {
    // Nothing
  }

  goBack() {
    this.location.back();
  }
}
