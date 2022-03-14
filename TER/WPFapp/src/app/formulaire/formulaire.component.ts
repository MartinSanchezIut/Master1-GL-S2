import { Component, OnInit } from '@angular/core';
import {ContactServiceService} from "../contact-service.service";
import {Contact} from "../models/Contact";
import { Router } from '@angular/router';


@Component({
  selector: 'app-formulaire',
  templateUrl: './formulaire.component.html',
  styleUrls: ['./formulaire.component.css']
})
export class FormulaireComponent implements OnInit {

  constructor(public contactService : ContactServiceService,
              private router: Router) { }

  ngOnInit(): void {
  }

  public ajouterContact(nom : String, prenom : String, age : String, num : String, addr : String, societe : String) {
    let c : Contact = new Contact(nom, prenom, Number(age), num, addr, societe) ;
    this.contactService.addContact(c);
    console.dir(this.contactService.getList() );
    this.router.navigate(['/list']);
  }

}
