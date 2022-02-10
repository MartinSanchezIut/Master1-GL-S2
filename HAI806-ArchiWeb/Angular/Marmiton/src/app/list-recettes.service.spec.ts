import { TestBed } from '@angular/core/testing';

import { ListRecettesService } from './list-recettes.service';

describe('ListRecettesService', () => {
  let service: ListRecettesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListRecettesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
