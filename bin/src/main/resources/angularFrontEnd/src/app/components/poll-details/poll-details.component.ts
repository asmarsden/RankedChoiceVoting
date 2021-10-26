import { Component, OnInit } from '@angular/core';
import { PollService } from 'src/app/services/poll.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Poll } from 'src/app/models/poll.model';

@Component({
  selector: 'app-poll-details',
  templateUrl: './poll-details.component.html',
  styleUrls: ['./poll-details.component.css']
})
export class PollDetailsComponent implements OnInit {
  currentPoll: Poll = {
    pollQuestion: '',
    askedBy: '',
    multipleAnswer: false,
    require_name: false,
    optionList: [],
    published: false
  };
  message = '';

  constructor(
    private pollService: PollService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.message = '';
    this.getPoll(this.route.snapshot.params.id);
  }

  getPoll(id: string): void {
    this.pollService.get(id)
      .subscribe(
        data => {
          this.currentPoll = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  updatePublished(status: boolean): void {
    const data = {
      pollQuestion: this.currentPoll.pollQuestion,
      askedBy: this.currentPoll.askedBy,
      published: status
    };

    this.message = '';

    this.pollService.update(this.currentPoll.pollCode, data)
      .subscribe(
        response => {
          this.currentPoll.published = status;
          console.log(response);
          this.message = response.message ? response.message : 'This poll was updated successfully!';
        },
        error => {
          console.log(error);
        });
  }

  updatePoll(): void {
    this.message = '';

    this.pollService.update(this.currentPoll.pollCode, this.currentPoll)
      .subscribe(
        response => {
          console.log(response);
          this.message = response.message ? response.message : 'This poll was updated successfully!';
        },
        error => {
          console.log(error);
        });
  }

  deletePoll(): void {
    this.pollService.delete(this.currentPoll.pollCode)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/polls']);
        },
        error => {
          console.log(error);
        });
  }
}
