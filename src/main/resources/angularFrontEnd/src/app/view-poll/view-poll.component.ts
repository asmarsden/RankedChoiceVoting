import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-view-poll',
  templateUrl: './view-poll.component.html',
  styleUrls: ['./view-poll.component.css']
})
export class ViewPollComponent implements OnInit {
  pollData : any;

  constructor(private http: HttpClient) {}

  ngOnInit() {
  }

  getPollData(){
    this.pollData = this.fetchData();
    //console.log(this.pollData);
  }

  fetchData(){
    //call service
    return {
      "adminCode" : "12345678",
      "urlCode" : "87654321",
      "question" : "What are the best things to do",
      "requireName" : "false",
      "password" : "",
      "candidates" : [
          "Eat Apple",
          "Eat Banana",
          "Drink Beer"
      ],
      "ballots" : [] as string[]
    }
  }

}
