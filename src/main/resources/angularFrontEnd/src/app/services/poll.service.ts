import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Poll } from '../models/poll.model';
import { Ballot } from '../models/ballot.model';

const baseUrl = 'http://localhost:8080/api/poll';
//will need to change this baseUrl once it's ready for prod. also not even sure if this is the right url. it Should be

@Injectable({
  providedIn: 'root'
})
export class PollService {

  constructor(private http: HttpClient) { }

  get(id: any): Observable<Poll> { //this will just return a whole poll, then i can do like poll.whatever to get the details
    return this.http.get(`${baseUrl}/${id}`);
  }

  update(id: any, data: any): Observable<any> { //this is gonna be needed in order to update a poll w new ballots
    return this.http.put(`${baseUrl}/${id}`, data);
  }

}
