import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientComponent } from './client/client.component';
import { MovieComponent } from './movie/movie.component';
import { ClientListComponent } from './client/client-list/client-list.component';

import { ClientService } from './client/shared/client.service';
import { ClientDetailComponent } from './client/client-detail/client-detail.component';
import { ClientAddComponent } from './client/client-add/client-add.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    MovieComponent,
    ClientListComponent,
    ClientDetailComponent,
    ClientAddComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
