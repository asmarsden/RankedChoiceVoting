import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PollsListComponent } from './components/polls-list/polls-list.component';
import { PollDetailsComponent } from './components/poll-details/poll-details.component';
import { AddPollComponent } from './components/add-poll/add-poll.component';

const routes: Routes = [
  { path: '', redirectTo: 'polls', pathMatch: 'full' },
  { path: 'polls', component: PollsListComponent },
  { path: 'polls/:id', component: PollDetailsComponent },
  { path: 'add', component: AddPollComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
