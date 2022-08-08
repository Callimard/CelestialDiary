import {Component, ContentChild, Input, OnInit, TemplateRef} from '@angular/core';

export interface Info {
  title: string,
  subTitle?: string,
  img?: string
}

@Component({
  selector: '[app-info-pane]',
  templateUrl: './info-pane.component.html',
  styleUrls: ['./info-pane.component.css']
})
export class InfoPaneComponent implements OnInit {

  @Input() displayLeft = true;
  @Input() displayCenter = true;
  @Input() displayRight = true;

  @Input() centreInfo!: Info;

  @ContentChild('left') leftContent: TemplateRef<any> | undefined;
  @ContentChild('center') centerContent: TemplateRef<any> | undefined;
  @ContentChild('right') rightContent: TemplateRef<any> | undefined;

  constructor() {
    // Nothings
  }

  ngOnInit(): void {
    // Nothing
  }

}
