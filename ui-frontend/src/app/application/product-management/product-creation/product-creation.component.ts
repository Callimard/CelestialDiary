import {Component, OnInit} from '@angular/core';
import {Location} from "@angular/common";

@Component({
  selector: 'app-product-creation',
  templateUrl: './product-creation.component.html',
  styleUrls: ['./product-creation.component.css']
})
export class ProductCreationComponent implements OnInit {

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
