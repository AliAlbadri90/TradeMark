import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IViewLog, ViewLog } from '../view-log.model';
import { ViewLogService } from '../service/view-log.service';

@Component({
  selector: 'jhi-view-log-update',
  templateUrl: './view-log-update.component.html',
})
export class ViewLogUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    actionName: [],
    entityName: [],
    entityId: [],
  });

  constructor(protected viewLogService: ViewLogService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ viewLog }) => {
      this.updateForm(viewLog);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const viewLog = this.createFromForm();
    if (viewLog.id !== undefined) {
      this.subscribeToSaveResponse(this.viewLogService.update(viewLog));
    } else {
      this.subscribeToSaveResponse(this.viewLogService.create(viewLog));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IViewLog>>): void {
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

  protected updateForm(viewLog: IViewLog): void {
    this.editForm.patchValue({
      id: viewLog.id,
      actionName: viewLog.actionName,
      entityName: viewLog.entityName,
      entityId: viewLog.entityId,
    });
  }

  protected createFromForm(): IViewLog {
    return {
      ...new ViewLog(),
      id: this.editForm.get(['id'])!.value,
      actionName: this.editForm.get(['actionName'])!.value,
      entityName: this.editForm.get(['entityName'])!.value,
      entityId: this.editForm.get(['entityId'])!.value,
    };
  }
}
