import {Component, ContentChild, Input, OnInit, TemplateRef} from '@angular/core';

export interface NavItemElem {
  canBeDisplay: boolean
}

@Component({
  selector: '[app-nav-list-item]',
  templateUrl: './nav-list-item.component.html',
  styleUrls: ['./nav-list-item.component.css', '../navigation-zone/navigation-zone.component.css']
})
export class NavListItemComponent implements OnInit {

  @Input() title!: string
  @Input() subItems: NavItemElem[] = [];

  items: NavItemElem[] = [];

  open = false;

  @ContentChild('item') itemTemplate: TemplateRef<any> | undefined

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    this.items = this.subItems.filter((i) => i.canBeDisplay);
  }

}
