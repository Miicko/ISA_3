import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersAppointmentsComponent } from './users-appointments.component';

describe('UsersAppointmentsComponent', () => {
  let component: UsersAppointmentsComponent;
  let fixture: ComponentFixture<UsersAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UsersAppointmentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsersAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
