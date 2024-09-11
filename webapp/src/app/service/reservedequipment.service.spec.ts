import { TestBed } from '@angular/core/testing';

import { ReservedEquipmentService } from './reservedequipment.service';

describe('ReservedEquipmentService', () => {
  let service: ReservedEquipmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReservedEquipmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
