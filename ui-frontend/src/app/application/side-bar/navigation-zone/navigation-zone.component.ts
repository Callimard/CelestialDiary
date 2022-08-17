import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-navigation-zone',
  templateUrl: './navigation-zone.component.html',
  styleUrls: ['./navigation-zone.component.css']
})
export class NavigationZoneComponent implements OnInit {

  @Output() selectNavItem = new EventEmitter<string>();

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  navClick(navItemTitle: string) {
    this.selectNavItem.emit(navItemTitle);
  }

}
