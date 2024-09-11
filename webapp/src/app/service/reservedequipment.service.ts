import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Equipment } from '../model/equipment';
import { Observable } from 'rxjs';
import { Reservedequipment } from '../model/reservedequipment';

@Injectable({
  providedIn: 'root'
})
export class ReservedEquipmentService {
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': '*/*',
    })
   }

  public findForAppointment(appId : any): Observable<Reservedequipment[]> {
    return this.http.get<Reservedequipment[]>("http://localhost:8080/api/v1/appointments/"+appId+"/reservedequipments", {headers:this.headers});
  }
  public createReservedEquipment(appId: any, req : Reservedequipment) {
    console.log("usao u kreiranje req")
    return this.http.post<any>("http://localhost:8080/api/v1/appointments/"+appId+"/reservedequipments", JSON.stringify(req), {headers:this.headers});
  }
  public deleteReservedEquipment(req : Reservedequipment) {
    return this.http.delete<string>("http://localhost:8080/api/v1/reservedequipments/"+req.id, {headers:this.headers});
  }
  public updateReservedEquipment(req : Reservedequipment) {
    return this.http.put<string>("http://localhost:8080/api/v1/reservedequipments/"+req.id, {headers:this.headers});
  }
}
