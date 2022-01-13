import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GovernmentDetailComponent } from './government-detail.component';

describe('Government Management Detail Component', () => {
  let comp: GovernmentDetailComponent;
  let fixture: ComponentFixture<GovernmentDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GovernmentDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ government: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(GovernmentDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(GovernmentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load government on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.government).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
