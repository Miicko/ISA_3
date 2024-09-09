import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentService } from '../service/appointment.service';
import { Equipment } from '../model/equipment';

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

  constructor(private router: Router, private appointmentService: AppointmentService, private route : ActivatedRoute) {
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
    //this.appointmentService.findForCompany().subscribe(data => {
    //  this.appointments = data;
    //});
  }

  public reserveAppointment(){
    console.log("button clicked");
  }

  gotoCompanyEquipments(companyId : any) {
    this.router.navigate(['/equipments'], {queryParams: {companyId}});
  }
}
