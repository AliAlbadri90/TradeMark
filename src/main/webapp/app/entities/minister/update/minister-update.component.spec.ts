import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MinisterService } from '../service/minister.service';
import { IMinister, Minister } from '../minister.model';

import { MinisterUpdateComponent } from './minister-update.component';

describe('Minister Management Update Component', () => {
  let comp: MinisterUpdateComponent;
  let fixture: ComponentFixture<MinisterUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ministerService: MinisterService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MinisterUpdateComponent],
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
      .overrideTemplate(MinisterUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MinisterUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ministerService = TestBed.inject(MinisterService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const minister: IMinister = { id: 456 };

      activatedRoute.data = of({ minister });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(minister));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Minister>>();
      const minister = { id: 123 };
      jest.spyOn(ministerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ minister });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: minister }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(ministerService.update).toHaveBeenCalledWith(minister);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Minister>>();
      const minister = new Minister();
      jest.spyOn(ministerService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ minister });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: minister }));
      saveSubject.complete();

      // THEN
      expect(ministerService.create).toHaveBeenCalledWith(minister);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Minister>>();
      const minister = { id: 123 };
      jest.spyOn(ministerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ minister });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ministerService.update).toHaveBeenCalledWith(minister);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
