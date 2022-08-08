import {Component, ContentChild, Input, OnInit, TemplateRef} from '@angular/core';

export interface PaneInfo {
  title: string,
  subTitle?: string,
  img?: string
}

export interface PaneInfoTransformer<T> {
  transform(elem: T): PaneInfo
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

  @Input() info!: PaneInfo | any;

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
