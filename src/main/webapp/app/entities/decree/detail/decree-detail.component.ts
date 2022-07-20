import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDecree } from '../decree.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-decree-detail',
  templateUrl: './decree-detail.component.html',
})
export class DecreeDetailComponent implements OnInit {
  decree: IDecree | null = null;

  constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ decree }) => {
      this.decree = decree;
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(fileUrl: any): void {
    window.open('/api/public/file/download/' + String(fileUrl) + '#zoom=85&scrollbar=0&toolbar=0&navpanes=0', '_blank');
  }

  previousState(): void {
    window.history.back();
  }
}
