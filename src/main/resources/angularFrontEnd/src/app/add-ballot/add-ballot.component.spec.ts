import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBallotComponent } from './add-ballot.component';

describe('AddBallotComponent', () => {
  let component: AddBallotComponent;
  let fixture: ComponentFixture<AddBallotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddBallotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBallotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
