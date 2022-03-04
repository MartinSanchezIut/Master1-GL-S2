import { Component, OnInit } from '@angular/core';
import { ListRecettesService, Recette, Avis} from '../list-recettes.service';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService, User } from '../user.service';
import { DatePipe } from '@angular/common';


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

      //console.dir(this.listRecettes);

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
      for (let x of avis) { // Ici filtrer les avis concernant la recette en questiion
        if (x.id_recette === this.listRecettes[0]._id) {
          this.listAvis.push(x) ;
        }
      }
      //this.listAvis = avis;
      //console.dir(this.listAvis);
    });
  }


  public sendAvis(avis : String) : void{
    let date = new Date().toLocaleDateString();
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
}
