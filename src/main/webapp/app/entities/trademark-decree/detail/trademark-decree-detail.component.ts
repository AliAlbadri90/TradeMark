import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrademarkDecree } from '../trademark-decree.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-trademark-decree-detail',
  templateUrl: './trademark-decree-detail.component.html',
})
export class TrademarkDecreeDetailComponent implements OnInit {
  trademarkDecree: ITrademarkDecree | null = null;

  constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trademarkDecree }) => {
      this.trademarkDecree = trademarkDecree;
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
