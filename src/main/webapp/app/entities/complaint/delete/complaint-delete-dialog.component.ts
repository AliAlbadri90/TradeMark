import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IComplaint } from '../complaint.model';
import { ComplaintService } from '../service/complaint.service';

@Component({
  templateUrl: './complaint-delete-dialog.component.html',
})
export class ComplaintDeleteDialogComponent {
  complaint?: IComplaint;

  constructor(protected complaintService: ComplaintService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.complaintService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
