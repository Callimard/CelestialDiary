import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DateService {

  constructor() {
    // Nothing
  }

  public static toYearWeekDay(date: Date): string {
    let split: string[] = date.toLocaleDateString().split('/');
    return split[2] + '-' + split[1] + '-' + split[0];
  }

  public static firstDayOfWeek(baseDate: Date): Date {
    let firstDayOfWeek = new Date(baseDate);
    let day = baseDate.getDay() == 0 ? 7 : baseDate.getDay();
    firstDayOfWeek.setDate(baseDate.getDate() - (day - 1));

    return firstDayOfWeek;
  }

  public static lastDayOfWeek(baseDate: Date): Date {
    let lastDayOfWeek = new Date(baseDate);
    lastDayOfWeek.setDate(DateService.firstDayOfWeek(baseDate).getDate() + 6);
    return lastDayOfWeek;
  }
}
