import { Component, OnInit, Input, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { text } from 'body-parser';

@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {

  @Input() isContained = false;

  constructor(private route: ActivatedRoute, private router : Router ) { }

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
          this.searchBarEvent(tag);
        }
        localStorage.removeItem('tagRecherche');
      }

      // script de recherche !

    }

  }

  searchBarEvent(value : String) {
      this.createTag(value);
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
      this.createTag("Fonction de recherche de recette a faire");

    }else {
      // Cas d'une composition (home)
      let tags : String[] = [];

      document.querySelectorAll('.chip').forEach(function(chip) {
        let textToAdd = String(chip.firstChild?.textContent);
        //console.log(chip.firstChild?.textContent);
        tags.push(textToAdd);
      });
      
      localStorage.setItem('tagRecherche', JSON.stringify(tags));
      this.router.navigate(['/recherche']);

    }
  }
}
