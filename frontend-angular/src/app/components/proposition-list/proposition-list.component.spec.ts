import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropositionListComponent } from './proposition-list.component';

describe('PropositionListComponent', () => {
  let component: PropositionListComponent;
  let fixture: ComponentFixture<PropositionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PropositionListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropositionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
