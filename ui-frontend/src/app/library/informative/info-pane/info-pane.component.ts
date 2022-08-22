import {Component, ContentChild, Input, OnInit, PipeTransform, TemplateRef} from '@angular/core';

export interface PaneInfo {
  title: string,
  subTitle?: string,
  img?: string
}

export interface PaneInfoWithId extends PaneInfo {
  id: string
}

export interface PaneInfoTransformer<T> extends PipeTransform {
  transform(elem: T): PaneInfo | undefined
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
