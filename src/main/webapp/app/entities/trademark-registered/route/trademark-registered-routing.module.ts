import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TrademarkRegisteredComponent } from '../list/trademark-registered.component';
import { TrademarkRegisteredDetailComponent } from '../detail/trademark-registered-detail.component';
import { TrademarkRegisteredUpdateComponent } from '../update/trademark-registered-update.component';
import { TrademarkRegisteredRoutingResolveService } from './trademark-registered-routing-resolve.service';
import { TrademarkRegisteredPublicComponent } from '../list-public/trademark-registered-public.component';
import { ComplaintNewPublicComponent } from '../../complaint/new-public/complaint-new-public.component';

const trademarkRegisteredRoute: Routes = [
  {
    path: '',
    component: TrademarkRegisteredComponent,
    data: {
      defaultSort: 'id,desc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'public',
    component: TrademarkRegisteredPublicComponent,
    data: {
      defaultSort: 'id,desc',
    },
    // canActivate: [UserRouteAccessService],
  },
  {
    path: 'public/:id/view',
    component: TrademarkRegisteredDetailComponent,
    resolve: {
      trademarkRegistered: TrademarkRegisteredRoutingResolveService,
    },
    // canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TrademarkRegisteredUpdateComponent,
    resolve: {
      trademarkRegistered: TrademarkRegisteredRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TrademarkRegisteredUpdateComponent,
    resolve: {
      trademarkRegistered: TrademarkRegisteredRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'public/complaint/:trademarkNo/trademark',
    component: ComplaintNewPublicComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(trademarkRegisteredRoute)],
  exports: [RouterModule],
})
export class TrademarkRegisteredRoutingModule {}
