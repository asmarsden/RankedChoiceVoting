import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Poll, Vote } from '../models/poll.model';

const baseUrl = 'http://localhost:8080/api/polls';

@Injectable({
  providedIn: 'root'
})
export class PollService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Poll[]> {
    return this.http.get<Poll[]>(baseUrl);
  }

  get(id: any): Observable<Poll> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByName(askedBy: any): Observable<Poll[]> {
    return this.http.get<Poll[]>(`${baseUrl}?askedBy=${askedBy}`);
  }
}
