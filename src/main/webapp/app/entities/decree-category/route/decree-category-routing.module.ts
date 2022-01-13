import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DecreeCategoryComponent } from '../list/decree-category.component';
import { DecreeCategoryDetailComponent } from '../detail/decree-category-detail.component';
import { DecreeCategoryUpdateComponent } from '../update/decree-category-update.component';
import { DecreeCategoryRoutingResolveService } from './decree-category-routing-resolve.service';

const decreeCategoryRoute: Routes = [
  {
    path: '',
    component: DecreeCategoryComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DecreeCategoryDetailComponent,
    resolve: {
      decreeCategory: DecreeCategoryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DecreeCategoryUpdateComponent,
    resolve: {
      decreeCategory: DecreeCategoryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DecreeCategoryUpdateComponent,
    resolve: {
      decreeCategory: DecreeCategoryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(decreeCategoryRoute)],
  exports: [RouterModule],
})
export class DecreeCategoryRoutingModule {}
