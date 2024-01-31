import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ComplaintStatus } from 'app/entities/enumerations/complaint-status.model';
import { IComplaint, Complaint } from '../complaint.model';

import { ComplaintService } from './complaint.service';

describe('Complaint Service', () => {
  let service: ComplaintService;
  let httpMock: HttpTestingController;
  let elemDefault: IComplaint;
  let expectedResult: IComplaint | IComplaint[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ComplaintService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      complaintUUID: 'AAAAAAA',
      complaintNo: 'AAAAAAA',
      trademarkNo: 'AAAAAAA',
      complaintDate: currentDate,
      complaintOfficeReceivedDate: currentDate,
      complaintPaymentReceipt: 'AAAAAAA',
      complaintYear: 0,
      complainerPersonFullName: 'AAAAAAA',
      complainerPersonJob: 'AAAAAAA',
      complainerPersonNationality: 'AAAAAAA',
      complainerPersonAddress: 'AAAAAAA',
      complainerCompanyName: 'AAAAAAA',
      complainerCompanyAddress: 'AAAAAAA',
      complainerCompanyPurpose: 'AAAAAAA',
      complainerCompanyHeadQuarterAddress: 'AAAAAAA',
      complainerCompanyLibyaAddress: 'AAAAAAA',
      pdfFileContentType: 'image/png',
      pdfFile: 'AAAAAAA',
      pdfFileUrl: 'AAAAAAA',
      complaintStatus: ComplaintStatus.PENDING,
      complaintDetails: 'AAAAAAA',
      notes: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          complaintDate: currentDate.format(DATE_FORMAT),
          complaintOfficeReceivedDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Complaint', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          complaintDate: currentDate.format(DATE_FORMAT),
          complaintOfficeReceivedDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          complaintDate: currentDate,
          complaintOfficeReceivedDate: currentDate,
        },
        returnedFromService
      );

      service.create(new Complaint()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Complaint', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          complaintUUID: 'BBBBBB',
          complaintNo: 'BBBBBB',
          trademarkNo: 'BBBBBB',
          complaintDate: currentDate.format(DATE_FORMAT),
          complaintOfficeReceivedDate: currentDate.format(DATE_FORMAT),
          complaintPaymentReceipt: 'BBBBBB',
          complaintYear: 1,
          complainerPersonFullName: 'BBBBBB',
          complainerPersonJob: 'BBBBBB',
          complainerPersonNationality: 'BBBBBB',
          complainerPersonAddress: 'BBBBBB',
          complainerCompanyName: 'BBBBBB',
          complainerCompanyAddress: 'BBBBBB',
          complainerCompanyPurpose: 'BBBBBB',
          complainerCompanyHeadQuarterAddress: 'BBBBBB',
          complainerCompanyLibyaAddress: 'BBBBBB',
          pdfFile: 'BBBBBB',
          pdfFileUrl: 'BBBBBB',
          complaintStatus: 'BBBBBB',
          complaintDetails: 'BBBBBB',
          notes: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          complaintDate: currentDate,
          complaintOfficeReceivedDate: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Complaint', () => {
      const patchObject = Object.assign(
        {
          trademarkNo: 'BBBBBB',
          complaintDate: currentDate.format(DATE_FORMAT),
          complaintPaymentReceipt: 'BBBBBB',
          complaintYear: 1,
          complainerPersonFullName: 'BBBBBB',
          complainerPersonAddress: 'BBBBBB',
          complainerCompanyName: 'BBBBBB',
          complaintStatus: 'BBBBBB',
          notes: 'BBBBBB',
        },
        new Complaint()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          complaintDate: currentDate,
          complaintOfficeReceivedDate: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Complaint', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          complaintUUID: 'BBBBBB',
          complaintNo: 'BBBBBB',
          trademarkNo: 'BBBBBB',
          complaintDate: currentDate.format(DATE_FORMAT),
          complaintOfficeReceivedDate: currentDate.format(DATE_FORMAT),
          complaintPaymentReceipt: 'BBBBBB',
          complaintYear: 1,
          complainerPersonFullName: 'BBBBBB',
          complainerPersonJob: 'BBBBBB',
          complainerPersonNationality: 'BBBBBB',
          complainerPersonAddress: 'BBBBBB',
          complainerCompanyName: 'BBBBBB',
          complainerCompanyAddress: 'BBBBBB',
          complainerCompanyPurpose: 'BBBBBB',
          complainerCompanyHeadQuarterAddress: 'BBBBBB',
          complainerCompanyLibyaAddress: 'BBBBBB',
          pdfFile: 'BBBBBB',
          pdfFileUrl: 'BBBBBB',
          complaintStatus: 'BBBBBB',
          complaintDetails: 'BBBBBB',
          notes: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          complaintDate: currentDate,
          complaintOfficeReceivedDate: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Complaint', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addComplaintToCollectionIfMissing', () => {
      it('should add a Complaint to an empty array', () => {
        const complaint: IComplaint = { id: 123 };
        expectedResult = service.addComplaintToCollectionIfMissing([], complaint);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(complaint);
      });

      it('should not add a Complaint to an array that contains it', () => {
        const complaint: IComplaint = { id: 123 };
        const complaintCollection: IComplaint[] = [
          {
            ...complaint,
          },
          { id: 456 },
        ];
        expectedResult = service.addComplaintToCollectionIfMissing(complaintCollection, complaint);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Complaint to an array that doesn't contain it", () => {
        const complaint: IComplaint = { id: 123 };
        const complaintCollection: IComplaint[] = [{ id: 456 }];
        expectedResult = service.addComplaintToCollectionIfMissing(complaintCollection, complaint);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(complaint);
      });

      it('should add only unique Complaint to an array', () => {
        const complaintArray: IComplaint[] = [{ id: 123 }, { id: 456 }, { id: 21129 }];
        const complaintCollection: IComplaint[] = [{ id: 123 }];
        expectedResult = service.addComplaintToCollectionIfMissing(complaintCollection, ...complaintArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const complaint: IComplaint = { id: 123 };
        const complaint2: IComplaint = { id: 456 };
        expectedResult = service.addComplaintToCollectionIfMissing([], complaint, complaint2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(complaint);
        expect(expectedResult).toContain(complaint2);
      });

      it('should accept null and undefined values', () => {
        const complaint: IComplaint = { id: 123 };
        expectedResult = service.addComplaintToCollectionIfMissing([], null, complaint, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(complaint);
      });

      it('should return initial array if no Complaint is added', () => {
        const complaintCollection: IComplaint[] = [{ id: 123 }];
        expectedResult = service.addComplaintToCollectionIfMissing(complaintCollection, undefined, null);
        expect(expectedResult).toEqual(complaintCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
