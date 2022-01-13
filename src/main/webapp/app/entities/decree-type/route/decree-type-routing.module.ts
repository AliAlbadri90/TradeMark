import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DecreeTypeComponent } from '../list/decree-type.component';
import { DecreeTypeDetailComponent } from '../detail/decree-type-detail.component';
import { DecreeTypeUpdateComponent } from '../update/decree-type-update.component';
import { DecreeTypeRoutingResolveService } from './decree-type-routing-resolve.service';

const decreeTypeRoute: Routes = [
  {
    path: '',
    component: DecreeTypeComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DecreeTypeDetailComponent,
    resolve: {
      decreeType: DecreeTypeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DecreeTypeUpdateComponent,
    resolve: {
      decreeType: DecreeTypeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DecreeTypeUpdateComponent,
    resolve: {
      decreeType: DecreeTypeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(decreeTypeRoute)],
  exports: [RouterModule],
})
export class DecreeTypeRoutingModule {}
