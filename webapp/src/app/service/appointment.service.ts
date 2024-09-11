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
  public findForUser(userId : any): Observable<Appointment[]> {
    return this.http.get<Appointment[]>("http://localhost:8080/api/v1/users/"+userId+"/appointments", {headers:this.headers});
  }
  public findForUserDone(userId : any): Observable<Appointment[]> {
    return this.http.get<Appointment[]>("http://localhost:8080/api/v1/users/"+userId+"/appointments/done", {headers:this.headers});
  }
  public findForUserAndCompanyDone(userId : any, companyId: any): Observable<Appointment[]> {
    return this.http.get<Appointment[]>("http://localhost:8080/api/v1/users/"+userId+"/appointments/done/"+companyId, {headers:this.headers});
  }
  public findById(appId : any): Observable<Appointment> {
    return this.http.get<Appointment>("http://localhost:8080/api/v1/appointments/"+appId, {headers:this.headers});
  }
  public updateAppointment(req : Appointment) {
    return this.http.put<any>("http://localhost:8080/api/v1/appointments/"+ req.id, JSON.stringify(req), {headers:this.headers});
  }
  public cancelAppointment(req : Appointment) {
    return this.http.put<any>("http://localhost:8080/api/v1/appointments/cancel/"+ req.id, JSON.stringify(req), {headers:this.headers});
  }
}
