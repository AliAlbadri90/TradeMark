import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IViewLog } from '../view-log.model';

@Component({
  selector: 'jhi-view-log-detail',
  templateUrl: './view-log-detail.component.html',
})
export class ViewLogDetailComponent implements OnInit {
  viewLog: IViewLog | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ viewLog }) => {
      this.viewLog = viewLog;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
