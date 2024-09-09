import { Component, OnInit } from '@angular/core';
import { Equipment } from '../model/equipment';
import { Router, ActivatedRoute } from '@angular/router';
import { EquipmentService } from '../service/equipment.service';

@Component({
  selector: 'app-equipment-list',
  templateUrl: './equipment-list.component.html',
  styleUrl: './equipment-list.component.css',
})
export class EquipmentListComponent implements OnInit{
  equipments: Equipment[] = [];
  equipmentsCart: Equipment[] = [];
  equipmentsTemp: Equipment[] = [];
  companyId: any;

  constructor(private router: Router, private equipmentService: EquipmentService, private route : ActivatedRoute) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      console.log(params); 
      this.companyId = params.companyId;
        });
    this.equipmentService.findForCompany(this.companyId).subscribe(data => {
      this.equipments = data;
      this.equipmentsTemp = data;
    });
  }

  /*public reserveEquipment(equipmentId : any){
    console.log("button clicked" + equipmentId);
  }*/

  gotoUserList() {
    this.router.navigate(['/users']);
  }

  formatLabel(value: number): string {
    if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }

    return `${value}`;
  }

  displayMyRanges(){
    var i = 0;
    if(this.equipmentsCart.length == 0){
    this.equipments.forEach(element => {
      var input = (document.getElementById("rang" + element.id) as HTMLInputElement).value;
      console.log(input);
      var diff = parseInt(input);
      this.equipments[i].quantity = this.equipments[i].quantity - diff;
      let e = new Equipment();
      e.equipmentName = this.equipments[i].equipmentName;
      e.quantity = e.quantity + diff;
      e.id = this.equipments[i].id;
      this.equipmentsCart.push(e);
      i++;
    });
  }else{
    this.equipments.forEach(element => {
      var input = (document.getElementById("rang" + element.id) as HTMLInputElement).value;
      console.log(input);
      var diff = parseInt(input);
      this.equipments[i].quantity = this.equipments[i].quantity - diff;
      this.equipmentsCart[i].quantity = this.equipmentsCart[i].quantity + diff;
      i++;
    });
  }
  }
  clearCart(){
    this.equipmentService.findForCompany(this.companyId).subscribe(data => {
      this.equipments = data;
    });
    this.equipmentsCart = [];
  }

  reserveEquipment(){
    console.log(this.equipments)
    console.log(this.equipmentsCart)
    this.router.navigate(['/reserveAppointment'], {queryParams: {equipments : JSON.stringify(this.equipments), equipmentsCart : JSON.stringify(this.equipmentsCart), companyId : this.companyId}})
  }

  updateCorrectLabel(){
    console.log("aaaa")
  }
}
