import { Component, OnInit, Input } from '@angular/core';
import { ListRecettesService, Ingredient} from '../list-recettes.service';


@Component({
  selector: 'app-liste-ingredients',
  templateUrl: './liste-ingredients.component.html',
  styleUrls: ['./liste-ingredients.component.css']
})
export class ListeIngredientsComponent implements OnInit {
  public listIngredients: Ingredient[] = new Array();

  /*
      Si ce truc est défini (>0): 
      le module a été appelle pour composer une autre page. EG: homepage
  */
  @Input() amount: Number = -1;
  constructor(private recette: ListRecettesService ) { }

  ngOnInit(): void {
    /* 
      Si je compose une page; je n'utilise pas la class "container"
    */
    let mainContainer = document.getElementById("mainContainer2");
//    console.log(mainContainer) ;
    if (this.amount === -1 && mainContainer != null) {
      mainContainer.classList.add("container");
    }else {
      if (mainContainer != null)
        mainContainer.classList.remove("container");
    }

 //   console.log("Recup des ingredients dans le service");
    this.recette.getIngredients().subscribe(ingre => {
      this.listIngredients = ingre;
      if (this.amount !== -1) {
        this.listIngredients  = this.listIngredients .slice(0, Number(this.amount));
      }
  //  console.dir(this.listIngredients);
    });
  }


}
