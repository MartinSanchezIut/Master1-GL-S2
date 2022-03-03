import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';


export interface serverResponse{
  message : String;
  resultat : Number;
  user : User;
}

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

  constructor(private http: HttpClient, private router : Router) {  }

/*
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }
*/

  public login(pseudo : String, password : String) {
    let foundUser : Observable<serverResponse> = this.http.post<serverResponse>(this.urlBase + "user/connexion", {"pseudo":pseudo,"password":password}) ;
    foundUser.subscribe(user => {
      if (user.resultat === 1) { // Connexion réussie
        localStorage.setItem('user', JSON.stringify(user.user));
        alert("Bienvenue " + user.user.pseudo + " !") ;
        this.router.navigate(['/home']);

        //localStorage.removeItem('authError');
        return 1;
      }else { // Connexion echouée
        alert("Erreur : " + user.message) ;
        //localStorage.setItem('authError', JSON.stringify(user.message));
        return -1;
      }
    });
  }

  public logout() {
    // remove user from local storage and set current user to null
    alert("Déconnexion ... à bientot !");
    localStorage.removeItem('user');
  }

  public getAllUsers() {
    return this.http.get<User[]>(this.urlBase + "users");
  }

  public register(email: String, pseudo : String, password : String) {
    // console.log("Tentative d'inscription");
    let serverCall : Observable<serverResponse> = this.http.post<serverResponse>(this.urlBase  + "user/inscription", {email, pseudo, password});
    serverCall.subscribe(val => {
      console.log(val);
      if (val.resultat === 1) { // Inscription réussite !
        alert(val.message);
        this.router.navigate(['/connexion']);
      }else {
        alert(val.message);
      }
    });
    return this.http.post(this.urlBase  + "user/inscription", {email, pseudo, password});
  }

  public isLogged() : boolean {
    if(localStorage.getItem("user") === undefined || localStorage.getItem("user") === null){
      return false;
    }else{
      return true;
    }
  }
}
