import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-role-creation',
  templateUrl: './role-creation.component.html',
  styleUrls: ['./role-creation.component.css']
})
export class RoleCreationComponent implements OnInit {

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
