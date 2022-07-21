import {Component, OnInit} from '@angular/core';
import {ScreenService} from "../../../service/screen/screen.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-two-side',
  templateUrl: './two-side.component.html',
  styleUrls: ['./two-side.component.css']
})
export class TwoSideComponent implements OnInit {

  constructor(private router: Router) {
    // Nothing
   console.log(this.router.routerState);
  }

  ngOnInit(): void {
    // Nothing
  }

  isBigScreen(): boolean {
    return ScreenService.isBigScreen();
  }

}
