import { Component, OnInit } from '@angular/core';
import { UserService, User } from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ajouterrecette',
  templateUrl: './ajouterrecette.component.html',
  styleUrls: ['./ajouterrecette.component.css']
})
export class AjouterrecetteComponent implements OnInit {

  constructor(public userService : UserService,
              private router : Router) { }

  ngOnInit(): void {

    if (!this.userService.isLogged())
      this.router.navigate(['/permissionDenied']);
  }


  public ajouterIngredient() {
    let ingredients = document.getElementById("ingredients");
/*
                <div class="row">
                    <div class="col m12 l8">
                        <input type="text" class="black-text input" placeholder="Le nom de l'ingrédient" required/>
                    </div>
                    <div class="col m6 l2">
                        <input type="number" class="black-text input" placeholder="Quantitée" required/>
                    </div>
                    <div class="col m6 l2">
                        <input type="text" class="black-text input" placeholder="Unitée" required/>
                    </div>
                  </div>
*/

    if (ingredients !== null && ingredients !== undefined)
      ingredients.innerHTML += '<div class="row"><div class="col m12 l8"><input type="text" class="black-text input" placeholder="Le nom de l\'ingrédient" required/></div><div class="col m6 l2"><input type="number" class="black-text input" placeholder="Quantitée" required/></div><div class="col m6 l2"><input type="text" class="black-text input" placeholder="Unitée" required/></div></div>' ;

    //console.log(ingredients);
  }

  public supprimerIngredient() {
    let ingredients = document.getElementById("ingredients");
    if (ingredients !== null && ingredients !== undefined && ingredients.lastChild !== null)
      ingredients.removeChild(ingredients.lastChild) ;
      
    //console.log(ingredients);    
  }

  public ajouterEtape() {
    let etapes = document.getElementById("etapes");
/*
  <input type="text" class="black-text input" placeholder="Decrivez ce qu'il faut faire." required/>
*/
  if (etapes !== null && etapes !== undefined)
    etapes.innerHTML += '<input type="text" class="black-text input" placeholder="Decrivez ce qu\'il faut faire." required/>'  ; 
  //console.log(etapes);
  }

  public supprimerEtape() {
    let etapes = document.getElementById("etapes");
    if (etapes !== null && etapes !== undefined && etapes.lastChild !== null)
      etapes.removeChild(etapes.lastChild) ;
    //console.log(etapes);
  }

}
