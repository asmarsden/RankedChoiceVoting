import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {
  title : string = "";
  name : string = "";
  fakeData : any;
  pollData : any;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.title = "Ranked Choice Voting";
    // this.fakeData = this.getData();
    // console.log(this.fakeData);
  }

  doSomething() {
    alert(this.name)
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