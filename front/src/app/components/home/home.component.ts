import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActuatorService} from "../../services/actuator.service";
import {ActuatorStatus} from "../../model/actuator-status";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private actuatorService: ActuatorService) { }

  status: ActuatorStatus = null;

  ngOnInit(): void {
    this.actuatorService.getServerHealth("http://localhost:8080").subscribe( (response) => {
      this.status = response;
    })
  }

}
