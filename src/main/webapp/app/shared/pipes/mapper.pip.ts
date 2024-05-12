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

    this.translations.set('WITHDRAW', 'سحب');
    this.translations.set('CANCELED', 'إلغاء');
    this.translations.set('WRITTEN_OFF', 'شطب');
    this.translations.set('ACCEPTED', 'قبول');
    this.translations.set('OTHER', 'أخرى');
    this.translations.set('REJECTED', 'رفض');

    this.translations.set('WITHDRAWa', 'المسحوب');
    this.translations.set('CANCELEDa', 'الملغي');
    this.translations.set('WRITTEN_OFFa', 'المشطوب');
    this.translations.set('REJECTEDa', 'المرفوض');
  }

  transform(value: any): string {
    // eslint-disable-next-line @typescript-eslint/no-unsafe-return
    return this.translations.get(value) || value;
  }
}

@Pipe({
  name: 'getColor',
})
export class GetColorPipe implements PipeTransform {
  colors: any;

  constructor() {
    this.colors = new Map<string, string>();
    this.colors.set('WITHDRAW', 'text-warning');
    this.colors.set('CANCELED', 'text-warning');
    this.colors.set('WRITTEN_OFF', 'text-warning');
    this.colors.set('ACCEPTED', 'text-success');
    this.colors.set('OTHER', 'text-primary');
    this.colors.set('REJECTED', 'text-danger');
  }

  transform(value: any): string {
    return this.colors.get(value) || value;
  }
}
