import { TestBed } from '@angular/core/testing';

import { AddTutorialService } from './add-tutorial.service';

describe('AddTutorialService', () => {
  let service: AddTutorialService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddTutorialService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
