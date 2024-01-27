import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { DecreeReport } from '../decree/decree-report.model';
import { map } from 'rxjs/operators';
import { TrademarkDecreeService } from '../trademark-decree/service/trademark-decree.service';

@Component({
  selector: 'jhi-reports',
  templateUrl: './reports.component.html',
})
export class ReportsComponent implements OnInit {
  isLoading: any;
  year: any;
  decreeReport: any;
  years: any[] = [];

  constructor(protected trademarkDecreeService: TrademarkDecreeService) {}

  ngOnInit(): void {
    this.trademarkDecreeService
      .getYears()
      .pipe(map((res: HttpResponse<any[]>) => res.body as string[]))
      .subscribe((years: any[]) => (this.years = years as string[]));
  }

  getReport(): void {
    this.decreeReport = null;
    this.trademarkDecreeService.getReport(this.year).subscribe((decreeReportHttpResponse: HttpResponse<DecreeReport>) => {
      this.decreeReport = decreeReportHttpResponse.body;
    });
  }

  filterByYear(year: any): void {
    this.year = year;
  }
}
