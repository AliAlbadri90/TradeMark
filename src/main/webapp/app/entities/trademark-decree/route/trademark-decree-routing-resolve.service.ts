import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITrademarkDecree, TrademarkDecree } from '../trademark-decree.model';
import { TrademarkDecreeService } from '../service/trademark-decree.service';

@Injectable({ providedIn: 'root' })
export class TrademarkDecreeRoutingResolveService implements Resolve<ITrademarkDecree> {
  constructor(protected service: TrademarkDecreeService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITrademarkDecree> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((trademarkDecree: HttpResponse<TrademarkDecree>) => {
          if (trademarkDecree.body) {
            return of(trademarkDecree.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TrademarkDecree());
  }
}
