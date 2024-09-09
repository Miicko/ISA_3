import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Appointment } from '../model/appointment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': '*/*',
    })
   }

  public findForCompany(companyId : any): Observable<Appointment[]> {
    return this.http.get<Appointment[]>("http://localhost:8080/api/v1/companies/"+companyId+"/appointmentsnotreserved", {headers:this.headers});
  }
}
