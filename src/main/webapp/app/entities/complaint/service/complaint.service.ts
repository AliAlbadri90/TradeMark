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
import { IComplaint, getComplaintIdentifier } from '../complaint.model';

export type EntityResponseType = HttpResponse<IComplaint>;
export type EntityArrayResponseType = HttpResponse<IComplaint[]>;

@Injectable({ providedIn: 'root' })
export class ComplaintService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/complaints');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/_search/complaints');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(complaint: IComplaint): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(complaint);
    return this.http
      .post<IComplaint>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(complaint: IComplaint): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(complaint);
    return this.http
      .put<IComplaint>(`${this.resourceUrl}/${getComplaintIdentifier(complaint) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(complaint: IComplaint): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(complaint);
    return this.http
      .patch<IComplaint>(`${this.resourceUrl}/${getComplaintIdentifier(complaint) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IComplaint>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IComplaint[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IComplaint[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  addComplaintToCollectionIfMissing(
    complaintCollection: IComplaint[],
    ...complaintsToCheck: (IComplaint | null | undefined)[]
  ): IComplaint[] {
    const complaints: IComplaint[] = complaintsToCheck.filter(isPresent);
    if (complaints.length > 0) {
      const complaintCollectionIdentifiers = complaintCollection.map(complaintItem => getComplaintIdentifier(complaintItem)!);
      const complaintsToAdd = complaints.filter(complaintItem => {
        const complaintIdentifier = getComplaintIdentifier(complaintItem);
        if (complaintIdentifier == null || complaintCollectionIdentifiers.includes(complaintIdentifier)) {
          return false;
        }
        complaintCollectionIdentifiers.push(complaintIdentifier);
        return true;
      });
      return [...complaintsToAdd, ...complaintCollection];
    }
    return complaintCollection;
  }

  protected convertDateFromClient(complaint: IComplaint): IComplaint {
    return Object.assign({}, complaint, {
      complaintDate: complaint.complaintDate?.isValid() ? complaint.complaintDate.format(DATE_FORMAT) : undefined,
      complaintOfficeReceivedDate: complaint.complaintOfficeReceivedDate?.isValid()
        ? complaint.complaintOfficeReceivedDate.format(DATE_FORMAT)
        : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.complaintDate = res.body.complaintDate ? dayjs(res.body.complaintDate) : undefined;
      res.body.complaintOfficeReceivedDate = res.body.complaintOfficeReceivedDate ? dayjs(res.body.complaintOfficeReceivedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((complaint: IComplaint) => {
        complaint.complaintDate = complaint.complaintDate ? dayjs(complaint.complaintDate) : undefined;
        complaint.complaintOfficeReceivedDate = complaint.complaintOfficeReceivedDate
          ? dayjs(complaint.complaintOfficeReceivedDate)
          : undefined;
      });
    }
    return res;
  }
}
