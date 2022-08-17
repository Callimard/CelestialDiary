import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AuthenticationService} from "../../../service/authentication/authentication.service";
import {NavigationZoneComponent} from "./navigation-zone/navigation-zone.component";

@Component({
  selector: '[app-side-bar]',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css','./navigation-zone/navigation-zone.component.css']
})
export class SideBarComponent extends NavigationZoneComponent implements OnInit {

  @Output() close = new EventEmitter<boolean>();

  constructor(private authenticationService: AuthenticationService) {
    super();
  }

  override ngOnInit(): void {
    super.ngOnInit();
  }

  logoutClick() {
    this.authenticationService.employeeLogout();
  }
}
