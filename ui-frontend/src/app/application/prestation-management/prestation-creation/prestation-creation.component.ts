import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-prestation-creation',
  templateUrl: './prestation-creation.component.html',
  styleUrls: ['./prestation-creation.component.css']
})
export class PrestationCreationComponent implements OnInit {

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

  goBack() {
    this.router.navigate(['..'], {relativeTo: this.activatedRoute});
  }

}
