import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { concat } from 'rxjs';
import { Ballot } from 'src/app/models/ballot.model';


@Component({
  selector: 'app-add-ballot',
  templateUrl: './add-ballot.component.html',
  styleUrls: ['./add-ballot.component.css']
})
export class AddBallotComponent implements OnInit {
  pollData : any;
  ballot = new Ballot();

  constructor() { }

  ngOnInit(): void {
    //grab poll code from url and fetch poll data
    this.pollData = this.fetchData();
  }

  onSubmit(form : NgForm){  
    //console.log(form.value);  
    
    var choices = [];

    for (const field in form.controls) { // 'field' is a string
      //console.log(form.controls[field].value);
      
      
      if (field.substr(0,6) == 'Choice'){
        var value = form.controls[field].value;

        if (value != '')
          choices.push(form.controls[field].value);
      }  
    }
    // console.log(choices);
    // console.log(choices.length);

    var ballot = {"parentPollCode": "abc", "name": "Chris", "ranking": choices};

    console.log(ballot);
  }

  getPollData(){
    
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
          "Drink Beer",
          "Go Hiking",
          "Ride a bike"
      ],
      "ballots" : [] as string[]
    }
  }

}
