import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ViewLogDetailComponent } from './view-log-detail.component';

describe('ViewLog Management Detail Component', () => {
  let comp: ViewLogDetailComponent;
  let fixture: ComponentFixture<ViewLogDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewLogDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ viewLog: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ViewLogDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ViewLogDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load viewLog on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.viewLog).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
