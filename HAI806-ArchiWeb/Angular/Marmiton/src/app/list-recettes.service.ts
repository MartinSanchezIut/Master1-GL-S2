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

  getRecetteByID(id : String): Observable<Recette[]> {
    return this.http.get<Recette[]>(this.urlBase+'recettes/_id/'+id);
  }
  
}
