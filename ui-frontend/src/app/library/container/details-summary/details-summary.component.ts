import {Component, ContentChild, OnInit, TemplateRef} from '@angular/core';

@Component({
  selector: '[app-details-summary]',
  templateUrl: './details-summary.component.html',
  styleUrls: ['./details-summary.component.css']
})
export class DetailsSummaryComponent implements OnInit {

  @ContentChild('summary') summaryTemplate: TemplateRef<any> | undefined;
  @ContentChild('details') detailsTemplate: TemplateRef<any> | undefined;

  constructor() {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing
  }

}
