import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDecree, getDecreeIdentifier } from '../decree.model';

export type EntityResponseType = HttpResponse<IDecree>;
export type EntityArrayResponseType = HttpResponse<IDecree[]>;

@Injectable({ providedIn: 'root' })
export class DecreeService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/decrees');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(decree: IDecree): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(decree);
    return this.http
      .post<IDecree>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(decree: IDecree): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(decree);
    return this.http
      .put<IDecree>(`${this.resourceUrl}/${getDecreeIdentifier(decree) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(decree: IDecree): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(decree);
    return this.http
      .patch<IDecree>(`${this.resourceUrl}/${getDecreeIdentifier(decree) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDecree>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDecree[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDecreeToCollectionIfMissing(decreeCollection: IDecree[], ...decreesToCheck: (IDecree | null | undefined)[]): IDecree[] {
    const decrees: IDecree[] = decreesToCheck.filter(isPresent);
    if (decrees.length > 0) {
      const decreeCollectionIdentifiers = decreeCollection.map(decreeItem => getDecreeIdentifier(decreeItem)!);
      const decreesToAdd = decrees.filter(decreeItem => {
        const decreeIdentifier = getDecreeIdentifier(decreeItem);
        if (decreeIdentifier == null || decreeCollectionIdentifiers.includes(decreeIdentifier)) {
          return false;
        }
        decreeCollectionIdentifiers.push(decreeIdentifier);
        return true;
      });
      return [...decreesToAdd, ...decreeCollection];
    }
    return decreeCollection;
  }

  protected convertDateFromClient(decree: IDecree): IDecree {
    return Object.assign({}, decree, {
      decreeDate: decree.decreeDate?.isValid() ? decree.decreeDate.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.decreeDate = res.body.decreeDate ? dayjs(res.body.decreeDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((decree: IDecree) => {
        decree.decreeDate = decree.decreeDate ? dayjs(decree.decreeDate) : undefined;
      });
    }
    return res;
  }
}
