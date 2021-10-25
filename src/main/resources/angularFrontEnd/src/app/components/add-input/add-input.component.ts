import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormArray, FormBuilder } from '@angular/forms';
  
@Component({
  selector: 'app-add-input',
  templateUrl: './add-input.component.html',
  styleUrls: ['./add-input.component.css']
})
// export class AddInputComponent implements OnInit {
  
//   constructor() { }
  
//   ngOnInit(): void {
//   }
//   add(){
//     let row = document.createElement('div');  
//       row.className = 'row';
//       row.innerHTML = `
//       <br>
//       <input type="text">`;
//       document.querySelector('.showInputField').appendChild(row); // @ts-ignore: Object is possibly 'null'.
//   }
// }

export class AddInputComponent  {
  name = 'Angular';
  pollForm: FormGroup;
  
  constructor(private fb:FormBuilder) {
    this.pollForm = this.fb.group({
      pollQuestion: '',
      pollOptions: this.fb.array([]) ,
    });
  }

  pollOptions() : FormArray {
    return this.pollForm.get("pollOptions") as FormArray
  }

  newQuantity(): FormGroup {
    return this.fb.group({
      option: ''
    })
  }

  addQuantity() {
    this.pollOptions().push(this.newQuantity());
  }

  removeQuantity(i:number) {
    this.pollOptions().removeAt(i);
  }

  onSubmit() {
    console.log(this.pollForm.value);
  }
}