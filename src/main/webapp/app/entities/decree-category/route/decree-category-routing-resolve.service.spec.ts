import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDecreeCategory, DecreeCategory } from '../decree-category.model';
import { DecreeCategoryService } from '../service/decree-category.service';

import { DecreeCategoryRoutingResolveService } from './decree-category-routing-resolve.service';

describe('DecreeCategory routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DecreeCategoryRoutingResolveService;
  let service: DecreeCategoryService;
  let resultDecreeCategory: IDecreeCategory | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(DecreeCategoryRoutingResolveService);
    service = TestBed.inject(DecreeCategoryService);
    resultDecreeCategory = undefined;
  });

  describe('resolve', () => {
    it('should return IDecreeCategory returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDecreeCategory = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDecreeCategory).toEqual({ id: 123 });
    });

    it('should return new IDecreeCategory if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDecreeCategory = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDecreeCategory).toEqual(new DecreeCategory());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as DecreeCategory })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDecreeCategory = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDecreeCategory).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
