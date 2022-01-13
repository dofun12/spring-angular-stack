import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ActuatorStatus} from "../model/actuator-status";

@Injectable({
  providedIn: 'root'
})
export class ActuatorService {

  constructor(private http: HttpClient) { }

  getServerHealth(url){
    return this.http.get<ActuatorStatus>(url+"/actuator/health");
  }
}
