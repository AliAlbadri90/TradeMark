import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IMinister, Minister } from '../minister.model';
import { MinisterService } from '../service/minister.service';

@Component({
  selector: 'jhi-minister-update',
  templateUrl: './minister-update.component.html',
})
export class MinisterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    serialNo: [],
    jobTitle: [],
  });

  constructor(protected ministerService: MinisterService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ minister }) => {
      this.updateForm(minister);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const minister = this.createFromForm();
    if (minister.id !== undefined) {
      this.subscribeToSaveResponse(this.ministerService.update(minister));
    } else {
      this.subscribeToSaveResponse(this.ministerService.create(minister));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMinister>>): void {
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

  protected updateForm(minister: IMinister): void {
    this.editForm.patchValue({
      id: minister.id,
      name: minister.name,
      serialNo: minister.serialNo,
      jobTitle: minister.jobTitle,
    });
  }

  protected createFromForm(): IMinister {
    return {
      ...new Minister(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      serialNo: this.editForm.get(['serialNo'])!.value,
      jobTitle: this.editForm.get(['jobTitle'])!.value,
    };
  }
}
