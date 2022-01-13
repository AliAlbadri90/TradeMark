import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DecreeCategoryService } from '../service/decree-category.service';
import { IDecreeCategory, DecreeCategory } from '../decree-category.model';

import { DecreeCategoryUpdateComponent } from './decree-category-update.component';

describe('DecreeCategory Management Update Component', () => {
  let comp: DecreeCategoryUpdateComponent;
  let fixture: ComponentFixture<DecreeCategoryUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let decreeCategoryService: DecreeCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DecreeCategoryUpdateComponent],
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
      .overrideTemplate(DecreeCategoryUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DecreeCategoryUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    decreeCategoryService = TestBed.inject(DecreeCategoryService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const decreeCategory: IDecreeCategory = { id: 456 };

      activatedRoute.data = of({ decreeCategory });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(decreeCategory));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DecreeCategory>>();
      const decreeCategory = { id: 123 };
      jest.spyOn(decreeCategoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ decreeCategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: decreeCategory }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(decreeCategoryService.update).toHaveBeenCalledWith(decreeCategory);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DecreeCategory>>();
      const decreeCategory = new DecreeCategory();
      jest.spyOn(decreeCategoryService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ decreeCategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: decreeCategory }));
      saveSubject.complete();

      // THEN
      expect(decreeCategoryService.create).toHaveBeenCalledWith(decreeCategory);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DecreeCategory>>();
      const decreeCategory = { id: 123 };
      jest.spyOn(decreeCategoryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ decreeCategory });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(decreeCategoryService.update).toHaveBeenCalledWith(decreeCategory);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
