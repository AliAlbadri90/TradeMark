import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ComplaintService } from '../service/complaint.service';
import { IComplaint, Complaint } from '../complaint.model';

import { ComplaintUpdateComponent } from './complaint-update.component';

describe('Complaint Management Update Component', () => {
  let comp: ComplaintUpdateComponent;
  let fixture: ComponentFixture<ComplaintUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let complaintService: ComplaintService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ComplaintUpdateComponent],
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
      .overrideTemplate(ComplaintUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ComplaintUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    complaintService = TestBed.inject(ComplaintService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const complaint: IComplaint = { id: 456 };

      activatedRoute.data = of({ complaint });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(complaint));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Complaint>>();
      const complaint = { id: 123 };
      jest.spyOn(complaintService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ complaint });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: complaint }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(complaintService.update).toHaveBeenCalledWith(complaint);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Complaint>>();
      const complaint = new Complaint();
      jest.spyOn(complaintService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ complaint });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: complaint }));
      saveSubject.complete();

      // THEN
      expect(complaintService.create).toHaveBeenCalledWith(complaint);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Complaint>>();
      const complaint = { id: 123 };
      jest.spyOn(complaintService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ complaint });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(complaintService.update).toHaveBeenCalledWith(complaint);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
