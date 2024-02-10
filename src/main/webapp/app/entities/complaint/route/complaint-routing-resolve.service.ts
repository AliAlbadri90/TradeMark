import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IComplaint, Complaint } from '../complaint.model';
import { ComplaintService } from '../service/complaint.service';

@Injectable({ providedIn: 'root' })
export class ComplaintRoutingResolveService implements Resolve<IComplaint> {
  constructor(protected service: ComplaintService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IComplaint> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.findPublic(id).pipe(
        mergeMap((complaint: HttpResponse<Complaint>) => {
          if (complaint.body) {
            return of(complaint.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Complaint());
  }
}
