import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IDecree, Decree } from '../decree.model';
import { DecreeService } from '../service/decree.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { IDecreeType } from 'app/entities/decree-type/decree-type.model';
import { DecreeTypeService } from 'app/entities/decree-type/service/decree-type.service';
import { IDecreeCategory } from 'app/entities/decree-category/decree-category.model';
import { DecreeCategoryService } from 'app/entities/decree-category/service/decree-category.service';
import { IMinister } from 'app/entities/minister/minister.model';
import { MinisterService } from 'app/entities/minister/service/minister.service';
import { IGovernment } from 'app/entities/government/government.model';
import { GovernmentService } from 'app/entities/government/service/government.service';
import { DecreeStatus } from 'app/entities/enumerations/decree-status.model';

@Component({
  selector: 'jhi-decree-update',
  templateUrl: './decree-update.component.html',
})
export class DecreeUpdateComponent implements OnInit {
  isSaving = false;
  decreeStatusValues = Object.keys(DecreeStatus);

  decreeTypesSharedCollection: IDecreeType[] = [];
  decreeCategoriesSharedCollection: IDecreeCategory[] = [];
  ministersSharedCollection: IMinister[] = [];
  governmentsSharedCollection: IGovernment[] = [];

  editForm = this.fb.group({
    id: [],
    documentNo: [],
    decreeNo: [null, [Validators.required]],
    title: [],
    details: [],
    keywords: [],
    pages: [],
    decreeDate: [],
    year: [null, [Validators.required]],
    notes: [],
    pdfFile: [],
    pdfFileContentType: [],
    pdfFileUrl: [],
    wordFile: [],
    wordFileContentType: [],
    wordFileUrl: [],
    extraPdfFile: [],
    extraPdfFileContentType: [],
    extraPdfFileUrl: [],
    decreeStatus: [],
    remarks: [],
    isHidden: [],
    hideNotes: [],
    hideEndDate: [],
    decreeType: [],
    decreeCategory: [],
    minister: [],
    government: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected decreeService: DecreeService,
    protected decreeTypeService: DecreeTypeService,
    protected decreeCategoryService: DecreeCategoryService,
    protected ministerService: MinisterService,
    protected governmentService: GovernmentService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ decree }) => {
      this.updateForm(decree);

      this.loadRelationshipsOptions();
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
    const decree = this.createFromForm();
    if (decree.id !== undefined) {
      this.subscribeToSaveResponse(this.decreeService.update(decree));
    } else {
      this.subscribeToSaveResponse(this.decreeService.create(decree));
    }
  }

  trackDecreeTypeById(index: number, item: IDecreeType): number {
    return item.id!;
  }

  trackDecreeCategoryById(index: number, item: IDecreeCategory): number {
    return item.id!;
  }

  trackMinisterById(index: number, item: IMinister): number {
    return item.id!;
  }

