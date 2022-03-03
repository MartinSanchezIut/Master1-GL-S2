import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


export interface Ingredient{
  _id : String;
  nom : String;
  prix : Number;
  unite : String;
}

export interface Recette{
  _id : String;
  nom : String;
  ingredients : any[];
  modeCuisson : String;
  nombrePersonnes : Number;
}

export interface Avis{
  _id : String;
  pseudo : String;
  nomrecette : String;
  commentaire : String;
  date : String;
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

  getIngredients(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.urlBase+'ingredients');
  }

  getRecetteByID(id : String): Observable<Recette[]> {
    return this.http.get<Recette[]>(this.urlBase+'recettes/_id/'+id);
  }
  
}
