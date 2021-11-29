import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Poll } from 'src/app/models/poll.model';
import { ActivatedRoute, Router } from '@angular/router';
import { PollService } from 'src/app/services/poll.service';
import { LogService } from 'src/app/log.service';

@Component({
  selector: 'app-view-poll',
  templateUrl: './view-poll.component.html',
  styleUrls: ['./view-poll.component.css']
})
export class ViewPollComponent implements OnInit {

  pollData : any;
  pollCode: string = '';

  constructor(private http: HttpClient, private pollService: PollService, private route: ActivatedRoute, private router: Router, private logger: LogService) {
  }

  ngOnInit() {
    //this.pollCode = this.route.snapshot.params.id;
    //this.getPollData(); //i Think this is supposed to route stuff through the html? im not sure.
  }

  getPollData(){
    // this.pollData = this.pollService.get(pollCode) //i am dumb and cant remember how to grab this from the html side
    // .subscribe( //it doesnt like me using subscribe hmmm
    //   data => {
    //     this.pollData = data;
    //     this.logger.log(data);
    //   },
    //   error => {
    //     this.logger.log(error);
    //   }); 
    this.logger.log("Test");
    this.pollData = this.fetchData();
    this.pollData.urlCode = this.pollCode; //because logging is not working on the springboot side of things...
    this.pollData.question = this.pollService.get(this.pollCode); //curious to see what this displays as

  }

  fetchData(){
    this.logger.log(this.pollService.get(this.pollCode));
    this.logger.log(this.pollCode);
    return this.pollService.get(this.pollCode);

    // return {
    //   "adminCode" : "12345678",
    //   "urlCode" : this.pollCode,
    //   "question" : "What are the best things to do",
    //   "requireName" : "false",
    //   "password" : "",
    //   "candidates" : [
    //       "Eat Apple",
    //       "Eat Banana",
    //       "Drink Beer"
    //   ],
    //   "ballots" : [] as string[]
    // }
  }

}

//alrighty so all i did was make this page fully disappear. wonderful
//okay cool i fixed that but its not grabbing the url code like i want it to, so i need to figure out why thats not happening
//YAY! it says the right value! now to like. make it connect through. but at least i Have the Value theyre typing in
//now to run it w the springboot app
//okay so its not actually getting anything, which means there is something wrong w my get function for the poll service. time to investigate
//hm i  had a typo in my  api thing...

