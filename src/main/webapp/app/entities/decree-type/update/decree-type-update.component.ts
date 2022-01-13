import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IDecreeType, DecreeType } from '../decree-type.model';
import { DecreeTypeService } from '../service/decree-type.service';

@Component({
  selector: 'jhi-decree-type-update',
  templateUrl: './decree-type-update.component.html',
})
export class DecreeTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    serialNo: [],
  });

  constructor(protected decreeTypeService: DecreeTypeService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ decreeType }) => {
      this.updateForm(decreeType);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const decreeType = this.createFromForm();
    if (decreeType.id !== undefined) {
      this.subscribeToSaveResponse(this.decreeTypeService.update(decreeType));
    } else {
      this.subscribeToSaveResponse(this.decreeTypeService.create(decreeType));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDecreeType>>): void {
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

  protected updateForm(decreeType: IDecreeType): void {
    this.editForm.patchValue({
      id: decreeType.id,
      name: decreeType.name,
      serialNo: decreeType.serialNo,
    });
  }

  protected createFromForm(): IDecreeType {
    return {
      ...new DecreeType(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      serialNo: this.editForm.get(['serialNo'])!.value,
    };
  }
}
