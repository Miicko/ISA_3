import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentService } from '../service/appointment.service';
import { Equipment } from '../model/equipment';
import { EquipmentService } from '../service/equipment.service';
import { UserService } from '../service/user-service.service';
import { ReservedEquipmentService } from '../service/reservedequipment.service';
import { AuthService } from '../service/auth-service.service';
import { User } from '../model/user';
import { delay, Observable, of } from 'rxjs';

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrl: './appointment-list.component.css'
})
export class AppointmentListComponent implements OnInit{
  appointments: Appointment[] = [];
  companyId: any;
  equipments : Equipment[] = [];
  equipmentsCart : Equipment[] = [];
  user : User;
  userId : number;
  appointmentForReservation : Appointment;

  constructor(private router: Router, private appointmentService: AppointmentService, private route : ActivatedRoute, private equipmentService: EquipmentService, private userService: UserService, private reservedEquipmentService: ReservedEquipmentService, private authservice: AuthService) {
    this.route.queryParams.subscribe(params => {
      console.log(params)
      this.companyId = params.companyId;
      this.equipments = JSON.parse(params.equipments);
      this.equipmentsCart = JSON.parse(params.equipmentsCart);
        });
      console.log(this.companyId);
      console.log(this.equipments);
      console.log(this.equipmentsCart);
  }

  ngOnInit() {
    this.appointmentService.findForCompany(this.companyId).subscribe(data => {
    this.appointments = data;
    });
    this.authservice.getLoggedUser().subscribe(data => {
      this.user = data;
      console.log("ucitao usera");
    });
  }

  public reserveAppointment(app : any){
    console.log("button clicked" + app);
    this.equipments.forEach(element => {
      console.log(element)
    });
    this.equipmentsCart.forEach(element => {
      console.log(element)
    });
    console.log(this.companyId);
    console.log(this.user);
    this.appointments.forEach(element => {
      if(element.id == app){
        console.log(element);
        this.appointmentForReservation = element;
      }
    });
    console.log("++++++++++++++++++++++++++++++++++++++++++++++");
    this.tryToSaveAll(this.appointmentForReservation);
  }

  tryToSaveAll(app : Appointment) : boolean{
    this.equipments.forEach(element => {
      this.equipmentService.updateEquipment(this.companyId, element).subscribe(result => console.log(result));
    });
    this.equipmentsCart.forEach(element => {
      this.reservedEquipmentService.createReservedEquipment(app.id, element).subscribe(result => console.log(result));
    });
    app.reserved = true;
    app.userId = this.user.id;
    console.log(app);
    this.appointmentService.updateAppointment(app).subscribe(result => {
      console.log(result)
      this.gotoUserAppointments();
    });
    return true;
  }

  gotoUserAppointments() {
    this.router.navigate(['/userAppointments']);
  }
}
