import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDecreeCategory, DecreeCategory } from '../decree-category.model';

import { DecreeCategoryService } from './decree-category.service';

describe('DecreeCategory Service', () => {
  let service: DecreeCategoryService;
  let httpMock: HttpTestingController;
  let elemDefault: IDecreeCategory;
  let expectedResult: IDecreeCategory | IDecreeCategory[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DecreeCategoryService);
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

    it('should create a DecreeCategory', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new DecreeCategory()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DecreeCategory', () => {
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

    it('should partial update a DecreeCategory', () => {
      const patchObject = Object.assign(
        {
          serialNo: 'BBBBBB',
        },
        new DecreeCategory()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DecreeCategory', () => {
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

    it('should delete a DecreeCategory', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDecreeCategoryToCollectionIfMissing', () => {
      it('should add a DecreeCategory to an empty array', () => {
        const decreeCategory: IDecreeCategory = { id: 123 };
        expectedResult = service.addDecreeCategoryToCollectionIfMissing([], decreeCategory);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(decreeCategory);
      });

      it('should not add a DecreeCategory to an array that contains it', () => {
        const decreeCategory: IDecreeCategory = { id: 123 };
        const decreeCategoryCollection: IDecreeCategory[] = [
          {
            ...decreeCategory,
          },
          { id: 456 },
        ];
        expectedResult = service.addDecreeCategoryToCollectionIfMissing(decreeCategoryCollection, decreeCategory);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DecreeCategory to an array that doesn't contain it", () => {
        const decreeCategory: IDecreeCategory = { id: 123 };
        const decreeCategoryCollection: IDecreeCategory[] = [{ id: 456 }];
        expectedResult = service.addDecreeCategoryToCollectionIfMissing(decreeCategoryCollection, decreeCategory);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(decreeCategory);
      });

      it('should add only unique DecreeCategory to an array', () => {
        const decreeCategoryArray: IDecreeCategory[] = [{ id: 123 }, { id: 456 }, { id: 14191 }];
        const decreeCategoryCollection: IDecreeCategory[] = [{ id: 123 }];
        expectedResult = service.addDecreeCategoryToCollectionIfMissing(decreeCategoryCollection, ...decreeCategoryArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const decreeCategory: IDecreeCategory = { id: 123 };
        const decreeCategory2: IDecreeCategory = { id: 456 };
        expectedResult = service.addDecreeCategoryToCollectionIfMissing([], decreeCategory, decreeCategory2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(decreeCategory);
        expect(expectedResult).toContain(decreeCategory2);
      });

      it('should accept null and undefined values', () => {
        const decreeCategory: IDecreeCategory = { id: 123 };
        expectedResult = service.addDecreeCategoryToCollectionIfMissing([], null, decreeCategory, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(decreeCategory);
      });

      it('should return initial array if no DecreeCategory is added', () => {
        const decreeCategoryCollection: IDecreeCategory[] = [{ id: 123 }];
        expectedResult = service.addDecreeCategoryToCollectionIfMissing(decreeCategoryCollection, undefined, null);
        expect(expectedResult).toEqual(decreeCategoryCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
