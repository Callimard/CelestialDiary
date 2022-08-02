import {Component, Input, OnInit} from '@angular/core';
import {RoleFormGroup} from "../role-form-group";

@Component({
  selector: '[app-role-form-content]',
  templateUrl: './role-form-content.component.html',
  styleUrls: ['./role-form-content.component.css']
})
export class RoleFormContentComponent implements OnInit {

  @Input() roleFormGroup!: RoleFormGroup;
  @Input() allDisabled = false;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
