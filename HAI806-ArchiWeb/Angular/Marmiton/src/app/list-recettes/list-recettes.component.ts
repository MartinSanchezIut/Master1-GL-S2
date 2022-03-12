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
  public listLikes: Likes[] = new Array();


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
    });
    
    console.log("Recup des likes dans le service");
    this.recette.getLikes().subscribe(likes => {
      for( let x of likes) {
        this.listLikes.push(x);
      }
    });
    console.dir(this.listLikes);
    console.dir(this.listRecettes);
  }


  public aimer(id_likeditem: String) : void {
    if (this.userService.isLogged() === true) {
      this.recette.addLike(id_likeditem, this.userService.getLoggedUser()._id) ;
      this.reloadPage();
    }
  }
  public canLike(id_likeditem: String) : boolean {
    if (this.userService.isLogged() == false) {
      return false;
    }
    let user_wholiked : User = this.userService.getLoggedUser() ;
   // console.log("canLike(" + id_likeditem + ")" + this.test);
    for (let x of this.listLikes) {
      if (x.id_likeditem === id_likeditem && x.id_wholiked === user_wholiked._id) {
        return false;
      }
    }
    return true;
  }

  public getAmountOfLikes(id_likeditem : String) : Number {
    //console.log("getAmountOfLikes(" + id_likeditem + ")");
    let ret = 0;
    for (let x of this.listLikes) {
      if (x.id_likeditem === id_likeditem) {
        ret += 1;
      }
    }
    return ret;
  }

  public reloadPage() : void  {
    window.location.reload();
  }
}
