import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'decree',
        data: { pageTitle: 'Decrees' },
        loadChildren: () => import('./decree/decree.module').then(m => m.DecreeModule),
      },
      {
        path: 'decree-type',
        data: { pageTitle: 'DecreeTypes' },
        loadChildren: () => import('./decree-type/decree-type.module').then(m => m.DecreeTypeModule),
      },
      {
        path: 'decree-category',
        data: { pageTitle: 'DecreeCategories' },
        loadChildren: () => import('./decree-category/decree-category.module').then(m => m.DecreeCategoryModule),
      },
      {
        path: 'minister',
        data: { pageTitle: 'Ministers' },
        loadChildren: () => import('./minister/minister.module').then(m => m.MinisterModule),
      },
      {
        path: 'government',
        data: { pageTitle: 'Governments' },
        loadChildren: () => import('./government/government.module').then(m => m.GovernmentModule),
      },
      {
        path: 'dashboard',
        data: { pageTitle: 'dashboard' },
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule),
      },
      {
        path: 'reports',
        loadChildren: () => import('./reports/reports.module').then(m => m.ReportsModule),
      },
      {
        path: 'view-log',
        data: { pageTitle: 'ViewLogs' },
        loadChildren: () => import('./view-log/view-log.module').then(m => m.ViewLogModule),
      },
      {
        path: 'trademark-decree',
        data: { pageTitle: 'TrademarkDecrees' },
        loadChildren: () => import('./trademark-decree/trademark-decree.module').then(m => m.TrademarkDecreeModule),
      },
      {
        path: 'trademark-registered',
        data: { pageTitle: 'TrademarkRegistereds' },
        loadChildren: () => import('./trademark-registered/trademark-registered.module').then(m => m.TrademarkRegisteredModule),
      },
      {
        path: 'regulation',
        data: { pageTitle: 'Regulations' },
        loadChildren: () => import('./regulation/regulation.module').then(m => m.RegulationModule),
      },
      {
        path: 'complaint',
        data: { pageTitle: 'Complaints' },
        loadChildren: () => import('./complaint/complaint.module').then(m => m.ComplaintModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
