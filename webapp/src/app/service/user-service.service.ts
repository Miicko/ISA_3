import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersUrl: string;
  private signupUrl: string;
  private headers: HttpHeaders;
  constructor(private http: HttpClient) { 
    this.usersUrl = 'http://localhost:8080/api/v1/userspace/users';
    this.signupUrl = 'http://localhost:8080/api/v1/authentication/signup';
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': '*/*',
    })
  }
  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }
  public save(user: User) {
    return this.http.post<string>(this.signupUrl, JSON.stringify(user, null, 15), { headers: this.headers});
  }
}
