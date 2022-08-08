import {Component, OnInit} from '@angular/core';
import {ScreenService} from "../../../../service/screen/screen.service";

@Component({
  selector: 'app-two-side',
  templateUrl: './two-side.component.html',
  styleUrls: ['./two-side.component.css']
})
export class TwoSideComponent implements OnInit {

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  isBigScreen(): boolean {
    return ScreenService.isBigScreen();
  }

}
