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
        //console.log(tags);
        for(let tag of tags) {
          this.createTag(tag);
        }
        localStorage.removeItem('tagRecherche');

        // script de recherche !
        this.chercher(this.getListOfTags()) ;
      }
    }
  }


  public chercher(listOfTags : String[]) : void {
    console.log("Recup des recettes dans le service");
    let listRecettes : Recette[] = new Array() ;
    this.recetteService.getRecettes().subscribe(recettes => {
      
      for( let x of recettes) {
        for (let tag of this.getListOfTags()) {
          if (x.difficulte.includes(String(tag)) || x.pseudo_auteur.includes(String(tag)) || x.nom.includes(String(tag)) || x.mode_prep.includes(String(tag))) {
                listRecettes.push(x);

          }else { // Recherche dans les ingredients
            for (let z of x.ingredients) {
              if (z.nom.includes(String(tag))) {
                listRecettes.push(x);
              }
            }
          }
        }
      }    
    });

    console.dir(listRecettes);
    let listOfFound = document.getElementById("listOfFoundElements") ;
    console.log(listRecettes.length);
    for(let x of listRecettes) {
      console.log("test");
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
  
  public createTag(val : String) : void {
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

  public buttonClickAction() : void {
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
