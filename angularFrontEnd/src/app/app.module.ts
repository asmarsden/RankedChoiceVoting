import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddInputComponent } from './add-input/add-input.component';
import { AddPollComponent } from './components/add-poll/add-poll.component';
import { PollDetailsComponent } from './components/poll-details/poll-details.component';
import { PollsListComponent } from './components/polls-list/polls-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AddPollComponent,
    PollDetailsComponent,
    PollsListComponent,
    AddInputComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
