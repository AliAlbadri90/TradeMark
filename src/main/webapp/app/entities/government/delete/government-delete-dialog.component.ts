import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IGovernment } from '../government.model';
import { GovernmentService } from '../service/government.service';

@Component({
  templateUrl: './government-delete-dialog.component.html',
})
export class GovernmentDeleteDialogComponent {
  government?: IGovernment;

  constructor(protected governmentService: GovernmentService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.governmentService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
