import { Component, OnInit, Input, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ListRecettesService, Recette, Likes} from '../list-recettes.service';
import { UserService, User} from '../user.service';


@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {

  @Input() isContained = false;
  public listLikes: Likes[] = new Array();

  constructor(private route: ActivatedRoute, private router : Router, private recetteService: ListRecettesService, 
              private userService : UserService,) { }

  ngOnInit(): void {
    let mainContainer = document.getElementById("mainContainer3");
    if (!this.isContained && mainContainer != null) {
      mainContainer.classList.add("container");
    }else {
      if (mainContainer != null)
        mainContainer.classList.remove("container");
    }

    console.log("Recup des likes dans le service");
    this.recetteService.getLikes().subscribe(likes => {
      for( let x of likes) {
        this.listLikes.push(x);
      }
    });

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


  //public listRecettes : Recette[] = new Array() ;

  public chercher(listOfTags : String[]) : void {

    let mainDiv = document.getElementById("listOfFoundElements") ;
    if (mainDiv !== null)
    mainDiv.innerHTML = "" ;

    let title = document.createElement("h3") ;
    title.className = "center";
    title.innerText = "Voila ce que nous avons trouvé";
    mainDiv?.appendChild(title) ;


    console.log("Recup des recettes dans le service");
    //this.listRecettes = new Array() ;
    this.recetteService.getRecettes().subscribe(recettes => {
      
      for( let x of recettes) {
        for (let tag of this.getListOfTags()) {
          if (x.difficulte.includes(String(tag)) || x.pseudo_auteur.includes(String(tag)) || x.nom.includes(String(tag)) || x.mode_prep.includes(String(tag))) {
                //this.listRecettes.push(x);
                this.addFoundRecette(x) ;

          }else { // Recherche dans les ingredients
            for (let z of x.ingredients) {
              if (z.nom.includes(String(tag))) {
                //this.listRecettes.push(x);
                this.addFoundRecette(x) ;
              }
            }
          }
        }
      }    
    });

      // Ce truc marche pas va savoir pourquoi ...
    /*
      console.dir(this.listRecettes);
    let listOfFound = document.getElementById("listOfFoundElements") ;
    console.log(this.listRecettes.length);
    for(let x of this.listRecettes) {
      console.log("test");
    }
    */
  }

  public addFoundRecette(recette : Recette) : void{
    let mainDiv = document.getElementById("listOfFoundElements") ;
    mainDiv?.appendChild(document.createElement("br")) ;

    let card = document.createElement("div");
    card.setAttribute("style", "border-radius: 25px 25px 25px 0px;");
    card.className = "card" ;

      // Card content
    let cardContent = document.createElement("div");
    cardContent.setAttribute("style", "border-radius: 25px 25px 0px 0px;");
    cardContent.className = "card-content black-text" ;

    let idText = document.createElement("p");
    idText.setAttribute("style", "font-size: 12px;");
    idText.className = "center red-text" ;
    idText.innerText = recette._id + "";
    cardContent.appendChild(idText) ;
  
  
    let titleSpan = document.createElement("span");
    titleSpan.setAttribute("style", "font-size: 28px;font-weight: 400;");
    titleSpan.className = "card-title center" ;
    titleSpan.innerText = recette.nom + " (pour " + recette.nb_pers + " personnes)" ;
    cardContent.appendChild(titleSpan) ;


    let row = document.createElement("div");
    row.className = "row" ;

    let row1 = document.createElement("div") ;
    row1.className = "col s4" ;
    let row1text = document.createElement("p") ;
    row1text.className = "center" ;
    row1text.setAttribute("style", "font-size: 18px;") ;
    row1text.innerText = "En : " + recette.tps_prep ;
    row1.appendChild(row1text) ;
    
    let row2 = document.createElement("div") ;
    row2.className = "col s4" ;
    let row2text = document.createElement("p") ;
    row2text.className = "center" ;
    row2text.setAttribute("style", "font-size: 18px;") ;
    row2text.innerText = "Mode de préparation :" + recette.mode_prep ;
    row2.appendChild(row2text) ;    
    
    let row3 = document.createElement("div") ;
    row3.className = "col s4" ;
    let row3text = document.createElement("p") ;
    row3text.className = "center" ;
    row3text.setAttribute("style", "font-size: 18px;") ;
    row3text.innerText = "Difficulté : "+recette.difficulte;
    row3.appendChild(row3text) ;    

    row.appendChild(row1);
    row.appendChild(row2);
    row.appendChild(row3);
    cardContent.appendChild(row) ;
    
    cardContent.appendChild(document.createElement("br")) ;

    let ingrLabel = document.createElement("p") ;
    ingrLabel.setAttribute("style", "font-size: 18px; margin-bottom: 0;") ;
    ingrLabel.innerText =  "Ingrédients : ";
    cardContent.appendChild(ingrLabel) ;

    let ingrList = document.createElement("ul") ;
    let count = 0;
    for( let ingr of recette.ingredients) {
      if (count < 4) {
        let ingrListElmt = document.createElement("li") ;

        let ingrListElmtLbl = document.createElement("p") ;
        ingrListElmtLbl.setAttribute("style", "font-size: 18px;") ;
        ingrListElmtLbl.innerText =  "\xa0\xa0\xa0"+ count +" - \xa0" + ingr.quantite + " " + ingr.unite + " " + ingr.nom ;

        ingrListElmt.appendChild(ingrListElmtLbl) ;

        ingrList.appendChild(ingrListElmt) ;
        count++;
      }
    }

    cardContent.appendChild(ingrList) ;

    let ingrLabelSepa = document.createElement("p") ;
    ingrLabelSepa.setAttribute("style", "font-size: 18px;") ;
    ingrLabelSepa.innerText =  "\xa0\xa0\xa0...";
    cardContent.appendChild(ingrLabelSepa) ;

    cardContent.appendChild(document.createElement("br")) ;
    cardContent.appendChild(document.createElement("hr")) ;
    cardContent.appendChild(document.createElement("br")) ;

    let etapesLabel = document.createElement("p") ;
    etapesLabel.setAttribute("style", "font-size: 18px; margin-bottom: 0;") ;
    etapesLabel.innerText =  "Etapes : ";
    cardContent.appendChild(etapesLabel) ;

    let etapesList = document.createElement("ul") ;
    let ecount = 0;
    for( let etape of recette.etapes) {
      if (ecount < 4) {
        let etapeListElmt = document.createElement("li") ;

        let etapeListElmtLbl = document.createElement("p") ;
        etapeListElmtLbl.setAttribute("style", "font-size: 18px;") ;
        etapeListElmtLbl.innerText =  "\xa0\xa0\xa0"+ ecount +" - \xa0" + etape[0] ;

        etapeListElmt.appendChild(etapeListElmtLbl) ;

        etapesList.appendChild(etapeListElmt) ;
        ecount++;
      }
    }
    cardContent.appendChild(etapesList) ;



    cardContent.appendChild(document.createElement("br")) ;
    cardContent.appendChild(document.createElement("br")) ;
    let author = document.createElement("p") ;
    author.className = "center";
    author.setAttribute("style", "font-size: 16px; ") ;
    author.innerText =  "Par "+recette.pseudo_auteur +" le "+recette.date;
    cardContent.appendChild(author) ;



    card.appendChild(cardContent) ;

      // La partie du bas de la div
    let cardAction = document.createElement("div");
    cardAction.setAttribute("style", "border-radius: 0px 0px 25px 0px;");
    cardAction.className = "card-action blue lighten-3" ;

    let linkToRecette = document.createElement("a") ;
    linkToRecette.href = "/recette/" + recette._id;
    //linkToRecette.setAttribute("[routerLink]", "['/recette', " + recette._id + "]");
    //linkToRecette.setAttribute("routerLinkActive", "active") ;
    linkToRecette.className = "hoverable white-text waves-effect waves-light btn blue" ;
    linkToRecette.text = "Plus de détail" ;
    cardAction.appendChild(linkToRecette) ;

    if (this.canLike(recette._id)) {
      let linkToLike = document.createElement("a") ;
      linkToLike.setAttribute("onclick", "aimer(recette._id)");
      linkToLike.className = "hoverable white-text waves-effect waves-light btn blue right" ;
      linkToLike.text = "J'aime" ;
      cardAction.appendChild(linkToLike) ;
    }else {
      let linkToLike = document.createElement("a") ;
      linkToLike.className = "hoverable white-text waves-effect waves-light btn blue disabled right" ;
      linkToLike.text = "J'aime" ;
      cardAction.appendChild(linkToLike) ;
    }
    let pAmountOfLikes = document.createElement("p") ;
    pAmountOfLikes.className = "right black-text" ;
    pAmountOfLikes.setAttribute("style", "font-size: 18px; margin-top: 5px; margin-right: 20px;") ;
    pAmountOfLikes.innerText = this.getAmountOfLikes(recette._id) + " utilisateurs ont aimé" ;
    cardAction.appendChild(pAmountOfLikes) ;

    card.append(cardAction);
    // ----
    mainDiv?.appendChild(card) ;

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
  public aimer(id_likeditem: String) : void {
    if (this.userService.isLogged() === true) {
      this.recetteService.addLike(id_likeditem, this.userService.getLoggedUser()._id) ;
      this.reloadPage();
    }
  }
  public reloadPage() : void  {
    window.location.reload();
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
