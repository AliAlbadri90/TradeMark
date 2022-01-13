import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DecreeService } from '../service/decree.service';
import { IDecree, Decree } from '../decree.model';
import { IDecreeType } from 'app/entities/decree-type/decree-type.model';
import { DecreeTypeService } from 'app/entities/decree-type/service/decree-type.service';
import { IDecreeCategory } from 'app/entities/decree-category/decree-category.model';
import { DecreeCategoryService } from 'app/entities/decree-category/service/decree-category.service';
import { IMinister } from 'app/entities/minister/minister.model';
import { MinisterService } from 'app/entities/minister/service/minister.service';
import { IGovernment } from 'app/entities/government/government.model';
import { GovernmentService } from 'app/entities/government/service/government.service';

import { DecreeUpdateComponent } from './decree-update.component';

describe('Decree Management Update Component', () => {
  let comp: DecreeUpdateComponent;
  let fixture: ComponentFixture<DecreeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let decreeService: DecreeService;
  let decreeTypeService: DecreeTypeService;
  let decreeCategoryService: DecreeCategoryService;
  let ministerService: MinisterService;
  let governmentService: GovernmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DecreeUpdateComponent],
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
      .overrideTemplate(DecreeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DecreeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    decreeService = TestBed.inject(DecreeService);
    decreeTypeService = TestBed.inject(DecreeTypeService);
    decreeCategoryService = TestBed.inject(DecreeCategoryService);
    ministerService = TestBed.inject(MinisterService);
    governmentService = TestBed.inject(GovernmentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call DecreeType query and add missing value', () => {
      const decree: IDecree = { id: 456 };
      const decreeType: IDecreeType = { id: 16660 };
      decree.decreeType = decreeType;

      const decreeTypeCollection: IDecreeType[] = [{ id: 11297 }];
      jest.spyOn(decreeTypeService, 'query').mockReturnValue(of(new HttpResponse({ body: decreeTypeCollection })));
      const additionalDecreeTypes = [decreeType];
      const expectedCollection: IDecreeType[] = [...additionalDecreeTypes, ...decreeTypeCollection];
      jest.spyOn(decreeTypeService, 'addDecreeTypeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ decree });
      comp.ngOnInit();

      expect(decreeTypeService.query).toHaveBeenCalled();
      expect(decreeTypeService.addDecreeTypeToCollectionIfMissing).toHaveBeenCalledWith(decreeTypeCollection, ...additionalDecreeTypes);
      expect(comp.decreeTypesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call DecreeCategory query and add missing value', () => {
      const decree: IDecree = { id: 456 };
      const decreeCategory: IDecreeCategory = { id: 87132 };
      decree.decreeCategory = decreeCategory;

      const decreeCategoryCollection: IDecreeCategory[] = [{ id: 37761 }];
      jest.spyOn(decreeCategoryService, 'query').mockReturnValue(of(new HttpResponse({ body: decreeCategoryCollection })));
      const additionalDecreeCategories = [decreeCategory];
      const expectedCollection: IDecreeCategory[] = [...additionalDecreeCategories, ...decreeCategoryCollection];
      jest.spyOn(decreeCategoryService, 'addDecreeCategoryToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ decree });
      comp.ngOnInit();

      expect(decreeCategoryService.query).toHaveBeenCalled();
      expect(decreeCategoryService.addDecreeCategoryToCollectionIfMissing).toHaveBeenCalledWith(
        decreeCategoryCollection,
        ...additionalDecreeCategories
      );
      expect(comp.decreeCategoriesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Minister query and add missing value', () => {
      const decree: IDecree = { id: 456 };
      const minister: IMinister = { id: 75002 };
      decree.minister = minister;

      const ministerCollection: IMinister[] = [{ id: 3163 }];
      jest.spyOn(ministerService, 'query').mockReturnValue(of(new HttpResponse({ body: ministerCollection })));
      const additionalMinisters = [minister];
      const expectedCollection: IMinister[] = [...additionalMinisters, ...ministerCollection];
      jest.spyOn(ministerService, 'addMinisterToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ decree });
      comp.ngOnInit();

      expect(ministerService.query).toHaveBeenCalled();
      expect(ministerService.addMinisterToCollectionIfMissing).toHaveBeenCalledWith(ministerCollection, ...additionalMinisters);
      expect(comp.ministersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Government query and add missing value', () => {
      const decree: IDecree = { id: 456 };
      const government: IGovernment = { id: 72918 };
      decree.government = government;

      const governmentCollection: IGovernment[] = [{ id: 20657 }];
      jest.spyOn(governmentService, 'query').mockReturnValue(of(new HttpResponse({ body: governmentCollection })));
      const additionalGovernments = [government];
      const expectedCollection: IGovernment[] = [...additionalGovernments, ...governmentCollection];
      jest.spyOn(governmentService, 'addGovernmentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ decree });
      comp.ngOnInit();

      expect(governmentService.query).toHaveBeenCalled();
      expect(governmentService.addGovernmentToCollectionIfMissing).toHaveBeenCalledWith(governmentCollection, ...additionalGovernments);
      expect(comp.governmentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const decree: IDecree = { id: 456 };
      const decreeType: IDecreeType = { id: 54854 };
      decree.decreeType = decreeType;
      const decreeCategory: IDecreeCategory = { id: 88991 };
      decree.decreeCategory = decreeCategory;
      const minister: IMinister = { id: 19242 };
      decree.minister = minister;
      const government: IGovernment = { id: 34978 };
      decree.government = government;

      activatedRoute.data = of({ decree });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(decree));
      expect(comp.decreeTypesSharedCollection).toContain(decreeType);
      expect(comp.decreeCategoriesSharedCollection).toContain(decreeCategory);
      expect(comp.ministersSharedCollection).toContain(minister);
      expect(comp.governmentsSharedCollection).toContain(government);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Decree>>();
      const decree = { id: 123 };
      jest.spyOn(decreeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ decree });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: decree }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(decreeService.update).toHaveBeenCalledWith(decree);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Decree>>();
      const decree = new Decree();
      jest.spyOn(decreeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ decree });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: decree }));
      saveSubject.complete();

      // THEN
      expect(decreeService.create).toHaveBeenCalledWith(decree);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Decree>>();
      const decree = { id: 123 };
      jest.spyOn(decreeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ decree });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(decreeService.update).toHaveBeenCalledWith(decree);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackDecreeTypeById', () => {
      it('Should return tracked DecreeType primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackDecreeTypeById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackDecreeCategoryById', () => {
      it('Should return tracked DecreeCategory primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackDecreeCategoryById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackMinisterById', () => {
      it('Should return tracked Minister primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackMinisterById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackGovernmentById', () => {
      it('Should return tracked Government primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackGovernmentById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
