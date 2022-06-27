import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { DecreeStatus } from 'app/entities/enumerations/decree-status.model';
import { IDecree, Decree } from '../decree.model';

import { DecreeService } from './decree.service';

describe('Decree Service', () => {
  let service: DecreeService;
  let httpMock: HttpTestingController;
  let elemDefault: IDecree;
  let expectedResult: IDecree | IDecree[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DecreeService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      documentNo: 'AAAAAAA',
      decreeNo: 'AAAAAAA',
      title: 'AAAAAAA',
      details: 'AAAAAAA',
      keywords: 'AAAAAAA',
      pages: 0,
      decreeDate: currentDate,
      year: 0,
      notes: 'AAAAAAA',
      pdfFileContentType: 'image/png',
      pdfFile: 'AAAAAAA',
      pdfFileUrl: 'AAAAAAA',
      wordFileContentType: 'image/png',
      wordFile: 'AAAAAAA',
      wordFileUrl: 'AAAAAAA',
      extraPdfFileContentType: 'image/png',
      extraPdfFile: 'AAAAAAA',
      extraPdfFileUrl: 'AAAAAAA',
      decreeStatus: DecreeStatus.ACTIVE,
      remarks: 'AAAAAAA',
      isHidden: false,
      hideNotes: 'AAAAAAA',
      hideEndDate: currentDate,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          decreeDate: currentDate.format(DATE_FORMAT),
          hideEndDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Decree', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          decreeDate: currentDate.format(DATE_FORMAT),
          hideEndDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          decreeDate: currentDate,
          hideEndDate: currentDate,
        },
        returnedFromService
      );

      service.create(new Decree()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Decree', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          documentNo: 'BBBBBB',
          decreeNo: 'BBBBBB',
          title: 'BBBBBB',
          details: 'BBBBBB',
          keywords: 'BBBBBB',
          pages: 1,
          decreeDate: currentDate.format(DATE_FORMAT),
          year: 1,
          notes: 'BBBBBB',
          pdfFile: 'BBBBBB',
          pdfFileUrl: 'BBBBBB',
          wordFile: 'BBBBBB',
          wordFileUrl: 'BBBBBB',
          extraPdfFile: 'BBBBBB',
          extraPdfFileUrl: 'BBBBBB',
          decreeStatus: 'BBBBBB',
          remarks: 'BBBBBB',
          isHidden: true,
          hideNotes: 'BBBBBB',
          hideEndDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          decreeDate: currentDate,
          hideEndDate: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Decree', () => {
      const patchObject = Object.assign(
        {
          title: 'BBBBBB',
          details: 'BBBBBB',
          pages: 1,
          year: 1,
          notes: 'BBBBBB',
          pdfFile: 'BBBBBB',
          pdfFileUrl: 'BBBBBB',
          wordFile: 'BBBBBB',
          extraPdfFile: 'BBBBBB',
          decreeStatus: 'BBBBBB',
          isHidden: true,
          hideEndDate: currentDate.format(DATE_FORMAT),
        },
        new Decree()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          decreeDate: currentDate,
          hideEndDate: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Decree', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          documentNo: 'BBBBBB',
          decreeNo: 'BBBBBB',
          title: 'BBBBBB',
          details: 'BBBBBB',
          keywords: 'BBBBBB',
          pages: 1,
          decreeDate: currentDate.format(DATE_FORMAT),
          year: 1,
          notes: 'BBBBBB',
          pdfFile: 'BBBBBB',
          pdfFileUrl: 'BBBBBB',
          wordFile: 'BBBBBB',
          wordFileUrl: 'BBBBBB',
          extraPdfFile: 'BBBBBB',
          extraPdfFileUrl: 'BBBBBB',
          decreeStatus: 'BBBBBB',
          remarks: 'BBBBBB',
          isHidden: true,
          hideNotes: 'BBBBBB',
          hideEndDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          decreeDate: currentDate,
          hideEndDate: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Decree', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDecreeToCollectionIfMissing', () => {
      it('should add a Decree to an empty array', () => {
        const decree: IDecree = { id: 123 };
        expectedResult = service.addDecreeToCollectionIfMissing([], decree);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(decree);
      });

      it('should not add a Decree to an array that contains it', () => {
        const decree: IDecree = { id: 123 };
        const decreeCollection: IDecree[] = [
          {
            ...decree,
          },
          { id: 456 },
        ];
        expectedResult = service.addDecreeToCollectionIfMissing(decreeCollection, decree);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Decree to an array that doesn't contain it", () => {
        const decree: IDecree = { id: 123 };
        const decreeCollection: IDecree[] = [{ id: 456 }];
        expectedResult = service.addDecreeToCollectionIfMissing(decreeCollection, decree);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(decree);
      });

      it('should add only unique Decree to an array', () => {
        const decreeArray: IDecree[] = [{ id: 123 }, { id: 456 }, { id: 64600 }];
        const decreeCollection: IDecree[] = [{ id: 123 }];
        expectedResult = service.addDecreeToCollectionIfMissing(decreeCollection, ...decreeArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const decree: IDecree = { id: 123 };
        const decree2: IDecree = { id: 456 };
        expectedResult = service.addDecreeToCollectionIfMissing([], decree, decree2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(decree);
        expect(expectedResult).toContain(decree2);
      });

      it('should accept null and undefined values', () => {
        const decree: IDecree = { id: 123 };
        expectedResult = service.addDecreeToCollectionIfMissing([], null, decree, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(decree);
      });

      it('should return initial array if no Decree is added', () => {
        const decreeCollection: IDecree[] = [{ id: 123 }];
        expectedResult = service.addDecreeToCollectionIfMissing(decreeCollection, undefined, null);
        expect(expectedResult).toEqual(decreeCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
