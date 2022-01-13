import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMinister } from '../minister.model';

@Component({
  selector: 'jhi-minister-detail',
  templateUrl: './minister-detail.component.html',
})
export class MinisterDetailComponent implements OnInit {
  minister: IMinister | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ minister }) => {
      this.minister = minister;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
