import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGovernment } from '../government.model';

@Component({
  selector: 'jhi-government-detail',
  templateUrl: './government-detail.component.html',
})
export class GovernmentDetailComponent implements OnInit {
  government: IGovernment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ government }) => {
      this.government = government;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
