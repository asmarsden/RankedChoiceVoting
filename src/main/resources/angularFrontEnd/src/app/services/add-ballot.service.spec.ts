import { TestBed } from '@angular/core/testing';

import { AddBallotService } from './add-ballot.service';

describe('AddBallotService', () => {
  let service: AddBallotService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddBallotService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
