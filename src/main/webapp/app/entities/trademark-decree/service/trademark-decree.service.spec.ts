import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITrademarkDecree, TrademarkDecree } from '../trademark-decree.model';

import { TrademarkDecreeService } from './trademark-decree.service';

describe('TrademarkDecree Service', () => {
  let service: TrademarkDecreeService;
  let httpMock: HttpTestingController;
  let elemDefault: ITrademarkDecree;
  let expectedResult: ITrademarkDecree | ITrademarkDecree[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TrademarkDecreeService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      year: 0,
      decreeNo: 'AAAAAAA',
      isAccepted: false,
      decreeDate: currentDate,
      applicantName: 'AAAAAAA',
      tradeMarkOwner: 'AAAAAAA',
      country: 'AAAAAAA',
      applyDate: currentDate,
      serialNo: 'AAAAAAA',
      trademarkEnglish: 'AAAAAAA',
      trademarkArabic: 'AAAAAAA',
      category: 'AAAAAAA',
      pdfFileContentType: 'image/png',
      pdfFile: 'AAAAAAA',
      pdfFileUrl: 'AAAAAAA',
      extraPdfFileContentType: 'image/png',
      extraPdfFile: 'AAAAAAA',
      extraPdfFileUrl: 'AAAAAAA',
      isWithdrawal: false,
      withdrawalDecreeNo: 'AAAAAAA',
      notes: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          decreeDate: currentDate.format(DATE_FORMAT),
          applyDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a TrademarkDecree', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          decreeDate: currentDate.format(DATE_FORMAT),
          applyDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          decreeDate: currentDate,
          applyDate: currentDate,
        },
        returnedFromService
      );

      service.create(new TrademarkDecree()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TrademarkDecree', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          year: 1,
          decreeNo: 'BBBBBB',
          isAccepted: true,
          decreeDate: currentDate.format(DATE_FORMAT),
          applicantName: 'BBBBBB',
          tradeMarkOwner: 'BBBBBB',
          country: 'BBBBBB',
          applyDate: currentDate.format(DATE_FORMAT),
          serialNo: 'BBBBBB',
          trademarkEnglish: 'BBBBBB',
          trademarkArabic: 'BBBBBB',
          category: 'BBBBBB',
          pdfFile: 'BBBBBB',
          pdfFileUrl: 'BBBBBB',
          extraPdfFile: 'BBBBBB',
          extraPdfFileUrl: 'BBBBBB',
          isWithdrawal: true,
          withdrawalDecreeNo: 'BBBBBB',
          notes: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          decreeDate: currentDate,
          applyDate: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TrademarkDecree', () => {
      const patchObject = Object.assign(
        {
          year: 1,
          isAccepted: true,
          decreeDate: currentDate.format(DATE_FORMAT),
          tradeMarkOwner: 'BBBBBB',
          serialNo: 'BBBBBB',
          pdfFile: 'BBBBBB',
          extraPdfFile: 'BBBBBB',
          extraPdfFileUrl: 'BBBBBB',
          isWithdrawal: true,
          notes: 'BBBBBB',
        },
        new TrademarkDecree()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          decreeDate: currentDate,
          applyDate: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TrademarkDecree', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          year: 1,
          decreeNo: 'BBBBBB',
          isAccepted: true,
          decreeDate: currentDate.format(DATE_FORMAT),
          applicantName: 'BBBBBB',
          tradeMarkOwner: 'BBBBBB',
          country: 'BBBBBB',
          applyDate: currentDate.format(DATE_FORMAT),
          serialNo: 'BBBBBB',
          trademarkEnglish: 'BBBBBB',
          trademarkArabic: 'BBBBBB',
          category: 'BBBBBB',
          pdfFile: 'BBBBBB',
          pdfFileUrl: 'BBBBBB',
          extraPdfFile: 'BBBBBB',
          extraPdfFileUrl: 'BBBBBB',
          isWithdrawal: true,
          withdrawalDecreeNo: 'BBBBBB',
          notes: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          decreeDate: currentDate,
          applyDate: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a TrademarkDecree', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addTrademarkDecreeToCollectionIfMissing', () => {
      it('should add a TrademarkDecree to an empty array', () => {
        const trademarkDecree: ITrademarkDecree = { id: 123 };
        expectedResult = service.addTrademarkDecreeToCollectionIfMissing([], trademarkDecree);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(trademarkDecree);
      });

      it('should not add a TrademarkDecree to an array that contains it', () => {
        const trademarkDecree: ITrademarkDecree = { id: 123 };
        const trademarkDecreeCollection: ITrademarkDecree[] = [
          {
            ...trademarkDecree,
          },
          { id: 456 },
        ];
        expectedResult = service.addTrademarkDecreeToCollectionIfMissing(trademarkDecreeCollection, trademarkDecree);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TrademarkDecree to an array that doesn't contain it", () => {
        const trademarkDecree: ITrademarkDecree = { id: 123 };
        const trademarkDecreeCollection: ITrademarkDecree[] = [{ id: 456 }];
        expectedResult = service.addTrademarkDecreeToCollectionIfMissing(trademarkDecreeCollection, trademarkDecree);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(trademarkDecree);
      });

      it('should add only unique TrademarkDecree to an array', () => {
        const trademarkDecreeArray: ITrademarkDecree[] = [{ id: 123 }, { id: 456 }, { id: 74688 }];
        const trademarkDecreeCollection: ITrademarkDecree[] = [{ id: 123 }];
        expectedResult = service.addTrademarkDecreeToCollectionIfMissing(trademarkDecreeCollection, ...trademarkDecreeArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const trademarkDecree: ITrademarkDecree = { id: 123 };
        const trademarkDecree2: ITrademarkDecree = { id: 456 };
        expectedResult = service.addTrademarkDecreeToCollectionIfMissing([], trademarkDecree, trademarkDecree2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(trademarkDecree);
        expect(expectedResult).toContain(trademarkDecree2);
      });

      it('should accept null and undefined values', () => {
        const trademarkDecree: ITrademarkDecree = { id: 123 };
        expectedResult = service.addTrademarkDecreeToCollectionIfMissing([], null, trademarkDecree, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(trademarkDecree);
      });

      it('should return initial array if no TrademarkDecree is added', () => {
        const trademarkDecreeCollection: ITrademarkDecree[] = [{ id: 123 }];
        expectedResult = service.addTrademarkDecreeToCollectionIfMissing(trademarkDecreeCollection, undefined, null);
        expect(expectedResult).toEqual(trademarkDecreeCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
