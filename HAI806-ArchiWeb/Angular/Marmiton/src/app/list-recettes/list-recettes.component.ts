import { Component, OnInit, Input } from '@angular/core';
import { ListRecettesService, Recette} from '../list-recettes.service';

@Component({
  selector: 'app-list-recettes',
  templateUrl: './list-recettes.component.html',
  styleUrls: ['./list-recettes.component.css']
})
export class ListRecettesComponent implements OnInit {
  public listRecettes: Recette[] = new Array();

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
    let mainContainer = document.getElementById("mainContainer");
    if (this.amount === -1 && mainContainer != null) {
      mainContainer.classList.add("container");
    }else {
      if (mainContainer != null)
        mainContainer.classList.remove("container");
    }

    console.log("Recup des recettes dans le service");
    this.recette.getRecettes().subscribe(recettes => {
      this.listRecettes = recettes;

    console.dir(this.listRecettes);
    });
  }

}
