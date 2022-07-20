import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ViewLogComponent } from '../list/view-log.component';
import { ViewLogDetailComponent } from '../detail/view-log-detail.component';
import { ViewLogUpdateComponent } from '../update/view-log-update.component';
import { ViewLogRoutingResolveService } from './view-log-routing-resolve.service';

const viewLogRoute: Routes = [
  {
    path: '',
    component: ViewLogComponent,
    data: {
      defaultSort: 'id,desc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ViewLogDetailComponent,
    resolve: {
      viewLog: ViewLogRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ViewLogUpdateComponent,
    resolve: {
      viewLog: ViewLogRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ViewLogUpdateComponent,
    resolve: {
      viewLog: ViewLogRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(viewLogRoute)],
  exports: [RouterModule],
})
export class ViewLogRoutingModule {}
