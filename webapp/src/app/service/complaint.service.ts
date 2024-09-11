import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Complaint } from '../model/complaint';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': '*/*',
    })
   }

  public findForUser(userId : any): Observable<Complaint[]> {
    return this.http.get<Complaint[]>("http://localhost:8080/api/v1/users/"+userId+"/complaints", {headers:this.headers});
  }
  public createComplaint(userId: any, req : Complaint) {
    return this.http.post<string>("http://localhost:8080/api/v1/users/"+userId+"/complaints", JSON.stringify(req), {headers:this.headers});
  }
  public deleteComplaint(req : Complaint) {
    return this.http.delete<string>("http://localhost:8080/api/v1/complaints/"+req.id, {headers:this.headers});
  }
}
