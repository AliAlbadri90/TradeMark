import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TrademarkRegisteredService } from '../service/trademark-registered.service';
import { ITrademarkRegistered, TrademarkRegistered } from '../trademark-registered.model';

import { TrademarkRegisteredUpdateComponent } from './trademark-registered-update.component';

describe('TrademarkRegistered Management Update Component', () => {
  let comp: TrademarkRegisteredUpdateComponent;
  let fixture: ComponentFixture<TrademarkRegisteredUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let trademarkRegisteredService: TrademarkRegisteredService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TrademarkRegisteredUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(TrademarkRegisteredUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TrademarkRegisteredUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    trademarkRegisteredService = TestBed.inject(TrademarkRegisteredService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const trademarkRegistered: ITrademarkRegistered = { id: 456 };

      activatedRoute.data = of({ trademarkRegistered });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(trademarkRegistered));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TrademarkRegistered>>();
      const trademarkRegistered = { id: 123 };
      jest.spyOn(trademarkRegisteredService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trademarkRegistered });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: trademarkRegistered }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(trademarkRegisteredService.update).toHaveBeenCalledWith(trademarkRegistered);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TrademarkRegistered>>();
      const trademarkRegistered = new TrademarkRegistered();
      jest.spyOn(trademarkRegisteredService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trademarkRegistered });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: trademarkRegistered }));
      saveSubject.complete();

      // THEN
      expect(trademarkRegisteredService.create).toHaveBeenCalledWith(trademarkRegistered);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TrademarkRegistered>>();
      const trademarkRegistered = { id: 123 };
      jest.spyOn(trademarkRegisteredService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trademarkRegistered });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(trademarkRegisteredService.update).toHaveBeenCalledWith(trademarkRegistered);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
