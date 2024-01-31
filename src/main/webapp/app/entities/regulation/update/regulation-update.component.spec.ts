import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RegulationService } from '../service/regulation.service';
import { IRegulation, Regulation } from '../regulation.model';

import { RegulationUpdateComponent } from './regulation-update.component';

describe('Regulation Management Update Component', () => {
  let comp: RegulationUpdateComponent;
  let fixture: ComponentFixture<RegulationUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let regulationService: RegulationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RegulationUpdateComponent],
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
      .overrideTemplate(RegulationUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RegulationUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    regulationService = TestBed.inject(RegulationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const regulation: IRegulation = { id: 456 };

      activatedRoute.data = of({ regulation });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(regulation));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Regulation>>();
      const regulation = { id: 123 };
      jest.spyOn(regulationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regulation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: regulation }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(regulationService.update).toHaveBeenCalledWith(regulation);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Regulation>>();
      const regulation = new Regulation();
      jest.spyOn(regulationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regulation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: regulation }));
      saveSubject.complete();

      // THEN
      expect(regulationService.create).toHaveBeenCalledWith(regulation);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Regulation>>();
      const regulation = { id: 123 };
      jest.spyOn(regulationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regulation });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(regulationService.update).toHaveBeenCalledWith(regulation);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
