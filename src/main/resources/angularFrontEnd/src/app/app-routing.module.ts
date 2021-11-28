import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBallotComponent } from 'src/app/components/add-ballot/add-ballot.component';
import { ViewPollComponent } from 'src/app/components/view-poll/view-poll.component';

const routes: Routes = [
  { path: 'add-ballot', component: AddBallotComponent },
  { path: 'view-poll', component: ViewPollComponent }
  //might need a path for homepage but im not worried abt that right now 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
