import { Component, OnInit } from '@angular/core';
import { DecreeService } from '../decree/service/decree.service';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent {
  decreeCount: any = 0;

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

  constructor(private decreeService: DecreeService) {}

  ngOnInit(): void {
    this.decreeService.count().subscribe((res: any) => {
      this.decreeCount = res.body;
    });

    this.decreeService.getDecreeLineChart().subscribe((res: any) => {
      /*eslint-disable */
      this.companyLineChartLabels = res.body.map((it: any) => it[0]);
      this.companyLineChartData = [
        {
          data: res.body.map((it: any) => it[1]),
          label: 'حركة تسجيل القرارات',
        },
      ];
    });
  }
}
