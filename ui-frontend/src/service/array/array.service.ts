import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ArrayService {

  constructor() {
    // Nothing
  }

  public static copy(array: any[]): any[] {
    let copy: any[] = [];
    array.forEach(val => copy.push(val));
    return copy;
  }
}
