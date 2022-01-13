import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MinisterComponent } from '../list/minister.component';
import { MinisterDetailComponent } from '../detail/minister-detail.component';
import { MinisterUpdateComponent } from '../update/minister-update.component';
import { MinisterRoutingResolveService } from './minister-routing-resolve.service';

const ministerRoute: Routes = [
  {
    path: '',
    component: MinisterComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MinisterDetailComponent,
    resolve: {
      minister: MinisterRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MinisterUpdateComponent,
    resolve: {
      minister: MinisterRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MinisterUpdateComponent,
    resolve: {
      minister: MinisterRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ministerRoute)],
  exports: [RouterModule],
})
export class MinisterRoutingModule {}
