import { Component, OnInit, Input, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {

  @Input() isContained = false;
  
  // Voir pour envoyer les données vers recherche.html
  @Output() taglist = false;

//  private searchBar = document.getElementById("autocompleteinput");

  constructor(private route: ActivatedRoute, private router : Router ) { }

  ngOnInit(): void {
    let mainContainer = document.getElementById("mainContainer3");
    if (!this.isContained && mainContainer != null) {
      mainContainer.classList.add("container");
    }else {
      if (mainContainer != null)
        mainContainer.classList.remove("container");
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
      this.createTag("tag ajoute");

    }else {
      // Cas d'une composition (home)
      this.router.navigate(['/recherche']);

    }
  }
}
