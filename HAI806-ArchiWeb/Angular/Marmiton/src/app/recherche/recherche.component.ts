import { Component, OnInit, Input, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ListRecettesService, Recette} from '../list-recettes.service';

@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {

  @Input() isContained = false;

  constructor(private route: ActivatedRoute, private router : Router, private recetteService: ListRecettesService) { }

  ngOnInit(): void {
    let mainContainer = document.getElementById("mainContainer3");
    if (!this.isContained && mainContainer != null) {
      mainContainer.classList.add("container");
    }else {
      if (mainContainer != null)
        mainContainer.classList.remove("container");
    }



    if (!this.isContained) { // Je ne suis pas contenu : Je cherche des tags dans le localStorage
      if(localStorage.getItem("tagRecherche") !== undefined && localStorage.getItem("tagRecherche") !== null){        
        let tags = JSON.parse(String (localStorage.getItem("tagRecherche")));
        console.log(tags);
        for(let tag of tags) {
          this.createTag(tag);
        }
        localStorage.removeItem('tagRecherche');
      }

      // script de recherche !
      this.chercher(this.getListOfTags()) ;
    }

  }

  public getListOfTags() : String[] {
    let tags : String[] = [];

    document.querySelectorAll('.chip').forEach(function(chip) {
      let textToAdd = String(chip.firstChild?.textContent);
      tags.push(textToAdd);
    });
    return tags;
  }
  
  public chercher(listOfTags : String[]) : void {
    console.log("Recup des recettes dans le service");
    let listRecettes : Recette[] = new Array() ;
    this.recetteService.getRecettes().subscribe(recettes => {
      
      for( let x of recettes) {
        for (let y of this.getListOfTags()) {
          if (x.difficulte.includes(String(y)) || x.pseudo_auteur.includes(String(y)) || x.nom.includes(String(y))) {
                listRecettes.push(x);

          }else { // Recherche dans les ingredients
            for (let z of x.ingredients) {
              if (z.nom.includes(String(y))) {
                listRecettes.push(x);
              }
            }
          }

        }
      }    
    });

    console.dir(listRecettes);
    let listOfFound = document.getElementById("listOfFoundElements") ;
    console.log("Avant la boucle");
    for(let i = 0; i< listRecettes.length; i++) {
      let x = listRecettes[i];
      console.log(x);
      let div = document.createElement("div");
      div.className = "card";
      div.style.borderRadius = "25px 25px 25px 0px;"
      div.innerHTML = "";
      /*
      div.innerHTML += '<div class="card-content black-text" style="border-radius: 25px 25px 0px 0px;">' ;
      div.innerHTML += '<p style="font-size: 12px;" class=" center red-text">'+x._id+'</p>';

      div.innerHTML += '<span style="font-size: 28px;font-weight: 400;" class="card-title center">'+x.nom+'(pour '+x.nb_pers+'personnes)</span>';

      div.innerHTML += '<div class="row">';
      div.innerHTML += '    <div class="col s4"> <p class="center" style="font-size: 18px;">En : '+x.tps_prep+'</p> </div>';
      div.innerHTML += '    <div class="col s4"> <p class="center" style="font-size: 18px;">Mode de préparation : '+x.mode_prep+'</p> </div>';
      div.innerHTML += '    <div class="col s4"> <p class="center" style="font-size: 18px;">Difficulté : '+x.difficulte+'</p> </div>';
      div.innerHTML += '  </div>';

      div.innerHTML += '<br>';
      div.innerHTML += '<p style="font-size: 18px; margin-bottom: 0;">Ingrédients : </p>';
      div.innerHTML += '<ul>';
      div.innerHTML += '    <li *ngFor="let i of x.ingredients; index as index">';
      div.innerHTML += '        <p *ngIf="(index < 4)" style="font-size: 18px;"> &nbsp;&nbsp;&nbsp; {{ index }} - &nbsp;{{ i.quantite }}{{ i.unite}} {{ i.nom }}  </p>';
      div.innerHTML += '    </li>';
      div.innerHTML += '</ul>';
      div.innerHTML += '<p style="font-size: 18px;"> &nbsp;&nbsp;&nbsp; ... </p>';
      
      div.innerHTML += '<br><hr><br>';

      div.innerHTML += '<p style="font-size: 18px; margin-bottom: 0;">Etapes : </p>';
      div.innerHTML += '<ul>';
      div.innerHTML += '    <li *ngFor="let i of x.etapes; index as index">';
      div.innerHTML += '        <p *ngIf="(index < 4)" style="font-size: 18px;"> &nbsp;&nbsp;&nbsp; {{ index }} - &nbsp;{{ i[0] }} </p>';
      div.innerHTML += '    </li>';
      div.innerHTML += '</ul>';
      div.innerHTML += '<p style="font-size: 18px;"> &nbsp;&nbsp;&nbsp; ... </p>';

      div.innerHTML += '<br>';
      div.innerHTML += '<br>';

      div.innerHTML += '<p class="center" style="font-size: 16px;">Par '+x.pseudo_auteur+' le '+x.date+'</p>';
      div.innerHTML += '</div>';
      div.innerHTML += '<div class="card-action blue lighten-3" style="border-radius: 0px 0px 25px 0px;">';
      div.innerHTML += '    <a href="#" [routerLink]="[\'/recette\', '+x._id+']" routerLinkActive="active" class="hoverable white-text waves-effect waves-light btn blue">Plus de détail</a>';
      div.innerHTML += '</div>';
      */
      console.log(div);
      listOfFound?.appendChild(div) ;
    }
  }

  createTag(val : String) : void {
    // <div class="chip"> Test <i class="close material-icons">close</i> </div>
    let tagList = document.getElementById("listOfChips");
    if (tagList !== null) {
      let d1 = document.createElement("div") ;
    //  d1.classList.add("chip blue lighten-1 white-text hoverable") ;
      d1.className = "chip blue lighten-1 white-text hoverable";
      d1.innerHTML = String(val) + "<i class=\"close material-icons blue lighten-1\">close</i>";
      
      tagList.appendChild(d1) ;
    }
  }

  buttonClickAction() : void {
    if (!this.isContained) {
      // Cas de la page complette
      this.chercher(this.getListOfTags()) ;
    }else {
      // Cas d'une composition (home)
      let tags : String[] = this.getListOfTags();     
      localStorage.setItem('tagRecherche', JSON.stringify(tags));
      this.router.navigate(['/recherche']);

    }
  }
}
