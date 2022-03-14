export class Contact {

  public readonly nom : String;
  public readonly prenom : String;
  public readonly age : Number;
  public readonly num : String;
  public readonly addr : String;
  public readonly societe : String;

  constructor(nom : String, prenom : String, age : Number, num : String, addr : String, societe : String) {
      this.nom = nom ;
      this.prenom = prenom ;
      this.age = age ;
      this.num = num ;
      this.addr = addr ;
      this.societe = societe ;
  }

  public equals(data : Contact) : boolean {
    return ( this.nom === data.nom && this.prenom === data.prenom && this.age === data.age
             && this.num == data.num && this.addr === data.addr && this.societe === data.societe) ;
  }
}
