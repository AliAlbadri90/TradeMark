import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { SearchWithPagination } from 'app/core/request/request.model';
import { ITrademarkRegistered, getTrademarkRegisteredIdentifier } from '../trademark-registered.model';

export type EntityResponseType = HttpResponse<ITrademarkRegistered>;
export type EntityArrayResponseType = HttpResponse<ITrademarkRegistered[]>;

@Injectable({ providedIn: 'root' })
export class TrademarkRegisteredService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/trademark-registereds');
  protected resourcePublicUrl = this.applicationConfigService.getEndpointFor('api/public/trademark-registereds');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/_search/trademark-registereds');
  protected resourcePublicSearchUrl = this.applicationConfigService.getEndpointFor('api/public/_search/trademark-registereds');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(trademarkRegistered: ITrademarkRegistered): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trademarkRegistered);
    return this.http
      .post<ITrademarkRegistered>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(trademarkRegistered: ITrademarkRegistered): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trademarkRegistered);
    return this.http
      .put<ITrademarkRegistered>(`${this.resourceUrl}/${getTrademarkRegisteredIdentifier(trademarkRegistered) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(trademarkRegistered: ITrademarkRegistered): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trademarkRegistered);
    return this.http
      .patch<ITrademarkRegistered>(`${this.resourceUrl}/${getTrademarkRegisteredIdentifier(trademarkRegistered) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITrademarkRegistered>(`${this.resourcePublicUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITrademarkRegistered[]>(this.resourcePublicUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITrademarkRegistered[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  searchPublic(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITrademarkRegistered[]>(this.resourcePublicSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  addTrademarkRegisteredToCollectionIfMissing(
    trademarkRegisteredCollection: ITrademarkRegistered[],
    ...trademarkRegisteredsToCheck: (ITrademarkRegistered | null | undefined)[]
  ): ITrademarkRegistered[] {
    const trademarkRegistereds: ITrademarkRegistered[] = trademarkRegisteredsToCheck.filter(isPresent);
    if (trademarkRegistereds.length > 0) {
      const trademarkRegisteredCollectionIdentifiers = trademarkRegisteredCollection.map(
        trademarkRegisteredItem => getTrademarkRegisteredIdentifier(trademarkRegisteredItem)!
      );
      const trademarkRegisteredsToAdd = trademarkRegistereds.filter(trademarkRegisteredItem => {
        const trademarkRegisteredIdentifier = getTrademarkRegisteredIdentifier(trademarkRegisteredItem);
        if (trademarkRegisteredIdentifier == null || trademarkRegisteredCollectionIdentifiers.includes(trademarkRegisteredIdentifier)) {
          return false;
        }
        trademarkRegisteredCollectionIdentifiers.push(trademarkRegisteredIdentifier);
        return true;
      });
      return [...trademarkRegisteredsToAdd, ...trademarkRegisteredCollection];
    }
    return trademarkRegisteredCollection;
  }

  count(req?: any): Observable<HttpResponse<any>> {
    const options = createRequestOption(req);
    return this.http.get<number>(this.resourceUrl + '/count', { params: options, observe: 'response' });
  }

  protected convertDateFromClient(trademarkRegistered: ITrademarkRegistered): ITrademarkRegistered {
    return Object.assign({}, trademarkRegistered, {
      applyDate: trademarkRegistered.applyDate?.isValid() ? trademarkRegistered.applyDate.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.applyDate = res.body.applyDate ? dayjs(res.body.applyDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((trademarkRegistered: ITrademarkRegistered) => {
        trademarkRegistered.applyDate = trademarkRegistered.applyDate ? dayjs(trademarkRegistered.applyDate) : undefined;
      });
    }
    return res;
  }
}
