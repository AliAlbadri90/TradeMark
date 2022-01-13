import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMinister, Minister } from '../minister.model';
import { MinisterService } from '../service/minister.service';

@Injectable({ providedIn: 'root' })
export class MinisterRoutingResolveService implements Resolve<IMinister> {
  constructor(protected service: MinisterService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMinister> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((minister: HttpResponse<Minister>) => {
          if (minister.body) {
            return of(minister.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Minister());
  }
}
