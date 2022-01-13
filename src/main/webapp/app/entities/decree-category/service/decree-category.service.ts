import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDecreeCategory, getDecreeCategoryIdentifier } from '../decree-category.model';

export type EntityResponseType = HttpResponse<IDecreeCategory>;
export type EntityArrayResponseType = HttpResponse<IDecreeCategory[]>;

@Injectable({ providedIn: 'root' })
export class DecreeCategoryService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/decree-categories');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(decreeCategory: IDecreeCategory): Observable<EntityResponseType> {
    return this.http.post<IDecreeCategory>(this.resourceUrl, decreeCategory, { observe: 'response' });
  }

  update(decreeCategory: IDecreeCategory): Observable<EntityResponseType> {
    return this.http.put<IDecreeCategory>(`${this.resourceUrl}/${getDecreeCategoryIdentifier(decreeCategory) as number}`, decreeCategory, {
      observe: 'response',
    });
  }

  partialUpdate(decreeCategory: IDecreeCategory): Observable<EntityResponseType> {
    return this.http.patch<IDecreeCategory>(
      `${this.resourceUrl}/${getDecreeCategoryIdentifier(decreeCategory) as number}`,
      decreeCategory,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDecreeCategory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDecreeCategory[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDecreeCategoryToCollectionIfMissing(
    decreeCategoryCollection: IDecreeCategory[],
    ...decreeCategoriesToCheck: (IDecreeCategory | null | undefined)[]
  ): IDecreeCategory[] {
    const decreeCategories: IDecreeCategory[] = decreeCategoriesToCheck.filter(isPresent);
    if (decreeCategories.length > 0) {
      const decreeCategoryCollectionIdentifiers = decreeCategoryCollection.map(
        decreeCategoryItem => getDecreeCategoryIdentifier(decreeCategoryItem)!
      );
      const decreeCategoriesToAdd = decreeCategories.filter(decreeCategoryItem => {
        const decreeCategoryIdentifier = getDecreeCategoryIdentifier(decreeCategoryItem);
        if (decreeCategoryIdentifier == null || decreeCategoryCollectionIdentifiers.includes(decreeCategoryIdentifier)) {
          return false;
        }
        decreeCategoryCollectionIdentifiers.push(decreeCategoryIdentifier);
        return true;
      });
      return [...decreeCategoriesToAdd, ...decreeCategoryCollection];
    }
    return decreeCategoryCollection;
  }
}
