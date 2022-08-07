import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDecree } from '../decree.model';

import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/config/pagination.constants';
import { DecreeService } from '../service/decree.service';
import { DecreeDeleteDialogComponent } from '../delete/decree-delete-dialog.component';
import { DataUtils } from 'app/core/util/data-util.service';
import { IMinister, Minister } from '../../minister/minister.model';
import { map } from 'rxjs/operators';
import { MinisterService } from '../../minister/service/minister.service';

@Component({
  selector: 'jhi-decree',
  templateUrl: './decree.component.html',
})
export class DecreeComponent implements OnInit {
  decrees?: IDecree[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 0;
  currentSearch: any = '';
  year: any = '';
  ministerId: any = '';
  ministersSharedCollection: IMinister[] = [];
  years: any[] = [];

  constructor(
    protected decreeService: DecreeService,
    protected activatedRoute: ActivatedRoute,
    protected dataUtils: DataUtils,
    protected router: Router,
    protected ministerService: MinisterService,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 0;

    this.decreeService
      .query({
        'decreeNo.contains': this.currentSearch,
        'keywords.contains': this.currentSearch,
        'title.contains': this.currentSearch,
        'details.contains': this.currentSearch,
        'notes.contains': this.currentSearch,
        'ministerId.equals': this.ministerId,
        'year.equals': this.year,
        page: pageToLoad,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<IDecree[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        error: () => {
          this.isLoading = false;
          this.onError();
        },
      });
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.ministerService
      .query({ sort: ['name' + ',' + 'asc'] })
      .pipe(map((res: HttpResponse<IMinister[]>) => res.body ?? []))
      .subscribe((ministers: IMinister[]) => (this.ministersSharedCollection = ministers));

    this.decreeService
      .getYears()
      .pipe(map((res: HttpResponse<any[]>) => res.body as string[]))
      .subscribe((years: any[]) => (this.years = years as string[]));
  }

  trackId(index: number, item: IDecree): number {
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(fileUrl: any): void {
    window.open('/api/public/file/download/' + String(fileUrl) + '#zoom=85&scrollbar=0&toolbar=0&navpanes=0', '_blank');
  }

  delete(decree: IDecree): void {
    const modalRef = this.modalService.open(DecreeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.decree = decree;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }
    });
  }

  search(currentSearch: any): void {
    this.currentSearch = currentSearch;
    this.decreeService
      .query({
        'decreeNo.contains': currentSearch,
        'keywords.contains': currentSearch,
        'title.contains': this.currentSearch,
        'details.contains': this.currentSearch,
        'notes.contains': this.currentSearch,
        'ministerId.equals': this.ministerId,
        'year.equals': this.year,
        page: 0,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<IDecree[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, 0, true);
        },
        error: () => {
          this.isLoading = false;
          this.onError();
        },
      });
  }

  filterByYear(year: any): void {
    this.year = year;
    if (year !== '') {
      this.decreeService
        .getMinistersByYear(year)
        .pipe(map((res: HttpResponse<IMinister[]>) => res.body ?? []))
        .subscribe((ministers: IMinister[]) => (this.ministersSharedCollection = ministers));
    } else {
      this.ministerService
        .query({ sort: ['name' + ',' + 'asc'] })
        .pipe(map((res: HttpResponse<IMinister[]>) => res.body ?? []))
        .subscribe((ministers: IMinister[]) => (this.ministersSharedCollection = ministers));
    }
    this.loadPage(0);
  }

  filterByMinister(minister: any): void {
    this.ministerId = minister;
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

  protected onSuccess(data: IDecree[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/decree'], {
        queryParams: {
          'decreeNo.contains': this.currentSearch,
          'keywords.contains': this.currentSearch,
          'title.contains': this.currentSearch,
          'details.contains': this.currentSearch,
          'notes.contains': this.currentSearch,
          'ministerId.equals': this.ministerId,
          'year.equals': this.year,
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? ASC : DESC),
        },
      });
    }
    this.decrees = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 0;
  }
}
