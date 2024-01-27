import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITrademarkRegistered, TrademarkRegistered } from '../trademark-registered.model';
import { TrademarkRegisteredService } from '../service/trademark-registered.service';

@Injectable({ providedIn: 'root' })
export class TrademarkRegisteredRoutingResolveService implements Resolve<ITrademarkRegistered> {
  constructor(protected service: TrademarkRegisteredService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITrademarkRegistered> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((trademarkRegistered: HttpResponse<TrademarkRegistered>) => {
          if (trademarkRegistered.body) {
            return of(trademarkRegistered.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TrademarkRegistered());
  }
}
