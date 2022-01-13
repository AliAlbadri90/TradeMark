import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { GovernmentService } from '../service/government.service';
import { IGovernment, Government } from '../government.model';

import { GovernmentUpdateComponent } from './government-update.component';

describe('Government Management Update Component', () => {
  let comp: GovernmentUpdateComponent;
  let fixture: ComponentFixture<GovernmentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let governmentService: GovernmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [GovernmentUpdateComponent],
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
      .overrideTemplate(GovernmentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(GovernmentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    governmentService = TestBed.inject(GovernmentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const government: IGovernment = { id: 456 };

      activatedRoute.data = of({ government });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(government));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Government>>();
      const government = { id: 123 };
      jest.spyOn(governmentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ government });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: government }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(governmentService.update).toHaveBeenCalledWith(government);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Government>>();
      const government = new Government();
      jest.spyOn(governmentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ government });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: government }));
      saveSubject.complete();

      // THEN
      expect(governmentService.create).toHaveBeenCalledWith(government);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Government>>();
      const government = { id: 123 };
      jest.spyOn(governmentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ government });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(governmentService.update).toHaveBeenCalledWith(government);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
