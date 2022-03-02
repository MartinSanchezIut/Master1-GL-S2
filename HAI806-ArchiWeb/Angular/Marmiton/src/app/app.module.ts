import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListRecettesComponent } from './list-recettes/list-recettes.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { DetailRecetteComponent } from './detail-recette/detail-recette.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ListeIngredientsComponent } from './liste-ingredients/liste-ingredients.component';
import { RechercheComponent } from './recherche/recherche.component';
import { AjouterrecetteComponent } from './ajouterrecette/ajouterrecette.component';

@NgModule({
  declarations: [
    AppComponent,
    ListRecettesComponent,
    PagenotfoundComponent,
    ConnexionComponent,
    DetailRecetteComponent,
    InscriptionComponent,
    HomepageComponent,
    ListeIngredientsComponent,
    RechercheComponent,
    AjouterrecetteComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
