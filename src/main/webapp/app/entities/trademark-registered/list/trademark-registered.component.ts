import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITrademarkRegistered } from '../trademark-registered.model';

import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/config/pagination.constants';
import { TrademarkRegisteredService } from '../service/trademark-registered.service';
import { TrademarkRegisteredDeleteDialogComponent } from '../delete/trademark-registered-delete-dialog.component';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-trademark-registered',
  templateUrl: './trademark-registered.component.html',
})
export class TrademarkRegisteredComponent implements OnInit {
  trademarkRegistereds?: ITrademarkRegistered[];
  currentSearch: string;
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 0;
  searchType = 'matching';
  selectedColumn = 'all';

  constructor(
    protected trademarkRegisteredService: TrademarkRegisteredService,
    protected activatedRoute: ActivatedRoute,
    protected dataUtils: DataUtils,
    protected router: Router,
    protected modalService: NgbModal
  ) {
    this.currentSearch = this.activatedRoute.snapshot.queryParams['search'] ?? '';
  }

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 0;

    if (this.currentSearch) {
      this.trademarkRegisteredService
        .searchPublic({
          page: pageToLoad,
          query: this.currentSearch,
          searchType: this.searchType,
          selectedColumn: this.selectedColumn,
          size: this.itemsPerPage,
          sort: this.sort(),
        })
        .subscribe({
          next: (res: HttpResponse<ITrademarkRegistered[]>) => {
            this.isLoading = false;
            this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
          },
          error: () => {
            this.isLoading = false;
            this.onError();
          },
        });
      return;
    }

    this.trademarkRegisteredService
      .query({
        page: pageToLoad,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<ITrademarkRegistered[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        error: () => {
          this.isLoading = false;
          this.onError();
        },
      });
  }

  search(query: string): void {
    if (
      query &&
      [
        'trademarkNo',
        'decreeNo',
        'applicantName',
        'tradeMarkOwner',
        'country',
        'nationality',
        'address',
        'trademarkEnglish',
        'trademarkArabic',
        'category',
        'file',
        'fileUrl',
        'extraFile',
        'extraFileUrl',
        'notes',
      ].includes(this.predicate)
    ) {
      this.predicate = 'id';
      this.ascending = true;
    }
    this.currentSearch = query;
    this.loadPage(0);
  }

  ngOnInit(): void {
    this.handleNavigation();
  }

  trackId(index: number, item: ITrademarkRegistered): number {
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    return this.dataUtils.openFile(base64String, contentType);
  }

  openPdfFile(id: any): void {
    window.open('/api/public/trademark-registereds/print/' + String(id) + '#zoom=85&scrollbar=0&toolbar=0&navpanes=0', '_blank');
  }

  delete(trademarkRegistered: ITrademarkRegistered): void {
    const modalRef = this.modalService.open(TrademarkRegisteredDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.trademarkRegistered = trademarkRegistered;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }
    });
  }
  openFilePdf(fileUrl: any): void {
    window.open('/api/public/file/download/' + String(fileUrl) + '#zoom=85&scrollbar=0&toolbar=0&navpanes=0', '_blank');
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
      const pageNumber = +(page ?? 0);
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

  protected onSuccess(data: ITrademarkRegistered[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.ngbPaginationPage = this.page;
    if (navigate) {
      this.router.navigate(['/trademark-registered'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          search: this.currentSearch,
          sort: this.predicate + ',' + (this.ascending ? ASC : DESC),
        },
      });
    }
    this.trademarkRegistereds = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 0;
  }
}
