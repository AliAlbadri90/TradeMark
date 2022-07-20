import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ViewLogService } from '../service/view-log.service';
import { IViewLog, ViewLog } from '../view-log.model';

import { ViewLogUpdateComponent } from './view-log-update.component';

describe('ViewLog Management Update Component', () => {
  let comp: ViewLogUpdateComponent;
  let fixture: ComponentFixture<ViewLogUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let viewLogService: ViewLogService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ViewLogUpdateComponent],
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
      .overrideTemplate(ViewLogUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ViewLogUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    viewLogService = TestBed.inject(ViewLogService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const viewLog: IViewLog = { id: 456 };

      activatedRoute.data = of({ viewLog });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(viewLog));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ViewLog>>();
      const viewLog = { id: 123 };
      jest.spyOn(viewLogService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ viewLog });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: viewLog }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(viewLogService.update).toHaveBeenCalledWith(viewLog);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ViewLog>>();
      const viewLog = new ViewLog();
      jest.spyOn(viewLogService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ viewLog });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: viewLog }));
      saveSubject.complete();

      // THEN
      expect(viewLogService.create).toHaveBeenCalledWith(viewLog);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ViewLog>>();
      const viewLog = { id: 123 };
      jest.spyOn(viewLogService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ viewLog });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(viewLogService.update).toHaveBeenCalledWith(viewLog);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