  trackGovernmentById(index: number, item: IGovernment): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDecree>>): void {
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

  protected updateForm(decree: IDecree): void {
    this.editForm.patchValue({
      id: decree.id,
      documentNo: decree.documentNo,
      decreeNo: decree.decreeNo,
      title: decree.title,
      details: decree.details,
      keywords: decree.keywords,
      pages: decree.pages,
      decreeDate: decree.decreeDate,
      year: decree.year,
      notes: decree.notes,
      pdfFile: decree.pdfFile,
      pdfFileContentType: decree.pdfFileContentType,
      pdfFileUrl: decree.pdfFileUrl,
      wordFile: decree.wordFile,
      wordFileContentType: decree.wordFileContentType,
      wordFileUrl: decree.wordFileUrl,
      extraPdfFile: decree.extraPdfFile,
      extraPdfFileContentType: decree.extraPdfFileContentType,
      extraPdfFileUrl: decree.extraPdfFileUrl,
      decreeStatus: decree.decreeStatus,
      remarks: decree.remarks,
      isHidden: decree.isHidden,
      hideNotes: decree.hideNotes,
      hideEndDate: decree.hideEndDate,
      decreeType: decree.decreeType,
      decreeCategory: decree.decreeCategory,
      minister: decree.minister,
      government: decree.government,
    });

    this.decreeTypesSharedCollection = this.decreeTypeService.addDecreeTypeToCollectionIfMissing(
      this.decreeTypesSharedCollection,
      decree.decreeType
    );
    this.decreeCategoriesSharedCollection = this.decreeCategoryService.addDecreeCategoryToCollectionIfMissing(
      this.decreeCategoriesSharedCollection,
      decree.decreeCategory
    );
    this.ministersSharedCollection = this.ministerService.addMinisterToCollectionIfMissing(this.ministersSharedCollection, decree.minister);
    this.governmentsSharedCollection = this.governmentService.addGovernmentToCollectionIfMissing(
      this.governmentsSharedCollection,
      decree.government
    );
  }

  protected loadRelationshipsOptions(): void {
    this.decreeTypeService
      .query({ size: 200 })
      .pipe(map((res: HttpResponse<IDecreeType[]>) => res.body ?? []))
      .pipe(
        map((decreeTypes: IDecreeType[]) =>
          this.decreeTypeService.addDecreeTypeToCollectionIfMissing(decreeTypes, this.editForm.get('decreeType')!.value)
        )
      )
      .subscribe((decreeTypes: IDecreeType[]) => (this.decreeTypesSharedCollection = decreeTypes));

    this.decreeCategoryService
      .query({ size: 200 })
      .pipe(map((res: HttpResponse<IDecreeCategory[]>) => res.body ?? []))
      .pipe(
        map((decreeCategories: IDecreeCategory[]) =>
          this.decreeCategoryService.addDecreeCategoryToCollectionIfMissing(decreeCategories, this.editForm.get('decreeCategory')!.value)
        )
      )
      .subscribe((decreeCategories: IDecreeCategory[]) => (this.decreeCategoriesSharedCollection = decreeCategories));

    this.ministerService
      .query({ size: 200 })
      .pipe(map((res: HttpResponse<IMinister[]>) => res.body ?? []))
      .pipe(
        map((ministers: IMinister[]) =>
          this.ministerService.addMinisterToCollectionIfMissing(ministers, this.editForm.get('minister')!.value)
        )
      )
      .subscribe((ministers: IMinister[]) => (this.ministersSharedCollection = ministers));

    this.governmentService
      .query({ size: 200 })
      .pipe(map((res: HttpResponse<IGovernment[]>) => res.body ?? []))
      .pipe(
        map((governments: IGovernment[]) =>
          this.governmentService.addGovernmentToCollectionIfMissing(governments, this.editForm.get('government')!.value)
        )
      )
      .subscribe((governments: IGovernment[]) => (this.governmentsSharedCollection = governments));
  }

  protected createFromForm(): IDecree {
    return {
      ...new Decree(),
      id: this.editForm.get(['id'])!.value,
      documentNo: this.editForm.get(['documentNo'])!.value,
      decreeNo: this.editForm.get(['decreeNo'])!.value,
      title: this.editForm.get(['title'])!.value,
      details: this.editForm.get(['details'])!.value,
      keywords: this.editForm.get(['keywords'])!.value,
      pages: this.editForm.get(['pages'])!.value,
      decreeDate: this.editForm.get(['decreeDate'])!.value,
      year: this.editForm.get(['year'])!.value,
      notes: this.editForm.get(['notes'])!.value,
      pdfFileContentType: this.editForm.get(['pdfFileContentType'])!.value,
      pdfFile: this.editForm.get(['pdfFile'])!.value,
      pdfFileUrl: this.editForm.get(['pdfFileUrl'])!.value,
      wordFileContentType: this.editForm.get(['wordFileContentType'])!.value,
      wordFile: this.editForm.get(['wordFile'])!.value,
      wordFileUrl: this.editForm.get(['wordFileUrl'])!.value,
      extraPdfFileContentType: this.editForm.get(['extraPdfFileContentType'])!.value,
      extraPdfFile: this.editForm.get(['extraPdfFile'])!.value,
      extraPdfFileUrl: this.editForm.get(['extraPdfFileUrl'])!.value,
      decreeStatus: this.editForm.get(['decreeStatus'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
      isHidden: this.editForm.get(['isHidden'])!.value,
      hideNotes: this.editForm.get(['hideNotes'])!.value,
      hideEndDate: this.editForm.get(['hideEndDate'])!.value,
      decreeType: this.editForm.get(['decreeType'])!.value,
      decreeCategory: this.editForm.get(['decreeCategory'])!.value,
      minister: this.editForm.get(['minister'])!.value,
      government: this.editForm.get(['government'])!.value,
    };
  }
}
