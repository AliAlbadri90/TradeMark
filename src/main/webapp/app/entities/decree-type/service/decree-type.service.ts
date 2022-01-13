import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDecreeType, getDecreeTypeIdentifier } from '../decree-type.model';

export type EntityResponseType = HttpResponse<IDecreeType>;
export type EntityArrayResponseType = HttpResponse<IDecreeType[]>;

@Injectable({ providedIn: 'root' })
export class DecreeTypeService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/decree-types');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(decreeType: IDecreeType): Observable<EntityResponseType> {
    return this.http.post<IDecreeType>(this.resourceUrl, decreeType, { observe: 'response' });
  }

  update(decreeType: IDecreeType): Observable<EntityResponseType> {
    return this.http.put<IDecreeType>(`${this.resourceUrl}/${getDecreeTypeIdentifier(decreeType) as number}`, decreeType, {
      observe: 'response',
    });
  }

  partialUpdate(decreeType: IDecreeType): Observable<EntityResponseType> {
    return this.http.patch<IDecreeType>(`${this.resourceUrl}/${getDecreeTypeIdentifier(decreeType) as number}`, decreeType, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDecreeType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDecreeType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDecreeTypeToCollectionIfMissing(
    decreeTypeCollection: IDecreeType[],
    ...decreeTypesToCheck: (IDecreeType | null | undefined)[]
  ): IDecreeType[] {
    const decreeTypes: IDecreeType[] = decreeTypesToCheck.filter(isPresent);
    if (decreeTypes.length > 0) {
      const decreeTypeCollectionIdentifiers = decreeTypeCollection.map(decreeTypeItem => getDecreeTypeIdentifier(decreeTypeItem)!);
      const decreeTypesToAdd = decreeTypes.filter(decreeTypeItem => {
        const decreeTypeIdentifier = getDecreeTypeIdentifier(decreeTypeItem);
        if (decreeTypeIdentifier == null || decreeTypeCollectionIdentifiers.includes(decreeTypeIdentifier)) {
          return false;
        }
        decreeTypeCollectionIdentifiers.push(decreeTypeIdentifier);
        return true;
      });
      return [...decreeTypesToAdd, ...decreeTypeCollection];
    }
    return decreeTypeCollection;
  }
}
