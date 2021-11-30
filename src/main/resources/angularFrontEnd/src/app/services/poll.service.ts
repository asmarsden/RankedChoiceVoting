import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Poll } from '../models/poll.model';
import { Ballot } from '../models/ballot.model';

const baseUrl = 'http://rankchoicevoting.herokuapp.com/api/poll';

@Injectable({
  providedIn: 'root'
})
export class PollService {

  constructor(private http: HttpClient) { }

  get(id: any): Observable<Poll> { 
    return this.http.get(`${baseUrl}/${id}`);
  }

  update(id: any, data: any): Observable<any> { 
    return this.http.put(`${baseUrl}/${id}`, data);
  }

}
