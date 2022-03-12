import { Component, OnInit } from '@angular/core';
import { UserService, User } from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';
import {formatDate} from '@angular/common';
import { ListRecettesService} from '../list-recettes.service';


@Component({
  selector: 'app-ajouterrecette',
  templateUrl: './ajouterrecette.component.html',
  styleUrls: ['./ajouterrecette.component.css']
})
export class AjouterrecetteComponent implements OnInit {

  constructor(public userService : UserService,
              private router : Router,
              private recetteService: ListRecettesService) { }

  ngOnInit(): void {

    if (!this.userService.isLogged())
      this.router.navigate(['/permissionDenied']);
  }






  public ajouterRecette(nomRecette : String, tpsPrep : String, diff : String, modePrep : String, nbPers : String) {
    if (this.userService.isLogged() === true) {
      let user : User = this.userService.getLoggedUser() ;

      let id_auteur : String = user._id;
      let pseudo_auteur : String = user.pseudo;
      let date = formatDate(new Date(), 'dd/MM/yyy', 'en');

  

      let listIngredients : {nom:String, quantite:String, unite:String}[] = new Array() ;
      let ingredients = document.getElementById("ingredients");
      if (ingredients !== null && ingredients !== undefined) {
        let length = ingredients?.getElementsByClassName("ingredientRow").length ;
        //console.log(length) ;
        for (let i = 0; i < length; i++) {
          let ingredientI = ingredients?.getElementsByClassName("ingredientRow")[i];

          let nomIngrI = (<HTMLInputElement>ingredientI.children[0].firstChild).value ;
          let qqtIngrI = (<HTMLInputElement>ingredientI.children[1].firstChild).value ;
          let uniIngrI = (<HTMLInputElement>ingredientI.children[2].firstChild).value ;
          //console.log(ingredientI.children[0]) ;
          //console.log(nomIngrI + " " + qqtIngrI + " " + uniIngrI) ;
          listIngredients.push({nom: nomIngrI, quantite: qqtIngrI, unite: uniIngrI}) ;
        }

      }
      //console.log(listIngredients);
      


      let listEtapes : String[][] = new Array() ;
      let etapes = document.getElementById("etapes");
      if (etapes !== null && etapes !== undefined) {
        let length = etapes?.getElementsByClassName("etapeText").length ;
        for (let i = 1; i <= length; i++) {
          let etapeI = (<HTMLInputElement>document.getElementById("etape" + i)).value;
          
          let tempArray : String[] = new Array;
          tempArray.push(etapeI);
          listEtapes.push(tempArray) ;
        }
      }
      //console.log(listEtapes);



      let data : {nom : String, 
        tps_prep : String,
        difficulte : String, 
        mode_prep : String, 
        nb_pers : String, 
        id_auteur : String, 
        pseudo_auteur : String, 
        date : String, 
        ingredients : {nom:String, quantite:String, unite:String}[], 
        etapes : String[][]} 
        
        =
        
        {

          nom: nomRecette,
          tps_prep: tpsPrep,
          difficulte: diff,
          mode_prep: modePrep,
          nb_pers: nbPers,
          id_auteur: id_auteur,
          pseudo_auteur: pseudo_auteur,
          date: date,
          ingredients: listIngredients,
          etapes: listEtapes
        };

      console.log(data) ;
      this.recetteService.addRecette(data) ;
      this.router.navigate(['/recettes']);
    }
  }











  

  public ajouterIngredient() {
    let ingredients = document.getElementById("ingredients");
/*
        <div class="row ingredientRow">
          <div class="col m12 l8">
              <input type="text" class="black-text input" placeholder="Le nom de l'ingrédient" required/>
          </div>
          <div class="col m6 l2">
              <input type="number" min="0" class="black-text input" placeholder="Quantitée" required/>
          </div>
          <div class="col m6 l2">
              <input type="text" class="black-text input" placeholder="Unitée" required/>
          </div>
        </div>
*/

    if (ingredients !== null && ingredients !== undefined) {
      let div = document.createElement("div");
      div.className = "row ingredientRow" ;

      // ----

      let nomIngr = document.createElement("div");
      nomIngr.className = "col m12 l8" ;

      let nomInput = document.createElement("input") ;
      nomInput.type = "text";
      nomInput.className = "black-text input";
      nomInput.placeholder = "Le nom de l'ingrédient";
      nomInput.required = true;


      nomIngr.appendChild(nomInput);
      div.appendChild(nomIngr);

      // ----

      let qttIngr = document.createElement("div");
      qttIngr.className = "col m12 l2" ;

      let qttInput = document.createElement("input") ;
      qttInput.type = "number";
      qttInput.min = "0";
      qttInput.className = "black-text input";
      qttInput.placeholder = "Quantitée";
      qttInput.required = true;


      qttIngr.appendChild(qttInput);
      div.appendChild(qttIngr);

      // ----

      let unitIngr = document.createElement("div");
      unitIngr.className = "col m12 l2" ;

      let unitInput = document.createElement("input") ;
      unitInput.type = "text";
      unitInput.className = "black-text input";
      unitInput.placeholder = "Unitée";
      unitInput.required = true;


      unitIngr.appendChild(unitInput);
      div.appendChild(unitIngr);



      ingredients.appendChild(div) ;
    }
  }

  public supprimerIngredient() {
    let ingredients = document.getElementById("ingredients");
    if (ingredients !== null && ingredients !== undefined && ingredients.lastChild !== null)
      ingredients.removeChild(ingredients.lastChild) ;
  }



  private nbEtape = 2;
  public ajouterEtape() {
    let etapes = document.getElementById("etapes");
/*
  <input type="text" class="black-text input etapeText" placeholder="Decrivez ce qu'il faut faire." required/>
*/
  if (etapes !== null && etapes !== undefined) {
    let row = document.createElement("input");
    row.type = "text";
    row.id = "etape" + this.nbEtape;
    this.nbEtape++;
    row.className = "black-text input etapeText";
    row.placeholder = "Decrivez ce qu\'il faut faire.";
    row.required = true;
    etapes.appendChild(row);
    }
  }

  public supprimerEtape() {
    let etapes = document.getElementById("etapes");
    if (etapes !== null && etapes !== undefined && etapes.lastChild !== null)
      etapes.removeChild(etapes.lastChild) ;
    //console.log(etapes);
  }
}
