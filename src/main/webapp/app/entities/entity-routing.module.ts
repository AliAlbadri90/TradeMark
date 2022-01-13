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
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
