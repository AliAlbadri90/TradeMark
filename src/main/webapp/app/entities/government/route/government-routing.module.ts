import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { GovernmentComponent } from '../list/government.component';
import { GovernmentDetailComponent } from '../detail/government-detail.component';
import { GovernmentUpdateComponent } from '../update/government-update.component';
import { GovernmentRoutingResolveService } from './government-routing-resolve.service';

const governmentRoute: Routes = [
  {
    path: '',
    component: GovernmentComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GovernmentDetailComponent,
    resolve: {
      government: GovernmentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GovernmentUpdateComponent,
    resolve: {
      government: GovernmentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GovernmentUpdateComponent,
    resolve: {
      government: GovernmentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(governmentRoute)],
  exports: [RouterModule],
})
export class GovernmentRoutingModule {}
