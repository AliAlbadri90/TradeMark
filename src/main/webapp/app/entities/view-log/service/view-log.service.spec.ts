import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IViewLog, ViewLog } from '../view-log.model';

import { ViewLogService } from './view-log.service';

describe('ViewLog Service', () => {
  let service: ViewLogService;
  let httpMock: HttpTestingController;
  let elemDefault: IViewLog;
  let expectedResult: IViewLog | IViewLog[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ViewLogService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      actionName: 'AAAAAAA',
      entityName: 'AAAAAAA',
      entityId: 'AAAAAAA',
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

    it('should create a ViewLog', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new ViewLog()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ViewLog', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          actionName: 'BBBBBB',
          entityName: 'BBBBBB',
          entityId: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ViewLog', () => {
      const patchObject = Object.assign(
        {
          entityId: 'BBBBBB',
        },
        new ViewLog()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ViewLog', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          actionName: 'BBBBBB',
          entityName: 'BBBBBB',
          entityId: 'BBBBBB',
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

    it('should delete a ViewLog', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addViewLogToCollectionIfMissing', () => {
      it('should add a ViewLog to an empty array', () => {
        const viewLog: IViewLog = { id: 123 };
        expectedResult = service.addViewLogToCollectionIfMissing([], viewLog);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(viewLog);
      });

      it('should not add a ViewLog to an array that contains it', () => {
        const viewLog: IViewLog = { id: 123 };
        const viewLogCollection: IViewLog[] = [
          {
            ...viewLog,
          },
          { id: 456 },
        ];
        expectedResult = service.addViewLogToCollectionIfMissing(viewLogCollection, viewLog);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ViewLog to an array that doesn't contain it", () => {
        const viewLog: IViewLog = { id: 123 };
        const viewLogCollection: IViewLog[] = [{ id: 456 }];
        expectedResult = service.addViewLogToCollectionIfMissing(viewLogCollection, viewLog);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(viewLog);
      });

      it('should add only unique ViewLog to an array', () => {
        const viewLogArray: IViewLog[] = [{ id: 123 }, { id: 456 }, { id: 98051 }];
        const viewLogCollection: IViewLog[] = [{ id: 123 }];
        expectedResult = service.addViewLogToCollectionIfMissing(viewLogCollection, ...viewLogArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const viewLog: IViewLog = { id: 123 };
        const viewLog2: IViewLog = { id: 456 };
        expectedResult = service.addViewLogToCollectionIfMissing([], viewLog, viewLog2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(viewLog);
        expect(expectedResult).toContain(viewLog2);
      });

      it('should accept null and undefined values', () => {
        const viewLog: IViewLog = { id: 123 };
        expectedResult = service.addViewLogToCollectionIfMissing([], null, viewLog, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(viewLog);
      });

      it('should return initial array if no ViewLog is added', () => {
        const viewLogCollection: IViewLog[] = [{ id: 123 }];
        expectedResult = service.addViewLogToCollectionIfMissing(viewLogCollection, undefined, null);
        expect(expectedResult).toEqual(viewLogCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
