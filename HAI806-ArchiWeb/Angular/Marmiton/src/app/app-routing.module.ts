import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { ListRecettesComponent } from './list-recettes/list-recettes.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { DetailRecetteComponent } from './detail-recette/detail-recette.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ListeIngredientsComponent } from './liste-ingredients/liste-ingredients.component';
import { RechercheComponent } from './recherche/recherche.component';


const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'home', component: HomepageComponent },
  { path: 'recettes', component: ListRecettesComponent },
  { path: 'ingredients', component: ListeIngredientsComponent },
  { path: 'recherche', component: RechercheComponent },
  { path: 'recette/:id', component: DetailRecetteComponent },
  { path: 'connexion', component: ConnexionComponent },
  { path: 'inscription', component: InscriptionComponent },
  { path: '**', component: PagenotfoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
