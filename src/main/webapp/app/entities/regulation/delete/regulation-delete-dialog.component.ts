import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRegulation } from '../regulation.model';
import { RegulationService } from '../service/regulation.service';

@Component({
  templateUrl: './regulation-delete-dialog.component.html',
})
export class RegulationDeleteDialogComponent {
  regulation?: IRegulation;

  constructor(protected regulationService: RegulationService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.regulationService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
