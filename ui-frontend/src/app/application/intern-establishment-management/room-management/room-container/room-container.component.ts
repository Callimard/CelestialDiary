import {Component, OnInit, ViewChild} from '@angular/core';
import {RoomSelectionComponent} from "../room-selection/room-selection.component";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-room-container',
  templateUrl: './room-container.component.html',
  styleUrls: ['./room-container.component.css']
})
export class RoomContainerComponent implements OnInit {

  @ViewChild("roomSelection") roomSelection!: RoomSelectionComponent;

  establishmentId!: string;

  constructor(private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe(params => {
      this.establishmentId = params['establishmentId'];
    })
  }

  ngOnInit(): void {
    // Nothing
  }

}
