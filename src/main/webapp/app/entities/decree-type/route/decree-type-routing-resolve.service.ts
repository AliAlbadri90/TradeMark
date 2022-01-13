import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDecreeType, DecreeType } from '../decree-type.model';
import { DecreeTypeService } from '../service/decree-type.service';

@Injectable({ providedIn: 'root' })
export class DecreeTypeRoutingResolveService implements Resolve<IDecreeType> {
  constructor(protected service: DecreeTypeService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDecreeType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((decreeType: HttpResponse<DecreeType>) => {
          if (decreeType.body) {
            return of(decreeType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DecreeType());
  }
}
