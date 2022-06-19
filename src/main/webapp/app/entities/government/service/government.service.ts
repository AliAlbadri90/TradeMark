import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IGovernment, getGovernmentIdentifier } from '../government.model';

export type EntityResponseType = HttpResponse<IGovernment>;
export type EntityArrayResponseType = HttpResponse<IGovernment[]>;

@Injectable({ providedIn: 'root' })
export class GovernmentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/governments');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(government: IGovernment): Observable<EntityResponseType> {
    return this.http.post<IGovernment>(this.resourceUrl, government, { observe: 'response' });
  }

  update(government: IGovernment): Observable<EntityResponseType> {
    return this.http.put<IGovernment>(`${this.resourceUrl}/${getGovernmentIdentifier(government) as number}`, government, {
      observe: 'response',
    });
  }

  partialUpdate(government: IGovernment): Observable<EntityResponseType> {
    return this.http.patch<IGovernment>(`${this.resourceUrl}/${getGovernmentIdentifier(government) as number}`, government, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGovernment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGovernment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addGovernmentToCollectionIfMissing(
    governmentCollection: IGovernment[],
    ...governmentsToCheck: (IGovernment | null | undefined)[]
  ): IGovernment[] {
    const governments: IGovernment[] = governmentsToCheck.filter(isPresent);
    if (governments.length > 0) {
      const governmentCollectionIdentifiers = governmentCollection.map(governmentItem => getGovernmentIdentifier(governmentItem)!);
      const governmentsToAdd = governments.filter(governmentItem => {
        const governmentIdentifier = getGovernmentIdentifier(governmentItem);
        if (governmentIdentifier == null || governmentCollectionIdentifiers.includes(governmentIdentifier)) {
          return false;
        }
        governmentCollectionIdentifiers.push(governmentIdentifier);
        return true;
      });
      return [...governmentsToAdd, ...governmentCollection];
    }
    return governmentCollection;
  }

  count(req?: any): Observable<HttpResponse<any>> {
    const options = createRequestOption(req);
    return this.http.get<number>(this.resourceUrl + '/count', { params: options, observe: 'response' });
  }
}
