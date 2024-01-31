import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRegulation, Regulation } from '../regulation.model';
import { RegulationService } from '../service/regulation.service';

@Injectable({ providedIn: 'root' })
export class RegulationRoutingResolveService implements Resolve<IRegulation> {
  constructor(protected service: RegulationService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRegulation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((regulation: HttpResponse<Regulation>) => {
          if (regulation.body) {
            return of(regulation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Regulation());
  }
}
