import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IGovernment, Government } from '../government.model';

import { GovernmentService } from './government.service';

describe('Government Service', () => {
  let service: GovernmentService;
  let httpMock: HttpTestingController;
  let elemDefault: IGovernment;
  let expectedResult: IGovernment | IGovernment[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(GovernmentService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      name: 'AAAAAAA',
      serialNo: 'AAAAAAA',
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

    it('should create a Government', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Government()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Government', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          serialNo: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Government', () => {
      const patchObject = Object.assign(
        {
          name: 'BBBBBB',
        },
        new Government()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Government', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          serialNo: 'BBBBBB',
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

    it('should delete a Government', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addGovernmentToCollectionIfMissing', () => {
      it('should add a Government to an empty array', () => {
        const government: IGovernment = { id: 123 };
        expectedResult = service.addGovernmentToCollectionIfMissing([], government);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(government);
      });

      it('should not add a Government to an array that contains it', () => {
        const government: IGovernment = { id: 123 };
        const governmentCollection: IGovernment[] = [
          {
            ...government,
          },
          { id: 456 },
        ];
        expectedResult = service.addGovernmentToCollectionIfMissing(governmentCollection, government);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Government to an array that doesn't contain it", () => {
        const government: IGovernment = { id: 123 };
        const governmentCollection: IGovernment[] = [{ id: 456 }];
        expectedResult = service.addGovernmentToCollectionIfMissing(governmentCollection, government);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(government);
      });

      it('should add only unique Government to an array', () => {
        const governmentArray: IGovernment[] = [{ id: 123 }, { id: 456 }, { id: 3339 }];
        const governmentCollection: IGovernment[] = [{ id: 123 }];
        expectedResult = service.addGovernmentToCollectionIfMissing(governmentCollection, ...governmentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const government: IGovernment = { id: 123 };
        const government2: IGovernment = { id: 456 };
        expectedResult = service.addGovernmentToCollectionIfMissing([], government, government2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(government);
        expect(expectedResult).toContain(government2);
      });

      it('should accept null and undefined values', () => {
        const government: IGovernment = { id: 123 };
        expectedResult = service.addGovernmentToCollectionIfMissing([], null, government, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(government);
      });

      it('should return initial array if no Government is added', () => {
        const governmentCollection: IGovernment[] = [{ id: 123 }];
        expectedResult = service.addGovernmentToCollectionIfMissing(governmentCollection, undefined, null);
        expect(expectedResult).toEqual(governmentCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
