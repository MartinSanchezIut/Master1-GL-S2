import { Component, OnInit } from '@angular/core';
import { ListRecettesService, Recette, Avis} from '../list-recettes.service';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService, User } from '../user.service';


@Component({
  selector: 'app-detail-recette',
  templateUrl: './detail-recette.component.html',
  styleUrls: ['./detail-recette.component.css']
})
export class DetailRecetteComponent implements OnInit {

  public listRecettes: Recette[] = new Array();
  public listAvis: Avis[] = new Array();
  public id : String = "";

  constructor(private route: ActivatedRoute, 
              private rec: ListRecettesService,
              public userService : UserService,
              private router : Router ) { }

  ngOnInit(): void {
    console.log("Recup des recettes dans le service");
    this.rec.getRecettes().subscribe(recettes => {
      this.listRecettes = recettes;

      console.dir(this.listRecettes);

      this.id = this.route.snapshot.params['id'];
      let isFound : Boolean = false;

      for(let i = 0; i< this.listRecettes.length; i++) {
        if (this.listRecettes[i]._id === this.id) {
          this.listRecettes[0] = this.listRecettes[i] ;
          isFound = true;
        }
      }
      if (! isFound) {
        this.router.navigate(['/recetteNotFoundError']);
      }
    });

    console.log("Recup des avis dans le service");
    this.rec.getAvis().subscribe(avis => {
      this.listAvis = avis;
      console.dir(this.listAvis);
    });
  }
  
  /*   Cette facon de faire est plus prorpe mais bon j'y arrive pas
  ngOnInit(): void {
    //this.id = this.route.params.id;
    this.id = this.route.snapshot.params['id'];

    console.log("Recup de la recette dans le service : " + this.id);
    this.rec.getRecetteByID(this.id).subscribe(recette => {
      this.listRecettes = recette;
      console.dir(this.listRecettes);

      if (this.listRecettes.length == 0) {
        this.router.navigate(['/recetteNotFoundError']);
      }
    });
  }
  */
}
