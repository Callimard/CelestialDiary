import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: '[app-square-image]',
  templateUrl: './square-image.component.html',
  styleUrls: ['./square-image.component.css']
})
export class SquareImageComponent implements OnInit {

  @Input() img!: string;
  @Input() sideSize!: number;
  @Input() round: boolean = false;
  @Input() border?: string

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
