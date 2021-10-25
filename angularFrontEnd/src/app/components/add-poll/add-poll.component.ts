import { Component, OnInit } from '@angular/core';
import { Poll } from 'src/app/models/poll.model';
import { PollService } from 'src/app/services/poll.service';
import { FormGroup, FormControl, FormArray, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-add-poll',
  templateUrl: './add-poll.component.html',
  styleUrls: ['./add-poll.component.css']
})

export class AddPollComponent implements OnInit {
  poll: Poll = {
    pollQuestion: '',
    askedBy: '',
    pollCode: '',
    createdAt: '',
    require_name: false,
    published: false,
    optionList: []
  };
  submitted = false;

  constructor(private pollService: PollService) { }

  ngOnInit(): void {
  }

  savePoll(): void {
    const data = {
      pollQuestion: this.poll.pollQuestion,
      askedBy: this.poll.askedBy,
      pollCode: this.poll.pollCode,
      createdAt: this.poll.createdAt,
      require_name: this.poll.require_name,
      optionList: this.poll.optionList
    };

    this.pollService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  newPoll(): void {
    this.submitted = false;
    this.poll = {
      pollQuestion: '',
      askedBy: '',
      pollCode: '',
      createdAt: '',
      require_name: false,
      published: false,
      optionList: []
    };
  }

}
//https://material.angular.io/guide/creating-a-custom-form-field-control has more info on this 

// export class AddPollComponent  {

//   pollForm: FormGroup;
  
//   constructor(private fb:FormBuilder) {
//     this.pollForm = this.fb.group({
//       pollQuestion: '',
//       askedBy: '',
//       pollCode: '',
//       createdAt: '',
//       require_name: false,
//       published: false,
//       optionList: this.fb.array([])
//     });
//   }

//   optionList() : FormArray {
//     return this.pollForm.get("optionList") as FormArray
//   }

//   newQuantity(): FormGroup {
//     return this.fb.group({
//       option: ''
//     })
//   }

//   addQuantity() {
//     this.optionList().push(this.newQuantity());
//   }

//   removeQuantity(i:number) {
//     this.optionList().removeAt(i);
//   }

//   onSubmit() {
//     console.log(this.pollForm.value);
//   }
// }
