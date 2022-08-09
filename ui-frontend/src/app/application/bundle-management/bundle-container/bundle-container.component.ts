import {Component, OnInit, ViewChild} from '@angular/core';
import {BundleSelectionComponent} from "../bundle-selection/bundle-selection.component";

@Component({
  selector: 'app-bundle-container',
  templateUrl: './bundle-container.component.html',
  styleUrls: ['./bundle-container.component.css']
})
export class BundleContainerComponent implements OnInit {

  @ViewChild('bundleSelection') bundleSelection!: BundleSelectionComponent;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
