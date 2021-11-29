import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { concat } from 'rxjs';
import { Ballot } from 'src/app/models/ballot.model';
import { Poll } from 'src/app/models/poll.model';
import { PollService } from 'src/app/services/poll.service';
import { BallotService } from 'src/app/services/ballot.service';
import { LogService } from 'src/app/log.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-ballot',
  templateUrl: './add-ballot.component.html',
  styleUrls: ['./add-ballot.component.css']
})
export class AddBallotComponent implements OnInit {
  pollData  = new Poll();
  data: any;
  voterName: any;
  pollCode: string = '';
  bal = new Ballot();
  ballot: any;

  constructor(private http: HttpClient, private pollService: PollService, private route: ActivatedRoute, private router: Router, private logger: LogService, private ballotService: BallotService) { }

  ngOnInit(): void {
    this.data = this.getPollData();
  }

  onSubmit(form : NgForm){  
    var choices = [];
    for (const field in form.controls) { //this for loop grabs all the choices chosen and plops em in an array
      if (field.substr(0,6) == 'Choice'){
        var value = form.controls[field].value;

        if (value != '')
          choices.push(form.controls[field].value);
      }  
    }

    this.ballot = {"parentPollCode": this.pollCode, "name": this.voterName, "ranking": choices};

    let balsub = this.ballotService.create(this.ballot, this.pollCode).subscribe(
      (response)=>{
        this.logger.log(response);
       }); 
  }

  getPollData(){
    let testing = this.pollService.get(this.pollCode).subscribe(
      (test)=>{
        this.pollData.adminCode = test.adminCode;
        this.pollData.question = test.question;
        this.pollData.urlCode = test.urlCode;
        this.pollData.candidates = test.candidates;
       }); 
  }

}
