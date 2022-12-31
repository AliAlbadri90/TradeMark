import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITrademarkDecree } from '../trademark-decree.model';
import { TrademarkDecreeService } from '../service/trademark-decree.service';

@Component({
  templateUrl: './trademark-decree-delete-dialog.component.html',
})
export class TrademarkDecreeDeleteDialogComponent {
  trademarkDecree?: ITrademarkDecree;

  constructor(protected trademarkDecreeService: TrademarkDecreeService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.trademarkDecreeService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
