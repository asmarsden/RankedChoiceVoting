import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { ViewPollComponent } from 'src/app/components/view-poll/view-poll.component';
import { AddBallotComponent } from 'src/app/components/add-ballot/add-ballot.component';
import { LogService } from 'src/app/log.service';
import { BallotService } from './services/ballot.service';

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
  providers: [LogService, BallotService],
  bootstrap: [AppComponent]
})
export class AppModule { }
