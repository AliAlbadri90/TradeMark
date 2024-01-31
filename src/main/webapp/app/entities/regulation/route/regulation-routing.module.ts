import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RegulationComponent } from '../list/regulation.component';
import { RegulationDetailComponent } from '../detail/regulation-detail.component';
import { RegulationUpdateComponent } from '../update/regulation-update.component';
import { RegulationRoutingResolveService } from './regulation-routing-resolve.service';

const regulationRoute: Routes = [
  {
    path: '',
    component: RegulationComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RegulationDetailComponent,
    resolve: {
      regulation: RegulationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RegulationUpdateComponent,
    resolve: {
      regulation: RegulationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RegulationUpdateComponent,
    resolve: {
      regulation: RegulationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(regulationRoute)],
  exports: [RouterModule],
})
export class RegulationRoutingModule {}
