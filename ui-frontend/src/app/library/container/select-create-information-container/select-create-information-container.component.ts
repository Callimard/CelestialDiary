import {Component, ContentChild, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {TwoSideManagementComponent} from "../two-side-management/two-side-management.component";

@Component({
  selector: '[app-select-create-information-container]',
  templateUrl: './select-create-information-container.component.html',
  styleUrls: ['./select-create-information-container.component.css']
})
export class SelectCreateInformationContainerComponent extends TwoSideManagementComponent implements OnInit {

  create = false;
  elementId?: string;

  @ViewChild("twoSideContainer") container!: TwoSideManagementComponent;

  @ContentChild("selection") selectionTemplate: TemplateRef<any> | undefined;
  @ContentChild("creation") creationTemplate: TemplateRef<any> | undefined;
  @ContentChild("information") informationTemplate: TemplateRef<any> | undefined;

  constructor() {
    super();
  }

  displayCreation() {
    this.elementId = undefined;
    this.create = true;
    this.swipeRightToLeft();
  }

  displayInformation(elementId: string) {
    this.elementId = elementId;
    this.create = false;
    this.swipeRightToLeft();
  }

  override ngOnInit(): void {
    super.ngOnInit();
  }

  override swipeRightToLeft() {
    this.container.swipeRightToLeft();
  }

  override swipeLeftToRight() {
    this.container.swipeLeftToRight();
  }

  override resetInitialPos() {
    this.container.resetInitialPos();
  }
}
