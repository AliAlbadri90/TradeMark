import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ITrademarkDecree, TrademarkDecree } from '../trademark-decree.model';
import { TrademarkDecreeService } from '../service/trademark-decree.service';

import { TrademarkDecreeRoutingResolveService } from './trademark-decree-routing-resolve.service';

describe('TrademarkDecree routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: TrademarkDecreeRoutingResolveService;
  let service: TrademarkDecreeService;
  let resultTrademarkDecree: ITrademarkDecree | undefined;

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
    routingResolveService = TestBed.inject(TrademarkDecreeRoutingResolveService);
    service = TestBed.inject(TrademarkDecreeService);
    resultTrademarkDecree = undefined;
  });

  describe('resolve', () => {
    it('should return ITrademarkDecree returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTrademarkDecree = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultTrademarkDecree).toEqual({ id: 123 });
    });

    it('should return new ITrademarkDecree if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTrademarkDecree = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultTrademarkDecree).toEqual(new TrademarkDecree());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as TrademarkDecree })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTrademarkDecree = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultTrademarkDecree).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
