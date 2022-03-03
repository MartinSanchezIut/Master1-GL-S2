import { Component, OnInit } from '@angular/core';
import { UserService, User } from '../user.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  private registerError : String = "" ;
  public listUsers: User[] = new Array();

  constructor(public userService : UserService) { }

  ngOnInit(): void {
  }

  public sendForm(email: String, pseudo : String, password : String) {
    // console.log(this.verifyFormValidity(email, pseudo, password)) ;
    if (this.verifyFormValidity(email, pseudo, password)) {
      this.userService.register(email, pseudo, password) ;
    }else {
      alert("Erreur : " + this.registerError) ;
    }
  }

  public verifyFormValidity(email: String, pseudo : String, password : String) : boolean {
    // On vérifie qu'il ne manque rien
    if((email === "" || pseudo === "" || password === "") ||
       (email === undefined || pseudo === undefined || password === undefined) ||
       (email === null || pseudo === null || password === null) ){
      this.registerError = "Elements manquant !";
      return false;
    }

    // On vérifie que l'email est valide
    let re = /\S+@\S+\.\S+/;
    if (! re.test(String(email))) {
      this.registerError = "Email invalide !";
      return false;
    }

    // On verifie si le pseudo ne contient pas des elements interdit
    let pseudoBanni = [' ', '@', '#', '&'] ; 
    for (let provided of pseudoBanni) {
      if (String(pseudo).includes(provided)) {
        this.registerError = "Nous interdisons '" + provided + "' dans les pseudo.";
        return false;
      }
    }

    // On verifie que le mail n'est pas déja utilise
    console.log("Recup des utilisateurs dans le service");
    this.userService.getAllUsers().subscribe(users => {
      this.listUsers = users;
      for (let usr of this.listUsers) {
        if ((email === usr.email) || (pseudo === usr.pseudo)) {
          this.registerError = "Pseudo et/ou email déja utilisé ..";
        }
      }
    });
    if (this.registerError === "Pseudo et/ou email déja utilisé .."){
      return false;
    }

    // Nous n'avons rien trouvé de mal => OK
    this.registerError = "";
    return true;
  }

}
