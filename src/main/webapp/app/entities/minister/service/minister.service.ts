import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMinister, getMinisterIdentifier } from '../minister.model';

export type EntityResponseType = HttpResponse<IMinister>;
export type EntityArrayResponseType = HttpResponse<IMinister[]>;

@Injectable({ providedIn: 'root' })
export class MinisterService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ministers');
  protected resourceUrlPublic = this.applicationConfigService.getEndpointFor('api/public/ministers');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(minister: IMinister): Observable<EntityResponseType> {
    return this.http.post<IMinister>(this.resourceUrl, minister, { observe: 'response' });
  }

  update(minister: IMinister): Observable<EntityResponseType> {
    return this.http.put<IMinister>(`${this.resourceUrl}/${getMinisterIdentifier(minister) as number}`, minister, { observe: 'response' });
  }

  partialUpdate(minister: IMinister): Observable<EntityResponseType> {
    return this.http.patch<IMinister>(`${this.resourceUrl}/${getMinisterIdentifier(minister) as number}`, minister, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMinister>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMinister[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryPublic(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMinister[]>(this.resourceUrlPublic, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  countPublic(req?: any): Observable<HttpResponse<any>> {
    const options = createRequestOption(req);
    return this.http.get<number>(this.resourceUrlPublic + '/count', { params: options, observe: 'response' });
  }

  addMinisterToCollectionIfMissing(ministerCollection: IMinister[], ...ministersToCheck: (IMinister | null | undefined)[]): IMinister[] {
    const ministers: IMinister[] = ministersToCheck.filter(isPresent);
    if (ministers.length > 0) {
      const ministerCollectionIdentifiers = ministerCollection.map(ministerItem => getMinisterIdentifier(ministerItem)!);
      const ministersToAdd = ministers.filter(ministerItem => {
        const ministerIdentifier = getMinisterIdentifier(ministerItem);
        if (ministerIdentifier == null || ministerCollectionIdentifiers.includes(ministerIdentifier)) {
          return false;
        }
        ministerCollectionIdentifiers.push(ministerIdentifier);
        return true;
      });
      return [...ministersToAdd, ...ministerCollection];
    }
    return ministerCollection;
  }

  count(req?: any): Observable<HttpResponse<any>> {
    const options = createRequestOption(req);
    return this.http.get<number>(this.resourceUrl + '/count', { params: options, observe: 'response' });
  }
}
