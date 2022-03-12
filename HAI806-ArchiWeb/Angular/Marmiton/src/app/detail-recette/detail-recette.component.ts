import { Component, OnInit, Input } from '@angular/core';
import { ListRecettesService, Recette, Avis, Likes} from '../list-recettes.service';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService, User } from '../user.service';
import {formatDate} from '@angular/common';


@Component({
  selector: 'app-detail-recette',
  templateUrl: './detail-recette.component.html',
  styleUrls: ['./detail-recette.component.css']
})
export class DetailRecetteComponent implements OnInit {

  public listRecettes: Recette[] = new Array();
  public listAvis: Avis[] = new Array();
  public id : String = "";
  public listLikes: Likes[] = new Array();


  constructor(private route: ActivatedRoute, 
              private rec: ListRecettesService,
              public userService : UserService,
              private router : Router ) { }

  ngOnInit(): void {
    console.log("Recup des recettes dans le service");
    this.rec.getRecettes().subscribe(recettes => {
      this.listRecettes = recettes;

      //console.dir(this.listRecettes);

      //if( this.route.snapshot.params['id'] !== undefined && this.route.snapshot.params['id'] !== null) {
        this.id = this.route.snapshot.params['id'];
      //}
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
      for (let x of avis) { // Ici filtrer les avis concernant la recette en questiion
        if (x.id_recette === this.listRecettes[0]._id) {
          this.listAvis.push(x) ;
        }
      }
      //this.listAvis = avis;
      //console.dir(this.listAvis);
    });

    console.log("Recup des likes dans le service");
    this.rec.getLikes().subscribe(likes => {
      for( let x of likes) {
        this.listLikes.push(x);
      }
    });

  }


  public sendAvis(avis : String) : void{
    let date = formatDate(new Date(), 'dd/MM/yyy', 'en');
    /*
    console.log(this.listRecettes[0]._id);
    console.log(this.userService.getLoggedUser()._id); 
    console.log(this.userService.getLoggedUser().pseudo);
    console.log(date);
    console.log(avis) ;
    */
    this.rec.addAvis(this.listRecettes[0]._id, this.userService.getLoggedUser()._id, this.userService.getLoggedUser().pseudo, date, avis) ;
    this.reloadPage();
  }

  public reloadPage() : void  {
    window.location.reload();
  }

  public aimer(id_likeditem: String) : void {
    if (this.userService.isLogged() === true) {
      this.rec.addLike(id_likeditem, this.userService.getLoggedUser()._id) ;
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
}
