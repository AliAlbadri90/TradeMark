import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDecreeCategory } from '../decree-category.model';
import { DecreeCategoryService } from '../service/decree-category.service';

@Component({
  templateUrl: './decree-category-delete-dialog.component.html',
})
export class DecreeCategoryDeleteDialogComponent {
  decreeCategory?: IDecreeCategory;

  constructor(protected decreeCategoryService: DecreeCategoryService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.decreeCategoryService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
