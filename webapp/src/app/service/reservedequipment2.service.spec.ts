import { TestBed } from '@angular/core/testing';

import { Reservedequipment2Service } from './reservedequipment2.service';

describe('Reservedequipment2Service', () => {
  let service: Reservedequipment2Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Reservedequipment2Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
