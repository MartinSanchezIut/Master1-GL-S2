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

@NgModule({
  declarations: [
    AppComponent,
    ListRecettesComponent,
    PagenotfoundComponent,
    ConnexionComponent,
    DetailRecetteComponent,
    InscriptionComponent
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
