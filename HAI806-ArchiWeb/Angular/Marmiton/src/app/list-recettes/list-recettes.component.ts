import { Component, OnInit } from '@angular/core';
import { ListRecettesService, Recette} from '../list-recettes.service';

@Component({
  selector: 'app-list-recettes',
  templateUrl: './list-recettes.component.html',
  styleUrls: ['./list-recettes.component.css']
})
export class ListRecettesComponent implements OnInit {

  /*recettes = [{"name" : "Pates carbo", "ingredients" : ["Pates", "oeuf", "lardons", "sel"]},
          {"name" : "Crepes", "ingredients" : ["Farine", "Sucre", "Oeuf", "Poele"]},
          {"name" : "Test", "ingredients" : ["a", "b", "c", "d"]}
  ];*/

  public listRecettes: Recette[] = new Array();

  constructor(private recette: ListRecettesService ) { }

  ngOnInit(): void {
    console.log("Recup des recettes dans le service");
    this.recette.getRecettes().subscribe(recettes => {
      this.listRecettes = recettes;

    console.dir(this.listRecettes);
    });
  }

}
