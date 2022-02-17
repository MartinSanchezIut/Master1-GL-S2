import { Component, OnInit } from '@angular/core';
import { ListRecettesService, Recette} from '../list-recettes.service';

@Component({
  selector: 'app-detail-recette',
  templateUrl: './detail-recette.component.html',
  styleUrls: ['./detail-recette.component.css']
})
export class DetailRecetteComponent implements OnInit {

  public listRecettes: Recette[] = new Array();
  private id : String = "620e49378156cfc5f315dad5";
  public recette : Recette = this.listRecettes[0];

  constructor(private rec: ListRecettesService ) { }

  ngOnInit(): void {
    console.log("Recup de la recette dans le service");
    this.rec.getRecetteById(this.id).subscribe(recette => {
      this.listRecettes[0] = recette;
      this.recette = this.listRecettes[0]; 
      this.recette = recette;
    console.dir(this.recette);
    });
  }

}
