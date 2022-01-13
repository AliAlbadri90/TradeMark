import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DecreeCategoryDetailComponent } from './decree-category-detail.component';

describe('DecreeCategory Management Detail Component', () => {
  let comp: DecreeCategoryDetailComponent;
  let fixture: ComponentFixture<DecreeCategoryDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DecreeCategoryDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ decreeCategory: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(DecreeCategoryDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DecreeCategoryDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load decreeCategory on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.decreeCategory).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
