import { Injectable } from '@angular/core';
import { Contact } from './models/Contact';

@Injectable({
  providedIn: 'root'
})
export class ContactServiceService {

  private listOfContact : Contact[] = [] ;
  constructor() {
    for (let i = 0; i < 6; i++) {
      this.listOfContact.push(new Contact("Nom" + i, "Prenom" + i, 18+i, "0123456789", i + " rue ...", "Societe"+i)) ;
    }
  }

  public addContact(data : Contact) : boolean{
      this.listOfContact.push(data) ;
      return true;
  }

  public rmContact(data : Contact) : boolean{
    let newList : Contact[] = new Array() ;
    for (let c of this.listOfContact) {
        if (! c.equals(data)) {
          newList.push(c) ;
        }
    }
    let ret : boolean =  newList.length === this.listOfContact.length;
    this.listOfContact = newList;
    return ret;
  }

  public getList() : Contact[] {
    return this.listOfContact;
  }
}
