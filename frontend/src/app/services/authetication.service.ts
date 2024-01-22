import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../common/user';
import { Observable } from 'rxjs';
import { Userdto } from '../common/userdto';
import { Jwtclient } from '../common/jwtclient';

@Injectable({
  providedIn: 'root'
})
export class AutheticationService {

  private apiUrl: string ='http://localhost:9092/api/v1/security';
  constructor(private httpClient:HttpClient) { }

  register(user : User):Observable<User>{
    return this.httpClient.post<User>(this.apiUrl+"/register",user)  }

    login(userdto : Userdto):Observable<Jwtclient>{
      return this.httpClient.post<Jwtclient>(this.apiUrl +"/login",userdto)
    }
}
