import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {User} from '../model/user';


let API_URL = "http://192.168.0.75:8080/api/users/";
//let API_URL = "https://app-esdevenimients.herokuapp.com/api/users/"
//let API_URL = "http://192.168.1.44:8080/api/users/";
//let API_URL = "http://172.16.180.45:8080/api/users/";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  private getHeaders(){
    const headers = new HttpHeaders(
      {
        authorization:'Basic ' + btoa(localStorage.getItem('userEmail') + ":" + localStorage.getItem('userPassword')),
        "Content-Type":"application/json; charset=UTF-8"
      });

    return headers;
}

  login(user:User): Observable<any> {
    const headers = new HttpHeaders(user ?
      {authorization:'Basic ' + btoa(user.email + ":" + user.password)}
      :
      {});

    return this.http.get<any>(API_URL + "login", {headers: headers ,withCredentials:true})
    .pipe(map(response => {
      if(response){
        localStorage.setItem('currentUser', JSON.stringify(response));
        localStorage.setItem('userEmail',response['email']);
        localStorage.setItem('userPassword',response['password']);
        localStorage.setItem('userId',response['userId']['value']);
      }
      return response;
    }));
  }

  logOut(): Observable<any> {
    return this.http.post(API_URL + "logout", {})
    .pipe(map(response => {
      localStorage.removeItem('currentUser');
      localStorage.removeItem('userEmail');
      localStorage.removeItem('userPassword');
      localStorage.removeItem('userId');
    }));
  }

  register(user: User): Observable<any> {
    return this.http.post(API_URL + 'register', JSON.stringify(user),
    {headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }

  updateUserInfo(user: User): Observable<any> {
    let userId = localStorage.getItem("userId");
    return this.http.post(API_URL + 'updateUserInfo/' + userId, JSON.stringify(user),
    {headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }

  deleteUser(): Observable<any> {
    let userId = localStorage.getItem("userId");
    return this.http.delete(API_URL +"delete/"+ userId,
      {headers: this.getHeaders(),withCredentials:true})
      .pipe(map(response => {
        localStorage.removeItem('currentUser');
        localStorage.removeItem('userEmail');
        localStorage.removeItem('userPassword');
        localStorage.removeItem('userId');
      }));
  }
  findAllUsers(): Observable<any> {
    return this.http.get(API_URL + "all",
    {headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }

  loadUserInfo(): Observable<any> {
    let userId = localStorage.getItem("userId");
    return this.http.get(API_URL + userId,
      {headers: this.getHeaders(),withCredentials:true});
  }
}