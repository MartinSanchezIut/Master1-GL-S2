import { Component, OnInit } from '@angular/core';
import {ContactServiceService} from "../contact-service.service";
import {Contact} from "../models/Contact";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  constructor(public contactService : ContactServiceService) { }
  public listOfContact : Contact[] = new Array();

  ngOnInit(): void {
    this.listOfContact = this.contactService.getList() ;
  }
}
