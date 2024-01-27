import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITrademarkRegistered, TrademarkRegistered } from '../trademark-registered.model';
import { TrademarkRegisteredService } from '../service/trademark-registered.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-trademark-registered-update',
  templateUrl: './trademark-registered-update.component.html',
})
export class TrademarkRegisteredUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    trademarkNo: [],
    year: [],
    decreeNo: [],
    applicantName: [],
    tradeMarkOwner: [],
    country: [],
    nationality: [],
    address: [],
    applyDate: [],
    trademarkEnglish: [],
    trademarkArabic: [],
    category: [],
    file: [],
    fileContentType: [],
    fileUrl: [],
    extraFile: [],
    extraFileContentType: [],
    extraFileUrl: [],
    notes: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected trademarkRegisteredService: TrademarkRegisteredService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trademarkRegistered }) => {
      this.updateForm(trademarkRegistered);
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
    const trademarkRegistered = this.createFromForm();
    if (trademarkRegistered.id !== undefined) {
      this.subscribeToSaveResponse(this.trademarkRegisteredService.update(trademarkRegistered));
    } else {
      this.subscribeToSaveResponse(this.trademarkRegisteredService.create(trademarkRegistered));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrademarkRegistered>>): void {
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

  protected updateForm(trademarkRegistered: ITrademarkRegistered): void {
    this.editForm.patchValue({
      id: trademarkRegistered.id,
      trademarkNo: trademarkRegistered.trademarkNo,
      year: trademarkRegistered.year,
      decreeNo: trademarkRegistered.decreeNo,
      applicantName: trademarkRegistered.applicantName,
      tradeMarkOwner: trademarkRegistered.tradeMarkOwner,
      country: trademarkRegistered.country,
      nationality: trademarkRegistered.nationality,
      address: trademarkRegistered.address,
      applyDate: trademarkRegistered.applyDate,
      trademarkEnglish: trademarkRegistered.trademarkEnglish,
      trademarkArabic: trademarkRegistered.trademarkArabic,
      category: trademarkRegistered.category,
      file: trademarkRegistered.file,
      fileContentType: trademarkRegistered.fileContentType,
      fileUrl: trademarkRegistered.fileUrl,
      extraFile: trademarkRegistered.extraFile,
      extraFileContentType: trademarkRegistered.extraFileContentType,
      extraFileUrl: trademarkRegistered.extraFileUrl,
      notes: trademarkRegistered.notes,
    });
  }

  protected createFromForm(): ITrademarkRegistered {
    return {
      ...new TrademarkRegistered(),
      id: this.editForm.get(['id'])!.value,
      trademarkNo: this.editForm.get(['trademarkNo'])!.value,
      year: this.editForm.get(['year'])!.value,
      decreeNo: this.editForm.get(['decreeNo'])!.value,
      applicantName: this.editForm.get(['applicantName'])!.value,
      tradeMarkOwner: this.editForm.get(['tradeMarkOwner'])!.value,
      country: this.editForm.get(['country'])!.value,
      nationality: this.editForm.get(['nationality'])!.value,
      address: this.editForm.get(['address'])!.value,
      applyDate: this.editForm.get(['applyDate'])!.value,
      trademarkEnglish: this.editForm.get(['trademarkEnglish'])!.value,
      trademarkArabic: this.editForm.get(['trademarkArabic'])!.value,
      category: this.editForm.get(['category'])!.value,
      fileContentType: this.editForm.get(['fileContentType'])!.value,
      file: this.editForm.get(['file'])!.value,
      fileUrl: this.editForm.get(['fileUrl'])!.value,
      extraFileContentType: this.editForm.get(['extraFileContentType'])!.value,
      extraFile: this.editForm.get(['extraFile'])!.value,
      extraFileUrl: this.editForm.get(['extraFileUrl'])!.value,
      notes: this.editForm.get(['notes'])!.value,
    };
  }
}
