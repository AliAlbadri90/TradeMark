import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IRegulation, Regulation } from '../regulation.model';
import { RegulationService } from '../service/regulation.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-regulation-update',
  templateUrl: './regulation-update.component.html',
})
export class RegulationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    title: [],
    description: [],
    type: [],
    year: [],
    filePdf: [],
    filePdfContentType: [],
    filePdfUrl: [],
    fileWord: [],
    fileWordContentType: [],
    fileWordUrl: [],
    isActive: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected regulationService: RegulationService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ regulation }) => {
      this.updateForm(regulation);
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
    const regulation = this.createFromForm();
    if (regulation.id !== undefined) {
      this.subscribeToSaveResponse(this.regulationService.update(regulation));
    } else {
      this.subscribeToSaveResponse(this.regulationService.create(regulation));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRegulation>>): void {
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

  protected updateForm(regulation: IRegulation): void {
    this.editForm.patchValue({
      id: regulation.id,
      title: regulation.title,
      description: regulation.description,
      type: regulation.type,
      year: regulation.year,
      filePdf: regulation.filePdf,
      filePdfContentType: regulation.filePdfContentType,
      filePdfUrl: regulation.filePdfUrl,
      fileWord: regulation.fileWord,
      fileWordContentType: regulation.fileWordContentType,
      fileWordUrl: regulation.fileWordUrl,
      isActive: regulation.isActive,
    });
  }

  protected createFromForm(): IRegulation {
    return {
      ...new Regulation(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      description: this.editForm.get(['description'])!.value,
      type: this.editForm.get(['type'])!.value,
      year: this.editForm.get(['year'])!.value,
      filePdfContentType: this.editForm.get(['filePdfContentType'])!.value,
      filePdf: this.editForm.get(['filePdf'])!.value,
      filePdfUrl: this.editForm.get(['filePdfUrl'])!.value,
      fileWordContentType: this.editForm.get(['fileWordContentType'])!.value,
      fileWord: this.editForm.get(['fileWord'])!.value,
      fileWordUrl: this.editForm.get(['fileWordUrl'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
    };
  }
}
