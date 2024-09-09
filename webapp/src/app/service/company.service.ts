import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../model/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': '*/*',
    })
   }

  public findAll(): Observable<Company[]> {
    return this.http.get<Company[]>("http://localhost:8080/api/v1/companies", {headers:this.headers});
  }
}
