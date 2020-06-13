import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Event } from '../model/event';

let API_URL = "http://192.168.1.44:8080/api/events/";

@Injectable({
    providedIn: 'root'
})
export class EventService{
    
    constructor (private http: HttpClient){}

    new(event: Event): Observable<any>{
        return this.http.post(API_URL + 'new', JSON.stringify(event),
        {headers:{"Content-Type":"application/json; charset=UTF-8"}});
    }
}