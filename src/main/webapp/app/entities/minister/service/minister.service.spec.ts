import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IMinister, Minister } from '../minister.model';

import { MinisterService } from './minister.service';

describe('Minister Service', () => {
  let service: MinisterService;
  let httpMock: HttpTestingController;
  let elemDefault: IMinister;
  let expectedResult: IMinister | IMinister[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(MinisterService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      name: 'AAAAAAA',
      serialNo: 'AAAAAAA',
      jobTitle: 'AAAAAAA',
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

    it('should create a Minister', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Minister()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Minister', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          serialNo: 'BBBBBB',
          jobTitle: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Minister', () => {
      const patchObject = Object.assign(
        {
          serialNo: 'BBBBBB',
          jobTitle: 'BBBBBB',
        },
        new Minister()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Minister', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          serialNo: 'BBBBBB',
          jobTitle: 'BBBBBB',
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

    it('should delete a Minister', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addMinisterToCollectionIfMissing', () => {
      it('should add a Minister to an empty array', () => {
        const minister: IMinister = { id: 123 };
        expectedResult = service.addMinisterToCollectionIfMissing([], minister);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(minister);
      });

      it('should not add a Minister to an array that contains it', () => {
        const minister: IMinister = { id: 123 };
        const ministerCollection: IMinister[] = [
          {
            ...minister,
          },
          { id: 456 },
        ];
        expectedResult = service.addMinisterToCollectionIfMissing(ministerCollection, minister);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Minister to an array that doesn't contain it", () => {
        const minister: IMinister = { id: 123 };
        const ministerCollection: IMinister[] = [{ id: 456 }];
        expectedResult = service.addMinisterToCollectionIfMissing(ministerCollection, minister);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(minister);
      });

      it('should add only unique Minister to an array', () => {
        const ministerArray: IMinister[] = [{ id: 123 }, { id: 456 }, { id: 89941 }];
        const ministerCollection: IMinister[] = [{ id: 123 }];
        expectedResult = service.addMinisterToCollectionIfMissing(ministerCollection, ...ministerArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const minister: IMinister = { id: 123 };
        const minister2: IMinister = { id: 456 };
        expectedResult = service.addMinisterToCollectionIfMissing([], minister, minister2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(minister);
        expect(expectedResult).toContain(minister2);
      });

      it('should accept null and undefined values', () => {
        const minister: IMinister = { id: 123 };
        expectedResult = service.addMinisterToCollectionIfMissing([], null, minister, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(minister);
      });

      it('should return initial array if no Minister is added', () => {
        const ministerCollection: IMinister[] = [{ id: 123 }];
        expectedResult = service.addMinisterToCollectionIfMissing(ministerCollection, undefined, null);
        expect(expectedResult).toEqual(ministerCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
