import {Component, OnInit, ViewChild} from '@angular/core';
import {ProductSelectionComponent} from "../product-selection/product-selection.component";

@Component({
  selector: 'app-product-container',
  templateUrl: './product-container.component.html',
  styleUrls: ['./product-container.component.css']
})
export class ProductContainerComponent implements OnInit {

  @ViewChild('productSelection') productSelection!: ProductSelectionComponent;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
