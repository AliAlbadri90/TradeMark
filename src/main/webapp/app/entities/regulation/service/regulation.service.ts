import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { SearchWithPagination } from 'app/core/request/request.model';
import { IRegulation, getRegulationIdentifier } from '../regulation.model';

export type EntityResponseType = HttpResponse<IRegulation>;
export type EntityArrayResponseType = HttpResponse<IRegulation[]>;

@Injectable({ providedIn: 'root' })
export class RegulationService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/regulations');
  protected resourcePublicUrl = this.applicationConfigService.getEndpointFor('api/public/regulations');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/_search/regulations');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(regulation: IRegulation): Observable<EntityResponseType> {
    return this.http.post<IRegulation>(this.resourceUrl, regulation, { observe: 'response' });
  }

  update(regulation: IRegulation): Observable<EntityResponseType> {
    return this.http.put<IRegulation>(`${this.resourceUrl}/${getRegulationIdentifier(regulation) as number}`, regulation, {
      observe: 'response',
    });
  }

  partialUpdate(regulation: IRegulation): Observable<EntityResponseType> {
    return this.http.patch<IRegulation>(`${this.resourceUrl}/${getRegulationIdentifier(regulation) as number}`, regulation, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRegulation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRegulation[]>(this.resourcePublicUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRegulation[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }

  addRegulationToCollectionIfMissing(
    regulationCollection: IRegulation[],
    ...regulationsToCheck: (IRegulation | null | undefined)[]
  ): IRegulation[] {
    const regulations: IRegulation[] = regulationsToCheck.filter(isPresent);
    if (regulations.length > 0) {
      const regulationCollectionIdentifiers = regulationCollection.map(regulationItem => getRegulationIdentifier(regulationItem)!);
      const regulationsToAdd = regulations.filter(regulationItem => {
        const regulationIdentifier = getRegulationIdentifier(regulationItem);
        if (regulationIdentifier == null || regulationCollectionIdentifiers.includes(regulationIdentifier)) {
          return false;
        }
        regulationCollectionIdentifiers.push(regulationIdentifier);
        return true;
      });
      return [...regulationsToAdd, ...regulationCollection];
    }
    return regulationCollection;
  }
}
