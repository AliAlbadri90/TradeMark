import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ITrademarkDecree, TrademarkDecree } from '../trademark-decree.model';
import { TrademarkDecreeService } from '../service/trademark-decree.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-trademark-decree-update',
  templateUrl: './trademark-decree-update.component.html',
})
export class TrademarkDecreeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    year: [],
    decreeNo: [],
    isAccepted: [],
    decreeDate: [],
    applicantName: [],
    tradeMarkOwner: [],
    country: [],
    applyDate: [],
    serialNo: [],
    trademarkEnglish: [],
    trademarkArabic: [],
    category: [],
    pdfFile: [],
    pdfFileContentType: [],
    pdfFileUrl: [],
    extraPdfFile: [],
    extraPdfFileContentType: [],
    extraPdfFileUrl: [],
    isWithdrawal: [],
    withdrawalDecreeNo: [],
    notes: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected trademarkDecreeService: TrademarkDecreeService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trademarkDecree }) => {
      this.updateForm(trademarkDecree);
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
    const trademarkDecree = this.createFromForm();
    if (trademarkDecree.id !== undefined) {
      this.subscribeToSaveResponse(this.trademarkDecreeService.update(trademarkDecree));
    } else {
      this.subscribeToSaveResponse(this.trademarkDecreeService.create(trademarkDecree));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrademarkDecree>>): void {
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

  protected updateForm(trademarkDecree: ITrademarkDecree): void {
    this.editForm.patchValue({
      id: trademarkDecree.id,
      year: trademarkDecree.year,
      decreeNo: trademarkDecree.decreeNo,
      isAccepted: trademarkDecree.isAccepted,
      decreeDate: trademarkDecree.decreeDate,
      applicantName: trademarkDecree.applicantName,
      tradeMarkOwner: trademarkDecree.tradeMarkOwner,
      country: trademarkDecree.country,
      applyDate: trademarkDecree.applyDate,
      serialNo: trademarkDecree.serialNo,
      trademarkEnglish: trademarkDecree.trademarkEnglish,
      trademarkArabic: trademarkDecree.trademarkArabic,
      category: trademarkDecree.category,
      pdfFile: trademarkDecree.pdfFile,
      pdfFileContentType: trademarkDecree.pdfFileContentType,
      pdfFileUrl: trademarkDecree.pdfFileUrl,
      extraPdfFile: trademarkDecree.extraPdfFile,
      extraPdfFileContentType: trademarkDecree.extraPdfFileContentType,
      extraPdfFileUrl: trademarkDecree.extraPdfFileUrl,
      isWithdrawal: trademarkDecree.isWithdrawal,
      withdrawalDecreeNo: trademarkDecree.withdrawalDecreeNo,
      notes: trademarkDecree.notes,
    });
  }

  protected createFromForm(): ITrademarkDecree {
    return {
      ...new TrademarkDecree(),
      id: this.editForm.get(['id'])!.value,
      year: this.editForm.get(['year'])!.value,
      decreeNo: this.editForm.get(['decreeNo'])!.value,
      isAccepted: this.editForm.get(['isAccepted'])!.value,
      decreeDate: this.editForm.get(['decreeDate'])!.value,
      applicantName: this.editForm.get(['applicantName'])!.value,
      tradeMarkOwner: this.editForm.get(['tradeMarkOwner'])!.value,
      country: this.editForm.get(['country'])!.value,
      applyDate: this.editForm.get(['applyDate'])!.value,
      serialNo: this.editForm.get(['serialNo'])!.value,
      trademarkEnglish: this.editForm.get(['trademarkEnglish'])!.value,
      trademarkArabic: this.editForm.get(['trademarkArabic'])!.value,
      category: this.editForm.get(['category'])!.value,
      pdfFileContentType: this.editForm.get(['pdfFileContentType'])!.value,
      pdfFile: this.editForm.get(['pdfFile'])!.value,
      pdfFileUrl: this.editForm.get(['pdfFileUrl'])!.value,
      extraPdfFileContentType: this.editForm.get(['extraPdfFileContentType'])!.value,
      extraPdfFile: this.editForm.get(['extraPdfFile'])!.value,
      extraPdfFileUrl: this.editForm.get(['extraPdfFileUrl'])!.value,
      isWithdrawal: this.editForm.get(['isWithdrawal'])!.value,
      withdrawalDecreeNo: this.editForm.get(['withdrawalDecreeNo'])!.value,
      notes: this.editForm.get(['notes'])!.value,
    };
  }
}
