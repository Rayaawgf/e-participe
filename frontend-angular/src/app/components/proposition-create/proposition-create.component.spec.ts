import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropositionCreateComponent } from './proposition-create.component';

describe('PropositionCreateComponent', () => {
  let component: PropositionCreateComponent;
  let fixture: ComponentFixture<PropositionCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PropositionCreateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropositionCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
