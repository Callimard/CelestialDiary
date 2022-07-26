import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-bundle-creation',
  templateUrl: './bundle-creation.component.html',
  styleUrls: ['./bundle-creation.component.css']
})
export class BundleCreationComponent implements OnInit {

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    // Nothing.
  }

  ngOnInit(): void {
    // Nothing
  }

  goBack() {
    this.router.navigate(['..'], {relativeTo: this.activatedRoute});
  }
}
