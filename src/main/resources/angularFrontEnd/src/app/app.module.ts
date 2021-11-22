import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddInputComponent } from './components/add-input/add-input.component';
import { AddBallotComponent } from './components/add-ballot/add-ballot.component';
import { PollDetailsComponent } from './components/poll-details/poll-details.component';
import { PollsListComponent } from './components/polls-list/polls-list.component';


@NgModule({
  declarations: [
    AppComponent,
    AddBallotComponent,
    PollDetailsComponent,
    PollsListComponent,
    AddInputComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
