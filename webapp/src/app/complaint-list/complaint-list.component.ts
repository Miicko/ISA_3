import { Component, OnInit } from '@angular/core';
import { Complaint } from '../model/complaint';
import { Router } from '@angular/router';
import { ComplaintService } from '../service/complaint.service';
import { AuthService } from '../service/auth-service.service';
import { User } from '../model/user';

@Component({
  selector: 'app-complaint-list',
  templateUrl: './complaint-list.component.html',
  styleUrl: './complaint-list.component.css'
})
export class ComplaintListComponent implements OnInit{
  complaints: Complaint[] = [];
  user: User;

  constructor(private router: Router, private complaintService: ComplaintService, private authService : AuthService) {
  }

  ngOnInit() {
    this.authService.getLoggedUser().subscribe(data => {
      this.user = data;
      this.complaintService.findForUser(this.user.id).subscribe(data => {
        this.complaints = data;
      });
    });
  }

  public reserveEquipment(companyId : any){
    console.log("button clicked" + companyId);
    this.gotoCompanyEquipments(companyId);
  }

  gotoCompanyEquipments(companyId : any) {
    this.router.navigate(['/equipments'], {queryParams: {companyId}});
  }
}
