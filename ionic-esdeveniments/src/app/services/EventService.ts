import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from '../model/event';

//let API_URL = "http://192.168.0.75:8080/api/events/";
//let API_URL = "http://192.168.1.44:8080/api/events/";
//let API_URL = "http://172.16.180.45:8080/api/events/";
let API_URL = "https://app-esdeveniments.herokuapp.com/api/events/";

@Injectable({
    providedIn: 'root'
})
export class EventService{
    
    constructor (private http: HttpClient){}

    private getHeaders(){
        const headers = new HttpHeaders(
            {
                authorization:'Basic ' + btoa(localStorage.getItem('userEmail') + ":" + localStorage.getItem('userPassword')),
                "Content-Type":"application/json; charset=UTF-8"
            }
            );

        return headers;
    }
    
    new(event: Event): Observable<any>{
        return this.http.post(API_URL + 'new', JSON.stringify(event),
        {headers:this.getHeaders(),withCredentials:true});
    }

    findNotJoinedEvents(): Observable<any> {
        let userId = localStorage.getItem("userId");
        return this.http.get(API_URL + "listNotJoined/" + userId,
        {headers: this.getHeaders(),withCredentials:true});
    }

    findJoinedEvents(): Observable<any> {
        let userId = localStorage.getItem("userId");
        return this.http.get(API_URL + "listJoined/" + userId,
        {headers: this.getHeaders(),withCredentials:true});
    }
    
    joinEvent(eventId: number): Observable<any>{
        let userId = localStorage.getItem("userId");
        return this.http.post(API_URL + "join/" + eventId + "/" + userId,
        {headers: this.getHeaders(),withCredentials:true});
    }

    leaveEvent(eventId: number): Observable<any>{
        let userId = localStorage.getItem("userId");
        return this.http.delete(API_URL + "leave/" + eventId + "/" + userId,
        {headers: this.getHeaders(),withCredentials:true});
    }

    deleteEvent(eventId: number): Observable<any>{
        return this.http.delete(API_URL + "delete/" + eventId ,
        {headers: this.getHeaders(),withCredentials:true});
    }
}