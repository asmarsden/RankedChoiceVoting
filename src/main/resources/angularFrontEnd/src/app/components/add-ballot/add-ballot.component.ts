import { Component, OnInit } from '@angular/core';
import { Poll, Ballot } from 'src/app/models/poll.model';
import { BallotService, PollService } from 'src/app/services/poll.service';
import { FormGroup, FormControl, FormArray, FormBuilder,ValidatorFn } from '@angular/forms';
import $ from "jquery";

@Component({
  selector: 'app-add-ballot',
  templateUrl: './add-ballot.component.html',
  styleUrls: ['./add-ballot.component.css']
})
//https://coryrylan.com/blog/creating-a-dynamic-select-with-angular-forms working with this to implement the forms


export class AddBallotComponent implements OnInit {

  poll: Poll;
  ballot: Ballot;

  constructor(private ballotService: BallotService, private pollService: PollService, private fb: FormBuilder) {
    this.ballot = new Ballot();
    this.ballot.name = '';
    this.ballot.ranking = [];
    this.ballot.parentPollCode = '';
    this.ballot.create();
    this.poll.BallotList.push(new Ballot()); //prob dont need this anymore 
    this.poll.optionList = [];
  }
  

  ngOnInit() {
  
    this.dropDownForm = this.fb.group({
      first_choice: ['0'],
      second_choice: ['0'],
      third_choice: ['0'],
    });

    this.pollService.SOMETHING.subscribe(optionList => {
      this.poll.optionList = optionList;
    });
  }

  add() {
    this.poll.ballotList.push(Ballot());
  }

  save() {
      this.ballotService.create(this.ballot).subscribe(response => {
        this.showNotification('success', 'Ballot added! Your ballot code is: ', 'pe-7s-smile');
        console.log(response);
      },
      error => {
          console.log(error);
      });
  }



  dropDownForm: FormGroup;

  ngOnInit() {

    this.dropDownForm = this.fb.group({
      country: ['0'],
      state: ['0']
    });

    this.dropdownService.getAllCountry().subscribe(coutryList => {
      this.countryList = coutryList;
    });
  }

  Save() {
    console.log(this.dropDownForm.value);
  }

  GetSateById(event) {//this isnt quite what i need; i need to set all of the options to the same list minus the ones already chosen. ill look further into it but this is a good start
    

    this.pollService.getStateByCountry(Number(event.target.value)).subscribe(stateList => {
      this.stateList = stateList;
    });
  }




//example drop down stuff i have to modify to work with our stuff
//https://stackoverflow.com/questions/49595766/dynamic-multiple-dropdown-list-with-append-method-not-working might be helpful
//https://stackoverflow.com/questions/37577286/updating-multiple-select-boxes-dynamically might also be helpful
  document.getElementById('generate').onclick = function() {
 
    var values = ["dog", "cat", "parrot", "rabbit"];
 
    var select = document.createElement("select");
    select.name = "pets";
    select.id = "pets"
 
    for (const val of values)
    {
        var option = document.createElement("option");
        option.value = val;
        option.text = val.charAt(0).toUpperCase() + val.slice(1);
        select.appendChild(option);
    }
 
    var label = document.createElement("label");
    label.innerHTML = "Choose your pets: "
    label.htmlFor = "pets";
 
    document.getElementById("container").appendChild(label).appendChild(select);
}

  //in here is where im gonna have to add stuff for the drag and drop... or whatever. i might try to get it to work with just 3 drop down menus for the time being, then change to drag and drop, then change to allowing more or less answers
//http://www.rockhoppertech.com/blog/spring-mvc-3-cascading-selects-using-jquery/ this does it with jquery... but this is angular. ugh
//https://stackoverflow.com/questions/31150903/dynamically-creating-multiple-dropdown-and-auto-setting-each-dropdown-in-angular this seems helpful?? idk
//https://github.com/vjaisknit/Crud-Operation-In-Angular/blob/master/Angular%20CRUD%20Project/AngularSPA/src/app/dropdown/cc-dropdown.service.ts i can prob model stuff after this...

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
