import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDecreeType, DecreeType } from '../decree-type.model';
import { DecreeTypeService } from '../service/decree-type.service';

import { DecreeTypeRoutingResolveService } from './decree-type-routing-resolve.service';

describe('DecreeType routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DecreeTypeRoutingResolveService;
  let service: DecreeTypeService;
  let resultDecreeType: IDecreeType | undefined;

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
    routingResolveService = TestBed.inject(DecreeTypeRoutingResolveService);
    service = TestBed.inject(DecreeTypeService);
    resultDecreeType = undefined;
  });

  describe('resolve', () => {
    it('should return IDecreeType returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDecreeType = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDecreeType).toEqual({ id: 123 });
    });

    it('should return new IDecreeType if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDecreeType = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDecreeType).toEqual(new DecreeType());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as DecreeType })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDecreeType = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDecreeType).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
