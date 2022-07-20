import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IViewLog, ViewLog } from '../view-log.model';
import { ViewLogService } from '../service/view-log.service';

@Injectable({ providedIn: 'root' })
export class ViewLogRoutingResolveService implements Resolve<IViewLog> {
  constructor(protected service: ViewLogService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IViewLog> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((viewLog: HttpResponse<ViewLog>) => {
          if (viewLog.body) {
            return of(viewLog.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ViewLog());
  }
}
