import {Component, ContentChild, OnInit, TemplateRef} from '@angular/core';
import {ScreenService} from "../../../../service/screen/screen.service";

@Component({
  selector: '[app-two-side-management]',
  templateUrl: './two-side-management.component.html',
  styleUrls: ['./two-side-management.component.css']
})
export class TwoSideManagementComponent implements OnInit {

  initialPos = true;

  @ContentChild('left') leftTemplate: TemplateRef<any> | undefined;
  @ContentChild('right') rightTemplate: TemplateRef<any> | undefined;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  swipeRightToLeft() {
    if (this.initialPos)
      this.initialPos = !this.initialPos;
  }

  swipeLeftToRight() {
    if (!this.initialPos) {
      this.initialPos = !this.initialPos;
    }
  }

  resetInitialPos() {
    this.initialPos = true;
  }

  isBigScreen(): boolean {
    return ScreenService.isBigScreen();
  }
}
