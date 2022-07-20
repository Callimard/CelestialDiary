import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ScreenService {

  public static readonly bigScreenWidth = environment.bigScreenWidth;

  constructor() {
    // Nothing
  }

  public static isBigScreen(): boolean {
    return window.innerWidth > ScreenService.bigScreenWidth;
  }
}
