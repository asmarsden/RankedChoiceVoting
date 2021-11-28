import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ballot } from '../models/ballot.model';

const baseUrl = 'http://localhost:8080/api/add-ballot';
//this baseUrl will have to change once this is ready for production

@Injectable({
  providedIn: 'root'
})
export class BallotService {

  constructor(private http: HttpClient) { }

  create(ballot: Ballot): Observable<any> {
    return this.http.post(baseUrl, data); //it doesnt like this data here; i dont think this is quite right
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

}
