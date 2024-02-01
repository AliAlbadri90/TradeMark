import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'translateMe',
})
export class TranslateMePipe implements PipeTransform {
  translations: any;

  constructor() {
    this.translations = new Map<string, string>();
    this.translations.set('ROLE_ADMIN', 'مدير نظام');
    this.translations.set('ROLE_USER', 'مستخدم');

    this.translations.set('INITIAL_PUBLICATION', 'نشر مبدئي');
    this.translations.set('FINAL_PUBLICATION', 'نشر نهائي');
    this.translations.set('CONTESTED', 'متنازع عليه');
  }

  transform(value: any): string {
    // eslint-disable-next-line @typescript-eslint/no-unsafe-return
    return this.translations.get(value) || value;
  }
}
