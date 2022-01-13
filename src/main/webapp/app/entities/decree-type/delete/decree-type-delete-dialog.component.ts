import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDecreeType } from '../decree-type.model';
import { DecreeTypeService } from '../service/decree-type.service';

@Component({
  templateUrl: './decree-type-delete-dialog.component.html',
})
export class DecreeTypeDeleteDialogComponent {
  decreeType?: IDecreeType;

  constructor(protected decreeTypeService: DecreeTypeService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.decreeTypeService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
