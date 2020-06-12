import { TestBed } from '@angular/core/testing';

import { ElasticService } from './elastic.service';

describe('ElasticService', () => {
  let service: ElasticService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ElasticService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
