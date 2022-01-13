import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDecreeCategory } from '../decree-category.model';

@Component({
  selector: 'jhi-decree-category-detail',
  templateUrl: './decree-category-detail.component.html',
})
export class DecreeCategoryDetailComponent implements OnInit {
  decreeCategory: IDecreeCategory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ decreeCategory }) => {
      this.decreeCategory = decreeCategory;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
