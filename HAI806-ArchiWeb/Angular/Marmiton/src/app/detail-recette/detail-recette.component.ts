import { Component, OnInit } from '@angular/core';
import { ListRecettesService, Recette} from '../list-recettes.service';

@Component({
  selector: 'app-detail-recette',
  templateUrl: './detail-recette.component.html',
  styleUrls: ['./detail-recette.component.css']
})
export class DetailRecetteComponent implements OnInit {

  public listRecettes: Recette[] = new Array();
  private id : String = "6211733c1770b57bceccb7a1";

  constructor(private rec: ListRecettesService ) { }

  ngOnInit(): void {
    console.log("Recup de la recette dans le service");
    this.rec.getRecetteByID(this.id).subscribe(recette => {
      this.listRecettes = recette;
      console.dir(this.listRecettes);
    });
  }

}
