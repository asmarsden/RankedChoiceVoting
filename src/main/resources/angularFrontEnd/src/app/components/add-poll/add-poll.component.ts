import { Component, OnInit } from '@angular/core';
import { Poll, Vote } from 'src/app/models/poll.model';
import { PollService } from 'src/app/services/poll.service';
import { FormGroup, FormControl, FormArray, FormBuilder } from '@angular/forms';
import $ from "jquery";

@Component({
  selector: 'app-add-poll',
  templateUrl: './add-poll.component.html',
  styleUrls: ['./add-poll.component.css']
})

export class AddPollComponent implements OnInit {

  poll: Poll;

  constructor(private pollService: PollService) {
    this.poll = new Poll();
    this.poll.pollQuestion = '';
    this.poll.askedBy = '';
    this.poll.multipleAnswer = false;
    this.poll.require_name = false;
    this.poll.voteList = [];
    this.poll.voteList.push(new Vote());
  }
  

  ngOnInit() {
  //might need stuff in here to make program run
  }

  add() {
    this.poll.voteList.push(new Vote());
  }

  save() {
      this.pollService.create(this.poll).subscribe(response => {
        this.showNotification('success', 'Poll created! Your poll code is: ', 'pe-7s-smile');
        console.log(response);
      },
      error => {
          console.log(error);
      });
  }

removeAnswer(id: number) {
  if (this.poll.optionList.length > 1) {
    this.poll.optionList.splice(id, 1);
  }
}

changeMultipleAnswer() {
  alert('ok');
  this.poll.multipleAnswer = !this.poll.multipleAnswer;
}

showNotification(type: string, text: string, icon: string) {
    ($ as any).notify({
      icon: icon,
      message: text
    }, {
        type: type,
        timer: 1000,
        placement: {
          from: 'top',
          align: 'right'
        }
      });
  }
}
