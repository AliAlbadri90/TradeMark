import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITrademarkRegistered } from '../trademark-registered.model';
import { TrademarkRegisteredService } from '../service/trademark-registered.service';

@Component({
  templateUrl: './trademark-registered-delete-dialog.component.html',
})
export class TrademarkRegisteredDeleteDialogComponent {
  trademarkRegistered?: ITrademarkRegistered;

  constructor(protected trademarkRegisteredService: TrademarkRegisteredService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.trademarkRegisteredService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
