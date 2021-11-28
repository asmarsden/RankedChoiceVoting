import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PollsListComponent } from './components/polls-list/polls-list.component';
import { PollDetailsComponent } from './components/poll-details/poll-details.component';
import { AddBallotComponent } from './components/add-ballot/add-ballot.component';

const routes: Routes = [
  { path: '', redirectTo: 'polls', pathMatch: 'full' },
  { path: 'polls', component: PollsListComponent },
  { path: 'polls/:id', component: PollDetailsComponent },
  { path: 'add', component: AddBallotComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
