import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { TrademarkRegisteredStatus } from 'app/entities/enumerations/trademark-registered-status.model';
import { ITrademarkRegistered, TrademarkRegistered } from '../trademark-registered.model';

import { TrademarkRegisteredService } from './trademark-registered.service';

describe('TrademarkRegistered Service', () => {
  let service: TrademarkRegisteredService;
  let httpMock: HttpTestingController;
  let elemDefault: ITrademarkRegistered;
  let expectedResult: ITrademarkRegistered | ITrademarkRegistered[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TrademarkRegisteredService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      trademarkUUID: 'AAAAAAA',
      trademarkNo: 'AAAAAAA',
      year: 0,
      decreeNo: 'AAAAAAA',
      applicantName: 'AAAAAAA',
      tradeMarkOwner: 'AAAAAAA',
      country: 'AAAAAAA',
      nationality: 'AAAAAAA',
      address: 'AAAAAAA',
      applyDate: currentDate,
      trademarkEnglish: 'AAAAAAA',
      trademarkArabic: 'AAAAAAA',
      category: 'AAAAAAA',
      imageFileContentType: 'image/png',
      imageFile: 'AAAAAAA',
      imageFileUrl: 'AAAAAAA',
      fileContentType: 'image/png',
      file: 'AAAAAAA',
      fileUrl: 'AAAAAAA',
      extraFileContentType: 'image/png',
      extraFile: 'AAAAAAA',
      extraFileUrl: 'AAAAAAA',
      publicationDate: currentDate,
      publicationNo: 0,
      trademarkRegisteredStatus: TrademarkRegisteredStatus.INITIAL_PUBLICATION,
      isHidden: false,
      notes: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          applyDate: currentDate.format(DATE_FORMAT),
          publicationDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a TrademarkRegistered', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          applyDate: currentDate.format(DATE_FORMAT),
          publicationDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          applyDate: currentDate,
          publicationDate: currentDate,
        },
        returnedFromService
      );

      service.create(new TrademarkRegistered()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TrademarkRegistered', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          trademarkUUID: 'BBBBBB',
          trademarkNo: 'BBBBBB',
          year: 1,
          decreeNo: 'BBBBBB',
          applicantName: 'BBBBBB',
          tradeMarkOwner: 'BBBBBB',
          country: 'BBBBBB',
          nationality: 'BBBBBB',
          address: 'BBBBBB',
          applyDate: currentDate.format(DATE_FORMAT),
          trademarkEnglish: 'BBBBBB',
          trademarkArabic: 'BBBBBB',
          category: 'BBBBBB',
          imageFile: 'BBBBBB',
          imageFileUrl: 'BBBBBB',
          file: 'BBBBBB',
          fileUrl: 'BBBBBB',
          extraFile: 'BBBBBB',
          extraFileUrl: 'BBBBBB',
          publicationDate: currentDate.format(DATE_FORMAT),
          publicationNo: 1,
          trademarkRegisteredStatus: 'BBBBBB',
          isHidden: true,
          notes: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          applyDate: currentDate,
          publicationDate: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TrademarkRegistered', () => {
      const patchObject = Object.assign(
        {
          trademarkUUID: 'BBBBBB',
          year: 1,
          decreeNo: 'BBBBBB',
          applicantName: 'BBBBBB',
          nationality: 'BBBBBB',
          address: 'BBBBBB',
          trademarkEnglish: 'BBBBBB',
          trademarkArabic: 'BBBBBB',
          imageFileUrl: 'BBBBBB',
          file: 'BBBBBB',
          extraFile: 'BBBBBB',
          extraFileUrl: 'BBBBBB',
          isHidden: true,
        },
        new TrademarkRegistered()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          applyDate: currentDate,
          publicationDate: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TrademarkRegistered', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          trademarkUUID: 'BBBBBB',
          trademarkNo: 'BBBBBB',
          year: 1,
          decreeNo: 'BBBBBB',
          applicantName: 'BBBBBB',
          tradeMarkOwner: 'BBBBBB',
          country: 'BBBBBB',
          nationality: 'BBBBBB',
          address: 'BBBBBB',
          applyDate: currentDate.format(DATE_FORMAT),
          trademarkEnglish: 'BBBBBB',
          trademarkArabic: 'BBBBBB',
          category: 'BBBBBB',
          imageFile: 'BBBBBB',
          imageFileUrl: 'BBBBBB',
          file: 'BBBBBB',
          fileUrl: 'BBBBBB',
          extraFile: 'BBBBBB',
          extraFileUrl: 'BBBBBB',
          publicationDate: currentDate.format(DATE_FORMAT),
          publicationNo: 1,
          trademarkRegisteredStatus: 'BBBBBB',
          isHidden: true,
          notes: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          applyDate: currentDate,
          publicationDate: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a TrademarkRegistered', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addTrademarkRegisteredToCollectionIfMissing', () => {
      it('should add a TrademarkRegistered to an empty array', () => {
        const trademarkRegistered: ITrademarkRegistered = { id: 123 };
        expectedResult = service.addTrademarkRegisteredToCollectionIfMissing([], trademarkRegistered);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(trademarkRegistered);
      });

      it('should not add a TrademarkRegistered to an array that contains it', () => {
        const trademarkRegistered: ITrademarkRegistered = { id: 123 };
        const trademarkRegisteredCollection: ITrademarkRegistered[] = [
          {
            ...trademarkRegistered,
          },
          { id: 456 },
        ];
        expectedResult = service.addTrademarkRegisteredToCollectionIfMissing(trademarkRegisteredCollection, trademarkRegistered);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TrademarkRegistered to an array that doesn't contain it", () => {
        const trademarkRegistered: ITrademarkRegistered = { id: 123 };
        const trademarkRegisteredCollection: ITrademarkRegistered[] = [{ id: 456 }];
        expectedResult = service.addTrademarkRegisteredToCollectionIfMissing(trademarkRegisteredCollection, trademarkRegistered);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(trademarkRegistered);
      });

      it('should add only unique TrademarkRegistered to an array', () => {
        const trademarkRegisteredArray: ITrademarkRegistered[] = [{ id: 123 }, { id: 456 }, { id: 51614 }];
        const trademarkRegisteredCollection: ITrademarkRegistered[] = [{ id: 123 }];
        expectedResult = service.addTrademarkRegisteredToCollectionIfMissing(trademarkRegisteredCollection, ...trademarkRegisteredArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const trademarkRegistered: ITrademarkRegistered = { id: 123 };
        const trademarkRegistered2: ITrademarkRegistered = { id: 456 };
        expectedResult = service.addTrademarkRegisteredToCollectionIfMissing([], trademarkRegistered, trademarkRegistered2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(trademarkRegistered);
        expect(expectedResult).toContain(trademarkRegistered2);
      });

      it('should accept null and undefined values', () => {
        const trademarkRegistered: ITrademarkRegistered = { id: 123 };
        expectedResult = service.addTrademarkRegisteredToCollectionIfMissing([], null, trademarkRegistered, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(trademarkRegistered);
      });

      it('should return initial array if no TrademarkRegistered is added', () => {
        const trademarkRegisteredCollection: ITrademarkRegistered[] = [{ id: 123 }];
        expectedResult = service.addTrademarkRegisteredToCollectionIfMissing(trademarkRegisteredCollection, undefined, null);
        expect(expectedResult).toEqual(trademarkRegisteredCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
