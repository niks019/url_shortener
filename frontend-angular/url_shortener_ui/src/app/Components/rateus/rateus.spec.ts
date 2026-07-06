import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Rateus } from './rateus';

describe('Rateus', () => {
  let component: Rateus;
  let fixture: ComponentFixture<Rateus>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Rateus]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Rateus);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
