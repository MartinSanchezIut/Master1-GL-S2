import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { serverResponse } from './user.service';

export interface Ingredient{
  nom : String;
  quantite : String;
  unite : String;
}

export interface Recette{
  _id : String;
  nom : String;
  tps_prep : String;
  difficulte : String;
  mode_prep : String;
  nb_pers : String;

  id_auteur : String;
  pseudo_auteur : String;
  date : String;

  ingredients : Ingredient[];
  etapes : any[]
}

export interface Avis{
  _id : String;
  id_recette : String;
  id_auteur : String;
  pseudo_auteur : String;
  avis : String;
  date : String;
}

export interface Likes{
  _id : String;
  id_likeditem : String;
  id_wholiked : String;
}

@Injectable({
  providedIn: 'root'
})
export class ListRecettesService {

  private urlBase: string = 'http://localhost:8888/';

  constructor(private http: HttpClient) { }

  getRecettes(): Observable<Recette[]> {
    return this.http.get<Recette[]>(this.urlBase+'recettes');
    /*console.dir(ret) ;
    return ret;*/
  }

  getAvis(): Observable<Avis[]> {
    return this.http.get<Avis[]>(this.urlBase+'avis');
  }

  getLikes(): Observable<Likes[]> {
    return this.http.get<Likes[]>(this.urlBase+'likes');
  }

  addLike(id_likeditem: String, id_wholiked : String) {
    console.log("Ajout d'un like");
    let serverCall : Observable<serverResponse> = this.http.post<serverResponse>(this.urlBase  + "likes/add", {id_likeditem, id_wholiked});
    serverCall.subscribe(val => {
      //console.log(val);
      if (val.resultat === 1) { // Ajout réussit !
        alert(val.message);
      }else {
        alert(val.message);
      }
    });
    //return this.http.post(this.urlBase  + "user/inscription", {email, pseudo, password});
  }

  addAvis(id_recette: String, id_auteur : String, pseudo_auteur : String, date : String, avis : String) {
    //console.log("Ajout d'un avis");
    let serverCall : Observable<serverResponse> = this.http.post<serverResponse>(this.urlBase  + "avis/add", {id_recette, id_auteur, pseudo_auteur, date, avis});
    serverCall.subscribe(val => {
      //console.log(val);
      if (val.resultat === 1) { // Ajout réussit !
        alert(val.message);
      }else {
        alert(val.message);
      }
    });
    //return this.http.post(this.urlBase  + "user/inscription", {email, pseudo, password});
  }



  getIngredients(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.urlBase+'ingredients');
  }

  getRecetteByID(id : String): Observable<Recette[]> {
    return this.http.get<Recette[]>(this.urlBase+'recettes/_id/'+id);
  }
  
}
