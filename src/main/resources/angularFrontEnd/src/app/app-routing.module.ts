import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBallotComponent } from './add-ballot/add-ballot.component';

const routes: Routes = [
  { path: 'add-ballot', component: AddBallotComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
