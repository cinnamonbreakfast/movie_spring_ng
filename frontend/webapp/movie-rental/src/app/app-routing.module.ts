import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientComponent } from './client/client.component';
import { ClientDetailComponent } from './client/client-detail/client-detail.component';
import { ClientAddComponent } from './client/client-add/client-add.component';

const routes: Routes = [
  {path: 'clients', component: ClientComponent},
  {path: 'clients/detail/:id', component: ClientDetailComponent},
  {path: 'clients/new', component: ClientAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
