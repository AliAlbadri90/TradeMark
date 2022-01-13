import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IDecreeCategory, DecreeCategory } from '../decree-category.model';
import { DecreeCategoryService } from '../service/decree-category.service';

@Component({
  selector: 'jhi-decree-category-update',
  templateUrl: './decree-category-update.component.html',
})
export class DecreeCategoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    serialNo: [],
  });

  constructor(
    protected decreeCategoryService: DecreeCategoryService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ decreeCategory }) => {
      this.updateForm(decreeCategory);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const decreeCategory = this.createFromForm();
    if (decreeCategory.id !== undefined) {
      this.subscribeToSaveResponse(this.decreeCategoryService.update(decreeCategory));
    } else {
      this.subscribeToSaveResponse(this.decreeCategoryService.create(decreeCategory));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDecreeCategory>>): void {
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

  protected updateForm(decreeCategory: IDecreeCategory): void {
    this.editForm.patchValue({
      id: decreeCategory.id,
      name: decreeCategory.name,
      serialNo: decreeCategory.serialNo,
    });
  }

  protected createFromForm(): IDecreeCategory {
    return {
      ...new DecreeCategory(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      serialNo: this.editForm.get(['serialNo'])!.value,
    };
  }
}
