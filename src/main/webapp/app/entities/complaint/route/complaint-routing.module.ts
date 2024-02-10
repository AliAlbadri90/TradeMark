import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ComplaintComponent } from '../list/complaint.component';
import { ComplaintDetailComponent } from '../detail/complaint-detail.component';
import { ComplaintUpdateComponent } from '../update/complaint-update.component';
import { ComplaintRoutingResolveService } from './complaint-routing-resolve.service';
import { ComplaintNewPublicComponent } from '../new-public/complaint-new-public.component';
import { ComplaintPrintComponent } from '../print/complaint-print.component';

const complaintRoute: Routes = [
  {
    path: '',
    component: ComplaintComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ComplaintDetailComponent,
    resolve: {
      complaint: ComplaintRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ComplaintUpdateComponent,
    resolve: {
      complaint: ComplaintRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ComplaintUpdateComponent,
    resolve: {
      complaint: ComplaintRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/complaint-print',
    component: ComplaintPrintComponent,
    resolve: {
      complaint: ComplaintRoutingResolveService,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(complaintRoute)],
  exports: [RouterModule],
})
export class ComplaintRoutingModule {}
