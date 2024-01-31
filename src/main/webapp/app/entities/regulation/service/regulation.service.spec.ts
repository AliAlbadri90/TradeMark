import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IRegulation, Regulation } from '../regulation.model';

import { RegulationService } from './regulation.service';

describe('Regulation Service', () => {
  let service: RegulationService;
  let httpMock: HttpTestingController;
  let elemDefault: IRegulation;
  let expectedResult: IRegulation | IRegulation[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RegulationService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      title: 'AAAAAAA',
      description: 'AAAAAAA',
      type: 'AAAAAAA',
      year: 0,
      filePdfContentType: 'image/png',
      filePdf: 'AAAAAAA',
      filePdfUrl: 'AAAAAAA',
      fileWordContentType: 'image/png',
      fileWord: 'AAAAAAA',
      fileWordUrl: 'AAAAAAA',
      isActive: false,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Regulation', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Regulation()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Regulation', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          title: 'BBBBBB',
          description: 'BBBBBB',
          type: 'BBBBBB',
          year: 1,
          filePdf: 'BBBBBB',
          filePdfUrl: 'BBBBBB',
          fileWord: 'BBBBBB',
          fileWordUrl: 'BBBBBB',
          isActive: true,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Regulation', () => {
      const patchObject = Object.assign(
        {
          type: 'BBBBBB',
          year: 1,
          filePdf: 'BBBBBB',
          filePdfUrl: 'BBBBBB',
          fileWord: 'BBBBBB',
          fileWordUrl: 'BBBBBB',
          isActive: true,
        },
        new Regulation()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Regulation', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          title: 'BBBBBB',
          description: 'BBBBBB',
          type: 'BBBBBB',
          year: 1,
          filePdf: 'BBBBBB',
          filePdfUrl: 'BBBBBB',
          fileWord: 'BBBBBB',
          fileWordUrl: 'BBBBBB',
          isActive: true,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Regulation', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addRegulationToCollectionIfMissing', () => {
      it('should add a Regulation to an empty array', () => {
        const regulation: IRegulation = { id: 123 };
        expectedResult = service.addRegulationToCollectionIfMissing([], regulation);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(regulation);
      });

      it('should not add a Regulation to an array that contains it', () => {
        const regulation: IRegulation = { id: 123 };
        const regulationCollection: IRegulation[] = [
          {
            ...regulation,
          },
          { id: 456 },
        ];
        expectedResult = service.addRegulationToCollectionIfMissing(regulationCollection, regulation);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Regulation to an array that doesn't contain it", () => {
        const regulation: IRegulation = { id: 123 };
        const regulationCollection: IRegulation[] = [{ id: 456 }];
        expectedResult = service.addRegulationToCollectionIfMissing(regulationCollection, regulation);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(regulation);
      });

      it('should add only unique Regulation to an array', () => {
        const regulationArray: IRegulation[] = [{ id: 123 }, { id: 456 }, { id: 60901 }];
        const regulationCollection: IRegulation[] = [{ id: 123 }];
        expectedResult = service.addRegulationToCollectionIfMissing(regulationCollection, ...regulationArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const regulation: IRegulation = { id: 123 };
        const regulation2: IRegulation = { id: 456 };
        expectedResult = service.addRegulationToCollectionIfMissing([], regulation, regulation2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(regulation);
        expect(expectedResult).toContain(regulation2);
      });

      it('should accept null and undefined values', () => {
        const regulation: IRegulation = { id: 123 };
        expectedResult = service.addRegulationToCollectionIfMissing([], null, regulation, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(regulation);
      });

      it('should return initial array if no Regulation is added', () => {
        const regulationCollection: IRegulation[] = [{ id: 123 }];
        expectedResult = service.addRegulationToCollectionIfMissing(regulationCollection, undefined, null);
        expect(expectedResult).toEqual(regulationCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
