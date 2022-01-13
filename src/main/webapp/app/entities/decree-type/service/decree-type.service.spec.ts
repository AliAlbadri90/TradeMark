import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDecreeType, DecreeType } from '../decree-type.model';

import { DecreeTypeService } from './decree-type.service';

describe('DecreeType Service', () => {
  let service: DecreeTypeService;
  let httpMock: HttpTestingController;
  let elemDefault: IDecreeType;
  let expectedResult: IDecreeType | IDecreeType[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DecreeTypeService);
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

    it('should create a DecreeType', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new DecreeType()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DecreeType', () => {
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

    it('should partial update a DecreeType', () => {
      const patchObject = Object.assign(
        {
          name: 'BBBBBB',
        },
        new DecreeType()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DecreeType', () => {
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

    it('should delete a DecreeType', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDecreeTypeToCollectionIfMissing', () => {
      it('should add a DecreeType to an empty array', () => {
        const decreeType: IDecreeType = { id: 123 };
        expectedResult = service.addDecreeTypeToCollectionIfMissing([], decreeType);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(decreeType);
      });

      it('should not add a DecreeType to an array that contains it', () => {
        const decreeType: IDecreeType = { id: 123 };
        const decreeTypeCollection: IDecreeType[] = [
          {
            ...decreeType,
          },
          { id: 456 },
        ];
        expectedResult = service.addDecreeTypeToCollectionIfMissing(decreeTypeCollection, decreeType);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DecreeType to an array that doesn't contain it", () => {
        const decreeType: IDecreeType = { id: 123 };
        const decreeTypeCollection: IDecreeType[] = [{ id: 456 }];
        expectedResult = service.addDecreeTypeToCollectionIfMissing(decreeTypeCollection, decreeType);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(decreeType);
      });

      it('should add only unique DecreeType to an array', () => {
        const decreeTypeArray: IDecreeType[] = [{ id: 123 }, { id: 456 }, { id: 50312 }];
        const decreeTypeCollection: IDecreeType[] = [{ id: 123 }];
        expectedResult = service.addDecreeTypeToCollectionIfMissing(decreeTypeCollection, ...decreeTypeArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const decreeType: IDecreeType = { id: 123 };
        const decreeType2: IDecreeType = { id: 456 };
        expectedResult = service.addDecreeTypeToCollectionIfMissing([], decreeType, decreeType2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(decreeType);
        expect(expectedResult).toContain(decreeType2);
      });

      it('should accept null and undefined values', () => {
        const decreeType: IDecreeType = { id: 123 };
        expectedResult = service.addDecreeTypeToCollectionIfMissing([], null, decreeType, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(decreeType);
      });

      it('should return initial array if no DecreeType is added', () => {
        const decreeTypeCollection: IDecreeType[] = [{ id: 123 }];
        expectedResult = service.addDecreeTypeToCollectionIfMissing(decreeTypeCollection, undefined, null);
        expect(expectedResult).toEqual(decreeTypeCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
