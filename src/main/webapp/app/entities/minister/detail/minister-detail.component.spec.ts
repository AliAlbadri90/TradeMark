import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MinisterDetailComponent } from './minister-detail.component';

describe('Minister Management Detail Component', () => {
  let comp: MinisterDetailComponent;
  let fixture: ComponentFixture<MinisterDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MinisterDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ minister: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(MinisterDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(MinisterDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load minister on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.minister).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
