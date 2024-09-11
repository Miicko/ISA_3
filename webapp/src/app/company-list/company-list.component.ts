import { Component, OnInit } from '@angular/core';
import { Company } from '../model/company';
import { CompanyService } from '../service/company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrl: './company-list.component.css'
})
export class CompanyListComponent implements OnInit{
  companies: Company[] = [];

  constructor(private router: Router, private companyService: CompanyService) {
  }

  ngOnInit() {
    this.companyService.findAll().subscribe(data => {
      this.companies = data;
    });
  }

  public reserveEquipment(companyId : any){
    console.log("button clicked" + companyId);
    this.gotoCompanyEquipments(companyId);
  }
  public leaveComplaint(companyId : any){
    console.log("button clicked" + companyId);
    this.gotoComplaint(companyId);
  }

  gotoCompanyEquipments(companyId : any) {
    this.router.navigate(['/equipments'], {queryParams: {companyId}});
  }
  gotoComplaint(companyId : any) {
    this.router.navigate(['/leaveComplaint'], {queryParams: {companyId}});
  }
}
