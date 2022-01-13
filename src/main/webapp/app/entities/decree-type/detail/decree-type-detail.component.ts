import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDecreeType } from '../decree-type.model';

@Component({
  selector: 'jhi-decree-type-detail',
  templateUrl: './decree-type-detail.component.html',
})
export class DecreeTypeDetailComponent implements OnInit {
  decreeType: IDecreeType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ decreeType }) => {
      this.decreeType = decreeType;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
