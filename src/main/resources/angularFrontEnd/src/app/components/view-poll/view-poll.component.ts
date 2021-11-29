import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Poll } from 'src/app/models/poll.model';
import { Ballot } from 'src/app/models/ballot.model';
import { ActivatedRoute, Router } from '@angular/router';
import { PollService } from 'src/app/services/poll.service';
import { BallotService } from 'src/app/services/ballot.service';
import { LogService } from 'src/app/log.service';

@Component({
  selector: 'app-view-poll',
  templateUrl: './view-poll.component.html',
  styleUrls: ['./view-poll.component.css']
})
export class ViewPollComponent implements OnInit {

  pollData  = new Poll();
  pollCode: string = '';
  bal = new Ballot();

  constructor(private http: HttpClient, private pollService: PollService, private route: ActivatedRoute, private router: Router, private logger: LogService, private ballotService: BallotService) {
  }

  ngOnInit() {
    //this.pollCode = this.route.snapshot.params.id;
    //this.getPollData(); //i Think this is supposed to route stuff through the html? im not sure.
  }

  getPollData(){

    this.logger.log("Test");
    let testing = this.pollService.get(this.pollCode).subscribe(
      (test)=>{
   
        this.logger.log(test);
        this.pollData.adminCode = test.adminCode;
        this.pollData.question = test.question;
        this.pollData.urlCode = test.urlCode;
        this.logger.log(this.pollData.urlCode);
        this.pollData.candidates = test.candidates;
        this.logger.log(this.pollData.candidates);
       }); 

  }

}
