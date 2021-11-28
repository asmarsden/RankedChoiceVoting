import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { ViewPollComponent } from 'src/app/components/view-poll/view-poll.component';
import { AddBallotComponent } from 'src/app/components/add-ballot/add-ballot.component';

@NgModule({
  declarations: [
    AppComponent,
    ViewPollComponent,
    AddBallotComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
