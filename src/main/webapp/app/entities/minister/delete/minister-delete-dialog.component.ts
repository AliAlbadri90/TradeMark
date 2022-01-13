import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IMinister } from '../minister.model';
import { MinisterService } from '../service/minister.service';

@Component({
  templateUrl: './minister-delete-dialog.component.html',
})
export class MinisterDeleteDialogComponent {
  minister?: IMinister;

  constructor(protected ministerService: MinisterService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ministerService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
