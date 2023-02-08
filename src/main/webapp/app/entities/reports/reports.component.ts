import { Component, OnInit } from '@angular/core';
import { DecreeService } from '../decree/service/decree.service';
import { HttpResponse } from '@angular/common/http';
import { DecreeReport } from '../decree/decree-report.model';
import { IMinister } from '../minister/minister.model';
import { MinisterService } from '../minister/service/minister.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'jhi-reports',
  templateUrl: './reports.component.html',
})
export class ReportsComponent implements OnInit {
  isLoading: any;
  year: any;
  ministerId: any;
  decreeReport: any;
  ministersSharedCollection: IMinister[] = [];
  years: any[] = [];

  constructor(protected ministerService: MinisterService, protected decreeService: DecreeService) {}

  ngOnInit(): void {
    this.decreeService
      .getYears()
      .pipe(map((res: HttpResponse<any[]>) => res.body as string[]))
      .subscribe((years: any[]) => (this.years = years as string[]));
  }

  getReport(): void {
    this.decreeReport = null;
    this.decreeService.getReport(this.year, this.ministerId).subscribe((decreeReportHttpResponse: HttpResponse<DecreeReport>) => {
      this.decreeReport = decreeReportHttpResponse.body;
    });
  }

  filterByYear(year: any): void {
    this.year = year;
    this.decreeService
      .getMinistersByYear(year)
      .pipe(map((res: HttpResponse<IMinister[]>) => res.body ?? []))
      .subscribe((ministers: IMinister[]) => (this.ministersSharedCollection = ministers));
  }
}
