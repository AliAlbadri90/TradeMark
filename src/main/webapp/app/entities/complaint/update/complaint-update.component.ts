import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IComplaint, Complaint } from '../complaint.model';
import { ComplaintService } from '../service/complaint.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { ComplaintStatus } from 'app/entities/enumerations/complaint-status.model';

@Component({
  selector: 'jhi-complaint-update',
  templateUrl: './complaint-update.component.html',
})
export class ComplaintUpdateComponent implements OnInit {
  isSaving = false;
  complaintStatusValues = Object.keys(ComplaintStatus);

  editForm = this.fb.group({
    id: [],
    complaintUUID: [],
    complaintNo: [],
    trademarkNo: [],
    complaintDate: [],
    complaintOfficeReceivedDate: [],
    complaintPaymentReceipt: [],
    complaintYear: [],
    complainerPersonFullName: [],
    complainerPersonJob: [],
    complainerPersonNationality: [],
    complainerPersonAddress: [],
    complainerCompanyName: [],
    complainerCompanyAddress: [],
    complainerCompanyPurpose: [],
    complainerCompanyHeadQuarterAddress: [],
    complainerCompanyLibyaAddress: [],
    pdfFile: [],
    pdfFileContentType: [],
    pdfFileUrl: [],
    complaintStatus: [],
    complaintDetails: [],
    notes: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected complaintService: ComplaintService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ complaint }) => {
      this.updateForm(complaint);
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('archiveApp.error', { message: err.message })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const complaint = this.createFromForm();
    if (complaint.id !== undefined) {
      this.subscribeToSaveResponse(this.complaintService.update(complaint));
    } else {
      this.subscribeToSaveResponse(this.complaintService.create(complaint));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComplaint>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(complaint: IComplaint): void {
    this.editForm.patchValue({
      id: complaint.id,
      complaintUUID: complaint.complaintUUID,
      complaintNo: complaint.complaintNo,
      trademarkNo: complaint.trademarkNo,
      complaintDate: complaint.complaintDate,
      complaintOfficeReceivedDate: complaint.complaintOfficeReceivedDate,
      complaintPaymentReceipt: complaint.complaintPaymentReceipt,
      complaintYear: complaint.complaintYear,
      complainerPersonFullName: complaint.complainerPersonFullName,
      complainerPersonJob: complaint.complainerPersonJob,
      complainerPersonNationality: complaint.complainerPersonNationality,
      complainerPersonAddress: complaint.complainerPersonAddress,
      complainerCompanyName: complaint.complainerCompanyName,
      complainerCompanyAddress: complaint.complainerCompanyAddress,
      complainerCompanyPurpose: complaint.complainerCompanyPurpose,
      complainerCompanyHeadQuarterAddress: complaint.complainerCompanyHeadQuarterAddress,
      complainerCompanyLibyaAddress: complaint.complainerCompanyLibyaAddress,
      pdfFile: complaint.pdfFile,
      pdfFileContentType: complaint.pdfFileContentType,
      pdfFileUrl: complaint.pdfFileUrl,
      complaintStatus: complaint.complaintStatus,
      complaintDetails: complaint.complaintDetails,
      notes: complaint.notes,
    });
  }

  protected createFromForm(): IComplaint {
    return {
      ...new Complaint(),
      id: this.editForm.get(['id'])!.value,
      complaintUUID: this.editForm.get(['complaintUUID'])!.value,
      complaintNo: this.editForm.get(['complaintNo'])!.value,
      trademarkNo: this.editForm.get(['trademarkNo'])!.value,
      complaintDate: this.editForm.get(['complaintDate'])!.value,
      complaintOfficeReceivedDate: this.editForm.get(['complaintOfficeReceivedDate'])!.value,
      complaintPaymentReceipt: this.editForm.get(['complaintPaymentReceipt'])!.value,
      complaintYear: this.editForm.get(['complaintYear'])!.value,
      complainerPersonFullName: this.editForm.get(['complainerPersonFullName'])!.value,
      complainerPersonJob: this.editForm.get(['complainerPersonJob'])!.value,
      complainerPersonNationality: this.editForm.get(['complainerPersonNationality'])!.value,
      complainerPersonAddress: this.editForm.get(['complainerPersonAddress'])!.value,
      complainerCompanyName: this.editForm.get(['complainerCompanyName'])!.value,
      complainerCompanyAddress: this.editForm.get(['complainerCompanyAddress'])!.value,
      complainerCompanyPurpose: this.editForm.get(['complainerCompanyPurpose'])!.value,
      complainerCompanyHeadQuarterAddress: this.editForm.get(['complainerCompanyHeadQuarterAddress'])!.value,
      complainerCompanyLibyaAddress: this.editForm.get(['complainerCompanyLibyaAddress'])!.value,
      pdfFileContentType: this.editForm.get(['pdfFileContentType'])!.value,
      pdfFile: this.editForm.get(['pdfFile'])!.value,
      pdfFileUrl: this.editForm.get(['pdfFileUrl'])!.value,
      complaintStatus: this.editForm.get(['complaintStatus'])!.value,
      complaintDetails: this.editForm.get(['complaintDetails'])!.value,
      notes: this.editForm.get(['notes'])!.value,
    };
  }
}
