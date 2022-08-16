import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: '[app-establishment-equipment-adding]',
  templateUrl: './establishment-equipment-adding.component.html',
  styleUrls: ['./establishment-equipment-adding.component.css']
})
export class EstablishmentEquipmentAddingComponent implements OnInit {

  @Output() wantGoBack = new EventEmitter<boolean>();
  @Output() establishmentEquipmentHasBeenAdded = new EventEmitter<boolean>();

  establishmentId!: string

  constructor(private activatedRoute: ActivatedRoute) {
    // Nothing
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.establishmentId = params['establishmentId'];
    });
  }

}
