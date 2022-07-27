import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-establishment-creation',
  templateUrl: './establishment-creation.component.html',
  styleUrls: ['./establishment-creation.component.css']
})
export class EstablishmentCreationComponent implements OnInit {

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
