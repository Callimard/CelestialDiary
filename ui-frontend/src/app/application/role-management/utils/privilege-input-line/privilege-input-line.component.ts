import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {PrivilegeDTO} from "../../../../../data/security/privilege/privilege-dto";

@Component({
  selector: '[app-privilege-input-line]',
  templateUrl: './privilege-input-line.component.html',
  styleUrls: ['./privilege-input-line.component.css']
})
export class PrivilegeInputLineComponent implements OnInit {

  @Input() group!: FormGroup;
  @Input() privilege!: PrivilegeDTO;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }



}
