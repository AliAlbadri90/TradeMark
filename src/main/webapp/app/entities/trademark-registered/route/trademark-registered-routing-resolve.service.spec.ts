import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ITrademarkRegistered, TrademarkRegistered } from '../trademark-registered.model';
import { TrademarkRegisteredService } from '../service/trademark-registered.service';

import { TrademarkRegisteredRoutingResolveService } from './trademark-registered-routing-resolve.service';

describe('TrademarkRegistered routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: TrademarkRegisteredRoutingResolveService;
  let service: TrademarkRegisteredService;
  let resultTrademarkRegistered: ITrademarkRegistered | undefined;

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
    routingResolveService = TestBed.inject(TrademarkRegisteredRoutingResolveService);
    service = TestBed.inject(TrademarkRegisteredService);
    resultTrademarkRegistered = undefined;
  });

  describe('resolve', () => {
    it('should return ITrademarkRegistered returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTrademarkRegistered = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultTrademarkRegistered).toEqual({ id: 123 });
    });

    it('should return new ITrademarkRegistered if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTrademarkRegistered = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultTrademarkRegistered).toEqual(new TrademarkRegistered());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as TrademarkRegistered })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTrademarkRegistered = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultTrademarkRegistered).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
