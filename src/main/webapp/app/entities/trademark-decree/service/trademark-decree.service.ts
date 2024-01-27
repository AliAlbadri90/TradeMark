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
import { ITrademarkDecree, getTrademarkDecreeIdentifier } from '../trademark-decree.model';
import { IDecreeReport } from '../../decree/decree-report.model';

export type EntityResponseType = HttpResponse<ITrademarkDecree>;
export type EntityArrayResponseType = HttpResponse<ITrademarkDecree[]>;

@Injectable({ providedIn: 'root' })
export class TrademarkDecreeService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/trademark-decrees');
  protected resourceUrlPublic = this.applicationConfigService.getEndpointFor('api/public/trademark-decrees');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/_search/trademark-decrees');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(trademarkDecree: ITrademarkDecree): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trademarkDecree);
    return this.http
      .post<ITrademarkDecree>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(trademarkDecree: ITrademarkDecree): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trademarkDecree);
    return this.http
      .put<ITrademarkDecree>(`${this.resourceUrl}/${getTrademarkDecreeIdentifier(trademarkDecree) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(trademarkDecree: ITrademarkDecree): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trademarkDecree);
    return this.http
      .patch<ITrademarkDecree>(`${this.resourceUrl}/${getTrademarkDecreeIdentifier(trademarkDecree) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITrademarkDecree>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITrademarkDecree[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITrademarkDecree[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  getTrademarkDecreeLineChart(): Observable<HttpResponse<any>> {
    return this.http.get<any>(this.resourceUrl + '/line-chart', { observe: 'response' });
  }

  getTrademarkDecreeLineChartPublic(): Observable<HttpResponse<any>> {
    return this.http.get<any>(this.resourceUrlPublic + '/line-chart', { observe: 'response' });
  }

  getCreatedByCount(): Observable<HttpResponse<any>> {
    return this.http.get<any>(this.resourceUrl + '/created-by', { observe: 'response' });
  }

  getYears(): Observable<HttpResponse<any[]>> {
    return this.http.get<any[]>(`${this.resourceUrl}/years/`, { observe: 'response' });
  }

  getReport(year: string): Observable<HttpResponse<IDecreeReport>> {
    return this.http
      .get<IDecreeReport>(`${this.resourceUrl}/report/${year}`, { observe: 'response' })
      .pipe(map((res: HttpResponse<IDecreeReport>) => res));
  }

  addTrademarkDecreeToCollectionIfMissing(
    trademarkDecreeCollection: ITrademarkDecree[],
    ...trademarkDecreesToCheck: (ITrademarkDecree | null | undefined)[]
  ): ITrademarkDecree[] {
    const trademarkDecrees: ITrademarkDecree[] = trademarkDecreesToCheck.filter(isPresent);
    if (trademarkDecrees.length > 0) {
      const trademarkDecreeCollectionIdentifiers = trademarkDecreeCollection.map(
        trademarkDecreeItem => getTrademarkDecreeIdentifier(trademarkDecreeItem)!
      );
      const trademarkDecreesToAdd = trademarkDecrees.filter(trademarkDecreeItem => {
        const trademarkDecreeIdentifier = getTrademarkDecreeIdentifier(trademarkDecreeItem);
        if (trademarkDecreeIdentifier == null || trademarkDecreeCollectionIdentifiers.includes(trademarkDecreeIdentifier)) {
          return false;
        }
        trademarkDecreeCollectionIdentifiers.push(trademarkDecreeIdentifier);
        return true;
      });
      return [...trademarkDecreesToAdd, ...trademarkDecreeCollection];
    }
    return trademarkDecreeCollection;
  }

  protected convertDateFromClient(trademarkDecree: ITrademarkDecree): ITrademarkDecree {
    return Object.assign({}, trademarkDecree, {
      decreeDate: trademarkDecree.decreeDate?.isValid() ? trademarkDecree.decreeDate.format(DATE_FORMAT) : undefined,
      applyDate: trademarkDecree.applyDate?.isValid() ? trademarkDecree.applyDate.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.decreeDate = res.body.decreeDate ? dayjs(res.body.decreeDate) : undefined;
      res.body.applyDate = res.body.applyDate ? dayjs(res.body.applyDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((trademarkDecree: ITrademarkDecree) => {
        trademarkDecree.decreeDate = trademarkDecree.decreeDate ? dayjs(trademarkDecree.decreeDate) : undefined;
        trademarkDecree.applyDate = trademarkDecree.applyDate ? dayjs(trademarkDecree.applyDate) : undefined;
      });
    }
    return res;
  }
}
