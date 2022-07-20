import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IViewLog, getViewLogIdentifier } from '../view-log.model';

export type EntityResponseType = HttpResponse<IViewLog>;
export type EntityArrayResponseType = HttpResponse<IViewLog[]>;

@Injectable({ providedIn: 'root' })
export class ViewLogService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/view-logs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(viewLog: IViewLog): Observable<EntityResponseType> {
    return this.http.post<IViewLog>(this.resourceUrl, viewLog, { observe: 'response' });
  }

  update(viewLog: IViewLog): Observable<EntityResponseType> {
    return this.http.put<IViewLog>(`${this.resourceUrl}/${getViewLogIdentifier(viewLog) as number}`, viewLog, { observe: 'response' });
  }

  partialUpdate(viewLog: IViewLog): Observable<EntityResponseType> {
    return this.http.patch<IViewLog>(`${this.resourceUrl}/${getViewLogIdentifier(viewLog) as number}`, viewLog, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IViewLog>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IViewLog[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addViewLogToCollectionIfMissing(viewLogCollection: IViewLog[], ...viewLogsToCheck: (IViewLog | null | undefined)[]): IViewLog[] {
    const viewLogs: IViewLog[] = viewLogsToCheck.filter(isPresent);
    if (viewLogs.length > 0) {
      const viewLogCollectionIdentifiers = viewLogCollection.map(viewLogItem => getViewLogIdentifier(viewLogItem)!);
      const viewLogsToAdd = viewLogs.filter(viewLogItem => {
        const viewLogIdentifier = getViewLogIdentifier(viewLogItem);
        if (viewLogIdentifier == null || viewLogCollectionIdentifiers.includes(viewLogIdentifier)) {
          return false;
        }
        viewLogCollectionIdentifiers.push(viewLogIdentifier);
        return true;
      });
      return [...viewLogsToAdd, ...viewLogCollection];
    }
    return viewLogCollection;
  }
}
