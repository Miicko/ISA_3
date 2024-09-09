import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Equipment } from '../model/equipment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EquipmentService {
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': '*/*',
    })
   }

  public findForCompany(companyId : any): Observable<Equipment[]> {
    return this.http.get<Equipment[]>("http://localhost:8080/api/v1/companies/"+companyId+"/equipments", {headers:this.headers});
  }
}
