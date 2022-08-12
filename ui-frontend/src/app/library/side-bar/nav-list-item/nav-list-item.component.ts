import {Component, ContentChild, Input, OnInit, TemplateRef} from '@angular/core';

@Component({
  selector: '[app-nav-list-item]',
  templateUrl: './nav-list-item.component.html',
  styleUrls: ['./nav-list-item.component.css', '../navigation-zone/navigation-zone.component.css']
})
export class NavListItemComponent implements OnInit {

  @Input() title!: string
  @Input() subItems!: any[];

  open = false;

  @ContentChild('item') itemTemplate: TemplateRef<any> | undefined

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
