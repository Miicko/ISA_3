import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user-service.service';
import { ComplaintService } from '../service/complaint.service';
import { Complaint } from '../model/complaint';
import { AppointmentService } from '../service/appointment.service';
import { AuthService } from '../service/auth-service.service';
import { Appointment } from '../model/appointment';

@Component({
  selector: 'app-complaint-form',
  templateUrl: './complaint-form.component.html',
  styleUrl: './complaint-form.component.css'
})
export class ComplaintFormComponent implements OnInit{
  user: User;
  createComplaintForm: FormGroup;
  submitted = false;
  complaint : Complaint;
  companyId : any;
  appointmentsCheck: Appointment[] = [];

  constructor(
    private route: ActivatedRoute, 
    private router: Router, 
    private userService: UserService,
    private formBuilder: FormBuilder,
    private complaintService: ComplaintService,
    private appointmentService: AppointmentService,
    private authService: AuthService,
  ) 
  {
    this.route.queryParams.subscribe(params => {
      console.log(params)
      this.companyId = params.companyId;
    });
    console.log(this.companyId);
    authService.getLoggedUser().subscribe(res => {
      this.user = res;
      appointmentService.findForUserAndCompanyDone(this.user.id, this.companyId).subscribe(res => {
        this.appointmentsCheck = res;
        if(this.appointmentsCheck.length < 1){
          alert("You have to have at least one appointment that has been done in this company to leave complaint!");
          this.gotoCompanies();
        }
      });
    });
    this.complaint = new Complaint();
  }

  ngOnInit() {
    this.createComplaintForm = this.formBuilder.group(
      {
        ct: ['', Validators.required],
      },

    );
    console.log("ngoninit");
  }

  get f() {
    return this.createComplaintForm.controls;
  }

  onSubmit() {
    console.log("submit clicked");
    this.submitted = true;

    if (this.createComplaintForm.invalid) {
      return;
    }
    
    console.log("submit");
    this.complaint.complaintText = this.createComplaintForm.controls['ct'].value;
    this.complaint.companyId = this.companyId;
    this.complaint.complaintResponse = '';
    console.log(this.complaint)
    alert(
      'SUCCESS!! :-)\n\n' + JSON.stringify(this.complaint, null, 6)
    );
    this.complaintService.createComplaint(this.user.id, this.complaint).subscribe(res => {
      this.gotoComplaints();
    })
  }

  gotoComplaints() {
    this.router.navigate(['/userComplaints']);
  }
  gotoCompanies() {
    this.router.navigate(['/companies']);
  }
}
