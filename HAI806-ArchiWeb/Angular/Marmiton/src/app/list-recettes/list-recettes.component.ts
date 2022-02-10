import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-recettes',
  templateUrl: './list-recettes.component.html',
  styleUrls: ['./list-recettes.component.css']
})
export class ListRecettesComponent implements OnInit {

  recettes = [{"name" : "Pates carbo", "ingredients" : ["Pates", "oeuf", "lardons", "sel"]},
          {"name" : "Crepes", "ingredients" : ["Farine", "Sucre", "Oeuf", "Poele"]},
          {"name" : "Test", "ingredients" : ["a", "b", "c", "d"]}
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
