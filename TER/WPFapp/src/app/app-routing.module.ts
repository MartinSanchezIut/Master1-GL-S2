import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FormulaireComponent } from './formulaire/formulaire.component';
import {HomepageComponent} from "./homepage/homepage.component";
import {ListComponent} from "./list/list.component";


const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'add', component: FormulaireComponent },
  { path: 'list', component: ListComponent },
  { path: 'detail', component: FormulaireComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
