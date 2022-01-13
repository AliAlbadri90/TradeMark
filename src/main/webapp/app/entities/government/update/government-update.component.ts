import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IGovernment, Government } from '../government.model';
import { GovernmentService } from '../service/government.service';

@Component({
  selector: 'jhi-government-update',
  templateUrl: './government-update.component.html',
})
export class GovernmentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    serialNo: [],
  });

  constructor(protected governmentService: GovernmentService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ government }) => {
      this.updateForm(government);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const government = this.createFromForm();
    if (government.id !== undefined) {
      this.subscribeToSaveResponse(this.governmentService.update(government));
    } else {
      this.subscribeToSaveResponse(this.governmentService.create(government));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGovernment>>): void {
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

  protected updateForm(government: IGovernment): void {
    this.editForm.patchValue({
      id: government.id,
      name: government.name,
      serialNo: government.serialNo,
    });
  }

  protected createFromForm(): IGovernment {
    return {
      ...new Government(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      serialNo: this.editForm.get(['serialNo'])!.value,
    };
  }
}
