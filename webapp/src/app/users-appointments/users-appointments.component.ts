import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment';
import { Router } from '@angular/router';
import { AppointmentService } from '../service/appointment.service';
import { AuthService } from '../service/auth-service.service';
import { User } from '../model/user';
import { delay, Observable, of } from 'rxjs';
import { EquipmentService } from '../service/equipment.service';
import { ReservedEquipmentService } from '../service/reservedequipment.service';
import { Reservedequipment } from '../model/reservedequipment';
import { Equipment } from '../model/equipment';

@Component({
  selector: 'app-users-appointments',
  templateUrl: './users-appointments.component.html',
  styleUrl: './users-appointments.component.css'
})
export class UsersAppointmentsComponent implements OnInit {
  appointments: Appointment[] = [];
  appointmentsHistory: Appointment[] = [];
  user : User;
  appointment : Appointment;
  appointmentFinal : Appointment;
  reservedEquipments : Reservedequipment[] = [];
  equipments : Equipment[] = [];
  finalequipments : Equipment[] = [];

  constructor(private router: Router, private appointmentService: AppointmentService, private authService : AuthService, private equipmentService : EquipmentService, private reservedEquipmentService : ReservedEquipmentService) {
  }

  ngOnInit() {
    this.authService.getLoggedUser().subscribe(data => {
      this.user = data;
      this.reloadApp();
    });
    
  }

  public cancelAppointment(appId : any){
    console.log("button clicked" + appId);
    this.cancelAppointmentSer(appId);
  }

  cancelAppointmentSer(appId : any){
    this.appointmentService.findById(appId).subscribe(data => {
      this.appointment = data;
      console.log(this.appointment);

      this.equipmentService.findForCompany(this.appointment.cid).subscribe(data => {
        this.equipments = data;
        console.log(this.equipments);
        this.reservedEquipmentService.findForAppointment(appId).subscribe(data => {
          this.reservedEquipments = data;
          console.log(this.reservedEquipments);

          this.equipments.forEach(element => {
            this.reservedEquipments.forEach(elem2 => {
              if (element.equipmentName == elem2.equipmentName){
                element.quantity += elem2.quantity;
              }
            })
          })
          console.log(this.equipments);
          this.reservedEquipments.forEach(el => {
            this.reservedEquipmentService.deleteReservedEquipment(el).subscribe(result => console.log(result));
          });
          this.equipments.forEach(el => {
            this.equipmentService.updateEquipment(this.appointment.cid, el).subscribe(result => console.log(result));
          });
          this.appointment.reserved = false;
          this.appointment.userId = -1;
          console.log(this.appointment);
          this.appointmentService.cancelAppointment(this.appointment).subscribe(result => {
            console.log(result)
            this.reloadApp()
          });
        });
      });
    });
  }
  finalizeAppointment(appId : any){
    this.appointmentService.findById(appId).subscribe(data => {
      this.appointmentFinal = data;
      this.appointmentFinal.done = true;
      console.log(this.appointmentFinal);
      this.appointmentService.updateAppointment(this.appointmentFinal).subscribe(res => {console.log(res)});
      this.reloadApp();
    });
  }

  gotoCompanyEquipments(companyId : any) {
    this.router.navigate(['/equipments'], {queryParams: {companyId}});
  }
  reloadApp() : Observable<boolean>{
    this.appointmentService.findForUser(this.user.id).subscribe(data => {
      this.appointments = data;
    });
    this.appointmentService.findForUserDone(this.user.id).subscribe(data => {
      this.appointmentsHistory = data;
    });
    return of(true);
  }
}
