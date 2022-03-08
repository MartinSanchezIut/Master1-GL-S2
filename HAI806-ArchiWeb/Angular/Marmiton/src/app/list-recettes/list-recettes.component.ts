import { Component, OnInit, Input } from '@angular/core';
import { ListRecettesService, Recette, Likes} from '../list-recettes.service';
import { UserService, User } from '../user.service';

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
  constructor(public userService : UserService, public recette: ListRecettesService ) { }

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
      if (this.amount !== -1) {
        this.listRecettes  = this.listRecettes .slice(0, Number(this.amount));
      }

    //console.dir(this.listRecettes);
    });
  }

  // boucle a l'infini
  private val : boolean = false ;
  public hasAlreadyLiked(id_likeditem: String, id_wholiked : String) : boolean {
    console.log("hasAlreadyLiked(" + id_likeditem + ", " + id_wholiked + ")") ;
    console.log("Recup des likes dans le service");
    this.recette.getLikes().subscribe(likes => {
      for (let x of likes) {
        if (x.id_likeditem === id_likeditem && x.id_wholiked === id_likeditem) {
          this.val = true;
        }
      }
      this.val = false;
    });
    console.log(this.val);
    return this.val;
  }
}
