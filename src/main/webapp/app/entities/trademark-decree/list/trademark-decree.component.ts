import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITrademarkDecree } from '../trademark-decree.model';

import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/config/pagination.constants';
import { TrademarkDecreeService } from '../service/trademark-decree.service';
import { TrademarkDecreeDeleteDialogComponent } from '../delete/trademark-decree-delete-dialog.component';
import { DataUtils } from 'app/core/util/data-util.service';
import { map } from 'rxjs/operators';
import { IMinister } from '../../minister/minister.model';
import { IDecree } from '../../decree/decree.model';

@Component({
  selector: 'jhi-trademark-decree',
  templateUrl: './trademark-decree.component.html',
})
export class TrademarkDecreeComponent implements OnInit {
  trademarkDecrees?: ITrademarkDecree[];
  currentSearch: any = '';
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  years: any[] = [];
  year: any = '';

  constructor(
    protected trademarkDecreeService: TrademarkDecreeService,
    protected activatedRoute: ActivatedRoute,
    protected dataUtils: DataUtils,
    protected router: Router,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 0;

    this.trademarkDecreeService
      .query({
        'decreeNo.contains': this.currentSearch,
        'tradeMarkOwner.contains': this.currentSearch,
        'country.contains': this.currentSearch,
        'applicantName.contains': this.currentSearch,
        'serialNo.contains': this.currentSearch,
        'trademarkEnglish.contains': this.currentSearch,
        'trademarkArabic.contains': this.currentSearch,
        'notes.contains': this.currentSearch,
        'year.equals': this.year,
        page: pageToLoad,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<ITrademarkDecree[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        error: () => {
          this.isLoading = false;
          this.onError();
        },
      });
  }

  search(currentSearch: any): void {
    this.currentSearch = currentSearch;
    this.trademarkDecreeService
      .query({
        'decreeNo.contains': this.currentSearch,
        'tradeMarkOwner.contains': this.currentSearch,
        'country.contains': this.currentSearch,
        'applicantName.contains': this.currentSearch,
        'serialNo.contains': this.currentSearch,
        'trademarkEnglish.contains': this.currentSearch,
        'trademarkArabic.contains': this.currentSearch,
        'notes.contains': this.currentSearch,
        'year.equals': this.year,
        page: 0,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<ITrademarkDecree[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, 0, true);
        },
        error: () => {
          this.isLoading = false;
          this.onError();
        },
      });
  }

  ngOnInit(): void {
    this.trademarkDecreeService
      .getYears()
      .pipe(map((res: HttpResponse<any[]>) => res.body as string[]))
      .subscribe((years: any[]) => (this.years = years as string[]));

    this.handleNavigation();
  }

  trackId(index: number, item: ITrademarkDecree): number {
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(fileUrl: any): void {
    window.open('/api/public/file/download/' + String(fileUrl) + '#zoom=85&scrollbar=0&toolbar=0&navpanes=0', '_blank');
  }

  delete(trademarkDecree: ITrademarkDecree): void {
    const modalRef = this.modalService.open(TrademarkDecreeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.trademarkDecree = trademarkDecree;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }
    });
  }

  filterByYear(year: any): void {
    this.year = year;
    this.loadPage(0);
  }

  protected sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? ASC : DESC)];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).subscribe(([data, params]) => {
      const page = params.get('page');
      const pageNumber = +(page ?? 1);
      const sort = (params.get(SORT) ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === ASC;
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    });
  }

  protected onSuccess(data: ITrademarkDecree[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/trademark-decree'], {
        queryParams: {
          'decreeNo.contains': this.currentSearch,
          'tradeMarkOwner.contains': this.currentSearch,
          'country.contains': this.currentSearch,
          'applicantName.contains': this.currentSearch,
          'serialNo.contains': this.currentSearch,
          'trademarkEnglish.contains': this.currentSearch,
          'trademarkArabic.contains': this.currentSearch,
          'notes.contains': this.currentSearch,
          'year.equals': this.year,
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? ASC : DESC),
        },
      });
    }
    this.trademarkDecrees = data ?? [];
    this.ngbPaginationPage = this.page;
  }
  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
}
