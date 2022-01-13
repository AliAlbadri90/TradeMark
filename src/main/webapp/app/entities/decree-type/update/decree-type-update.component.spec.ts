import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DecreeTypeService } from '../service/decree-type.service';
import { IDecreeType, DecreeType } from '../decree-type.model';

import { DecreeTypeUpdateComponent } from './decree-type-update.component';

describe('DecreeType Management Update Component', () => {
  let comp: DecreeTypeUpdateComponent;
  let fixture: ComponentFixture<DecreeTypeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let decreeTypeService: DecreeTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DecreeTypeUpdateComponent],
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
      .overrideTemplate(DecreeTypeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DecreeTypeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    decreeTypeService = TestBed.inject(DecreeTypeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const decreeType: IDecreeType = { id: 456 };

      activatedRoute.data = of({ decreeType });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(decreeType));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DecreeType>>();
      const decreeType = { id: 123 };
      jest.spyOn(decreeTypeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ decreeType });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: decreeType }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(decreeTypeService.update).toHaveBeenCalledWith(decreeType);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DecreeType>>();
      const decreeType = new DecreeType();
      jest.spyOn(decreeTypeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ decreeType });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: decreeType }));
      saveSubject.complete();

      // THEN
      expect(decreeTypeService.create).toHaveBeenCalledWith(decreeType);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DecreeType>>();
      const decreeType = { id: 123 };
      jest.spyOn(decreeTypeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ decreeType });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(decreeTypeService.update).toHaveBeenCalledWith(decreeType);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
