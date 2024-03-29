import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDecreeCategory, DecreeCategory } from '../decree-category.model';
import { DecreeCategoryService } from '../service/decree-category.service';

@Injectable({ providedIn: 'root' })
export class DecreeCategoryRoutingResolveService implements Resolve<IDecreeCategory> {
  constructor(protected service: DecreeCategoryService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDecreeCategory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((decreeCategory: HttpResponse<DecreeCategory>) => {
          if (decreeCategory.body) {
            return of(decreeCategory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DecreeCategory());
  }
}
