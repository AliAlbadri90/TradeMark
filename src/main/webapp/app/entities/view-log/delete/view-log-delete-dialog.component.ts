import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IViewLog } from '../view-log.model';
import { ViewLogService } from '../service/view-log.service';

@Component({
  templateUrl: './view-log-delete-dialog.component.html',
})
export class ViewLogDeleteDialogComponent {
  viewLog?: IViewLog;

  constructor(protected viewLogService: ViewLogService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.viewLogService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
