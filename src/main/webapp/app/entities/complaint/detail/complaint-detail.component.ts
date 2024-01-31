import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComplaint } from '../complaint.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-complaint-detail',
  templateUrl: './complaint-detail.component.html',
})
export class ComplaintDetailComponent implements OnInit {
  complaint: IComplaint | null = null;

  constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ complaint }) => {
      this.complaint = complaint;
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
