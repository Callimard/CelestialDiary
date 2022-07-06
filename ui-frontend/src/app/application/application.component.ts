import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../service/authentication/authentication.service";

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css']
})
export class ApplicationComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) {
    // Nothing.
  }

  ngOnInit(): void {
    this.authenticationService.checkLogin();
  }
}
