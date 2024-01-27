import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrademarkRegistered } from '../trademark-registered.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-trademark-registered-detail',
  templateUrl: './trademark-registered-detail.component.html',
})
export class TrademarkRegisteredDetailComponent implements OnInit {
  trademarkRegistered: ITrademarkRegistered | null = null;

  constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trademarkRegistered }) => {
      this.trademarkRegistered = trademarkRegistered;
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
