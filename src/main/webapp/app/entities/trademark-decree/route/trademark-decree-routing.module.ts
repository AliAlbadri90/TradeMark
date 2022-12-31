import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TrademarkDecreeComponent } from '../list/trademark-decree.component';
import { TrademarkDecreeDetailComponent } from '../detail/trademark-decree-detail.component';
import { TrademarkDecreeUpdateComponent } from '../update/trademark-decree-update.component';
import { TrademarkDecreeRoutingResolveService } from './trademark-decree-routing-resolve.service';

const trademarkDecreeRoute: Routes = [
  {
    path: '',
    component: TrademarkDecreeComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TrademarkDecreeDetailComponent,
    resolve: {
      trademarkDecree: TrademarkDecreeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TrademarkDecreeUpdateComponent,
    resolve: {
      trademarkDecree: TrademarkDecreeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TrademarkDecreeUpdateComponent,
    resolve: {
      trademarkDecree: TrademarkDecreeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(trademarkDecreeRoute)],
  exports: [RouterModule],
})
export class TrademarkDecreeRoutingModule {}
