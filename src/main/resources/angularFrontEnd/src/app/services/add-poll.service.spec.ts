import { TestBed } from '@angular/core/testing';

import { AddPollService } from './add-poll.service';

describe('AddPollService', () => {
  let service: AddPollService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddPollService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
