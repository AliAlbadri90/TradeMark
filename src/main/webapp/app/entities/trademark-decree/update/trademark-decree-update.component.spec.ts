import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TrademarkDecreeService } from '../service/trademark-decree.service';
import { ITrademarkDecree, TrademarkDecree } from '../trademark-decree.model';

import { TrademarkDecreeUpdateComponent } from './trademark-decree-update.component';

describe('TrademarkDecree Management Update Component', () => {
  let comp: TrademarkDecreeUpdateComponent;
  let fixture: ComponentFixture<TrademarkDecreeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let trademarkDecreeService: TrademarkDecreeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TrademarkDecreeUpdateComponent],
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
      .overrideTemplate(TrademarkDecreeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TrademarkDecreeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    trademarkDecreeService = TestBed.inject(TrademarkDecreeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const trademarkDecree: ITrademarkDecree = { id: 456 };

      activatedRoute.data = of({ trademarkDecree });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(trademarkDecree));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TrademarkDecree>>();
      const trademarkDecree = { id: 123 };
      jest.spyOn(trademarkDecreeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trademarkDecree });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: trademarkDecree }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(trademarkDecreeService.update).toHaveBeenCalledWith(trademarkDecree);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TrademarkDecree>>();
      const trademarkDecree = new TrademarkDecree();
      jest.spyOn(trademarkDecreeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trademarkDecree });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: trademarkDecree }));
      saveSubject.complete();

      // THEN
      expect(trademarkDecreeService.create).toHaveBeenCalledWith(trademarkDecree);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TrademarkDecree>>();
      const trademarkDecree = { id: 123 };
      jest.spyOn(trademarkDecreeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trademarkDecree });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(trademarkDecreeService.update).toHaveBeenCalledWith(trademarkDecree);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
