import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionToken } from '../models/session-token';

@Injectable({
  providedIn: 'root'
})
export class LoginManagerService {

  constructor(private client: HttpClient) { }

  public connectToServer(user:string, pass:string) {
    return this.client.get<SessionToken>("http://localhost:8080/bob/login/"+user+"/"+pass); //....+type,{responseType:'text'});
  }

  public logout(token:string){
  return this.client.get("http://localhost:8080/bob/logout/"+ token, { responseType: 'text' });
}
}
