import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ballot } from '../models/ballot.model';
import { LogService } from 'src/app/log.service';


const baseUrl = 'http://rankedchoicevoting.herokuapp.com/api/poll';

@Injectable({
  providedIn: 'root'
})
export class BallotService {

  constructor(private http: HttpClient, private logger: LogService) { }

  create(ballot: any, id: any): Observable<any> {
    return this.http.post(`${baseUrl}/${id}/vote`, ballot); 
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

}
