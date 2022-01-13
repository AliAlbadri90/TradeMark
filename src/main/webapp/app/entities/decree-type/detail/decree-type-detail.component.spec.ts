import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DecreeTypeDetailComponent } from './decree-type-detail.component';

describe('DecreeType Management Detail Component', () => {
  let comp: DecreeTypeDetailComponent;
  let fixture: ComponentFixture<DecreeTypeDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DecreeTypeDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ decreeType: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(DecreeTypeDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DecreeTypeDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load decreeType on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.decreeType).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
