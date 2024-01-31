import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRegulation } from '../regulation.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-regulation-detail',
  templateUrl: './regulation-detail.component.html',
})
export class RegulationDetailComponent implements OnInit {
  regulation: IRegulation | null = null;

  constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ regulation }) => {
      this.regulation = regulation;
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  previousState(): void {
    window.history.back();
  }
}
