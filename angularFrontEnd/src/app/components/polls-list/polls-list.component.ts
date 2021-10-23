import { Component, OnInit } from '@angular/core';
import { Poll } from 'src/app/models/poll.model';
import { PollService } from 'src/app/services/poll.service';

@Component({
  selector: 'app-polls-list',
  templateUrl: './polls-list.component.html',
  styleUrls: ['./polls-list.component.css']
})
export class PollsListComponent implements OnInit {
  polls?: Poll[];
  currentPoll?: Poll;
  currentIndex = -1;
  pollQuestion = '';

  constructor(private pollService: PollService) { }

  ngOnInit(): void {
    this.retrievePolls();
  }

  retrievePolls(): void {
    this.pollService.getAll()
      .subscribe(
        data => {
          this.polls = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrievePolls();
    this.currentPoll = undefined;
    this.currentIndex = -1;
  }

  setActivePoll(poll: Poll, index: number): void {
    this.currentPoll = poll;
    this.currentIndex = index;
  }

  removeAllPolls(): void {
    this.pollService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }

  searchTitle(): void {
    this.currentPoll = undefined;
    this.currentIndex = -1;

    this.pollService.findByTitle(this.pollQuestion)
      .subscribe(
        data => {
          this.polls = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

}
