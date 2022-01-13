import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {AnotherComponent} from "./components/another/another.component";

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'another', component: AnotherComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
