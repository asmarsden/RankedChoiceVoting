import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ballot } from '../models/ballot.model';

const baseUrl = 'http://localhost:8080/api/poll';
//this baseUrl will have to change once this is ready for production

@Injectable({
  providedIn: 'root'
})
export class BallotService {

  constructor(private http: HttpClient) { }

  create(ballot: Ballot, id: any): Observable<any> {
    return this.http.post(`${baseUrl}/${id}/vote`, ballot); 
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

}
