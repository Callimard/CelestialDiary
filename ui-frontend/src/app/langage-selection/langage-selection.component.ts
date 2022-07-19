import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {environment} from 'src/environments/environment';

@Component({
  selector: '[app-langage-selection]',
  templateUrl: './langage-selection.component.html',
  styleUrls: ['./langage-selection.component.css']
})
export class LangageSelectionComponent implements OnInit {

  env = environment;

  constructor(private translate: TranslateService) {
    // Nothing
  }

  ngOnInit(): void {
    // Nothing

  }

  changeLangage(langage: string) {
    this.translate.use(langage);
  }

}
