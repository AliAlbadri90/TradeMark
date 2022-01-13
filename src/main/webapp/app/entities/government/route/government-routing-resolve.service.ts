import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IGovernment, Government } from '../government.model';
import { GovernmentService } from '../service/government.service';

@Injectable({ providedIn: 'root' })
export class GovernmentRoutingResolveService implements Resolve<IGovernment> {
  constructor(protected service: GovernmentService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGovernment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((government: HttpResponse<Government>) => {
          if (government.body) {
            return of(government.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Government());
  }
}
