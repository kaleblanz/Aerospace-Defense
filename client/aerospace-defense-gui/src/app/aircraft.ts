import { Injectable } from '@angular/core'; //tells Angular that class can be injected into my map component
import { HttpClient } from '@angular/common/http'; // tool that peforms HTTP Requests
import { Observable } from 'rxjs'; // Stream object that allows the map to listen for data updates

//makrs this class as a service
@Injectable({
  providedIn: 'root', //makes this service a global tool to any part of your app
})

export class AircraftService {
  private apiURL = 'http://localhost:8080/api/get-all-aircraft';

  constructor(private http: HttpClient){} //inject Httpclient into constructor so we can use this.http

  // requests to java backend and returns aircrafts
  getAircraftData(): Observable<any[]> {
    //sends HTTP Get Request to Java URL
    // fetches JSON from Hashmap in Java
    return this.http.get<any[]>(this.apiURL); 
  }

}
