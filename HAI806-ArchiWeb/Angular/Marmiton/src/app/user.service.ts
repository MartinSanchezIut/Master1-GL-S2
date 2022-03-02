import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';


export interface User{
  _id : String;
  pseudo : String;
  email : String;
  password : String;
}


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private urlBase: string = 'http://localhost:8888/';

  constructor(private http: HttpClient) {  }

/*
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }
*/

  public login(pseudo : String, password : String) {
    localStorage.setItem('user', JSON.stringify(this.http.post(this.urlBase + "user/connexion", {"pseudo":pseudo,"password":password})));

    /* return this.http.post<any>(this.urlBase + "/user/connexion", {pseudo, password})
        .pipe(map(user => {
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem('user', JSON.stringify(user));
            return user;
        })); */
  }

  public logout() {
    // remove user from local storage and set current user to null
    alert("Déconnexion ... à bientot !");
    localStorage.removeItem('user');
  }

  public getAll() {
    return this.http.get<User[]>(this.urlBase + "users");
  }

  public register(email: String, pseudo : String, password : String) {
    return this.http.post(this.urlBase  + "user/inscription", {email, pseudo, password});
  }

  public isLogged() : boolean {
    if(localStorage.getItem("user") === undefined){
      return false;
    }else{
      return true;
    }
  }
}
