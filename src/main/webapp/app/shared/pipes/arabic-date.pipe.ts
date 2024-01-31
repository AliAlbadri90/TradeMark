import { Pipe, PipeTransform } from '@angular/core';
import { DatePipe, registerLocaleData } from '@angular/common';
import localeAr from '@angular/common/locales/ar';
import * as dayjs from 'dayjs';
import 'dayjs/locale/ar'; // Import the Arabic locale

registerLocaleData(localeAr);

@Pipe({
  name: 'arabicDate',
})
export class ArabicDatePipe implements PipeTransform {
  transform(value: any): string {
    const datePipe = new DatePipe('ar-EG');
    return datePipe.transform(value, 'd MMMM yyyy') ?? '';
  }
}
