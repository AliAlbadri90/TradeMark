import { Component, OnInit } from '@angular/core';
import { DecreeService } from '../decree/service/decree.service';
import { MinisterService } from '../minister/service/minister.service';
import { GovernmentService } from '../government/service/government.service';
import { map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { IMinister } from '../minister/minister.model';
import { IGovernment } from '../government/government.model';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent {
  decreeCount: any = 0;
  ministerCount: any = 0;
  govCount: any = 0;

  companyLineChartData: Array<any> = [];
  companyLineChartLabels = [];
  companyLineChartColors: Array<any> = [
    {
      // grey
      backgroundColor: 'rgb(70,100,188)',
      borderColor: '#5272c3',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)',
    },
  ];

  ministerDecreesPieChartData: any;
  ministerDecreesPieChartLabels: any;
  public ministerDecreesData: Array<any> = [];
  public ministerDecreesLabelsData: Array<any> = [];

  govDecreesDoughnutChartData: any;
  govDecreesDoughnutChartLabels: any;
  public govDecreesData: Array<any> = [];
  public govDecreesLabelsData: Array<any> = [];

  constructor(
    private decreeService: DecreeService,
    private ministerService: MinisterService,
    private governmentService: GovernmentService
  ) {}

  ngOnInit(): void {
    this.decreeService.countPublic().subscribe((res: any) => {
      this.decreeCount = res.body;
    });

    this.governmentService.countPublic().subscribe((res: any) => {
      this.govCount = res.body;
    });

    this.ministerService.countPublic().subscribe((res: any) => {
      this.ministerCount = res.body;
    });

    this.decreeService.getDecreeLineChartPublic().subscribe((res: any) => {
      /*eslint-disable */
      this.companyLineChartLabels = res.body.map((it: any) => it[0]);
      this.companyLineChartData = [
        {
          data: res.body.map((it: any) => it[1]),
          label: 'القرارات المرقمنة',
        },
      ];
    });

    this.ministerService
      .queryPublic({ size: 200 })
      .pipe(map((res: HttpResponse<IMinister[]>) => res.body ?? []))
      .subscribe((ministers: IMinister[]) => {
        ministers.forEach((minister: IMinister) => {
          this.ministerDecreesData.push(minister.decreeCount);
          this.ministerDecreesLabelsData.push(minister.name);
        });
        this.ministerDecreesPieChartData = this.ministerDecreesData;
        this.ministerDecreesPieChartLabels = this.ministerDecreesLabelsData;
      });

    this.governmentService
      .queryPublic({ size: 200 })
      .pipe(map((res: HttpResponse<IGovernment[]>) => res.body ?? []))
      .subscribe((governments: IGovernment[]) => {
        governments.forEach((government: IGovernment) => {
          this.govDecreesData.push(government.decreeCount);
          this.govDecreesLabelsData.push(government.name);
        });
        this.govDecreesDoughnutChartData = this.govDecreesData;
        this.govDecreesDoughnutChartLabels = this.govDecreesLabelsData;
      });
  }
}
