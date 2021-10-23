import { Component, OnInit } from '@angular/core';
  
@Component({
  selector: 'app-add-input',
  templateUrl: './add-input.component.html',
  styleUrls: ['./add-input.component.css']
})
export class AddInputComponent implements OnInit {
  
  constructor() { }
  
  ngOnInit(): void {
  }
  add(){
    let row = document.createElement('div');  
      row.className = 'row';
      row.innerHTML = `
      <br>
      <input type="text">`;
      document.querySelector('.showInputField').appendChild(row);
  }
}