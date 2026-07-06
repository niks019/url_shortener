import { ComponentFixture, TestBed } from '@angular/core/testing';

import { URLLists } from './urllists';

describe('URLLists', () => {
  let component: URLLists;
  let fixture: ComponentFixture<URLLists>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [URLLists]
    })
    .compileComponents();

    fixture = TestBed.createComponent(URLLists);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
