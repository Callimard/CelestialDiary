import {Component, Input, OnInit} from '@angular/core';
import {ScopeFormGroup} from "../scope-form-group";
import {FormControl} from "@angular/forms";

@Component({
  selector: '[app-privilege-table]',
  templateUrl: './privilege-table.component.html',
  styleUrls: ['./privilege-table.component.css']
})
export class PrivilegeTableComponent implements OnInit {

  @Input() scopeForm!: ScopeFormGroup;

  allChecked = false;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  checkAll() {
    this.allChecked = !this.allChecked;
    for (let privilege of this.scopeForm.scopePrivileges) {
      this.scopeForm.setControl(privilege.identifierName, new FormControl(this.allChecked));
    }
  }

}
